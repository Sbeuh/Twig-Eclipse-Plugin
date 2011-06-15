package org.eclipse.twig.ui;

import java.text.Collator;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.eclipse.dltk.ui.editor.highlighting.HighlightingStyle;
import org.eclipse.jface.preference.ColorSelector;
import org.eclipse.jface.preference.IPreferenceStore;
import org.eclipse.jface.preference.PreferencePage;
import org.eclipse.jface.resource.JFaceResources;
import org.eclipse.jface.text.BadLocationException;
import org.eclipse.jface.text.Position;
import org.eclipse.jface.text.TextAttribute;
import org.eclipse.jface.text.source.SourceViewer;
import org.eclipse.jface.util.IPropertyChangeListener;
import org.eclipse.jface.util.PropertyChangeEvent;
import org.eclipse.jface.viewers.ISelectionChangedListener;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.jface.viewers.ITreeContentProvider;
import org.eclipse.jface.viewers.LabelProvider;
import org.eclipse.jface.viewers.ListViewer;
import org.eclipse.jface.viewers.SelectionChangedEvent;
import org.eclipse.jface.viewers.StructuredSelection;
import org.eclipse.jface.viewers.StructuredViewer;
import org.eclipse.jface.viewers.Viewer;
import org.eclipse.jface.viewers.ViewerComparator;
import org.eclipse.php.internal.core.documentModel.parser.PHPRegionContext;
import org.eclipse.php.internal.core.documentModel.parser.regions.IPhpScriptRegion;
import org.eclipse.php.internal.core.documentModel.parser.regions.PHPRegionTypes;
import org.eclipse.php.internal.core.documentModel.provisional.contenttype.ContentTypeIdForPHP;
import org.eclipse.php.internal.ui.IPHPHelpContextIds;
import org.eclipse.php.internal.ui.PHPUIMessages;
import org.eclipse.php.internal.ui.editor.SemanticHighlightingManager;
import org.eclipse.php.internal.ui.editor.highlighter.AbstractSemanticHighlighting;
import org.eclipse.php.internal.ui.preferences.PreferenceConstants;
import org.eclipse.swt.SWT;
import org.eclipse.swt.accessibility.ACC;
import org.eclipse.swt.accessibility.AccessibleAdapter;
import org.eclipse.swt.accessibility.AccessibleEvent;
import org.eclipse.swt.custom.SashForm;
import org.eclipse.swt.custom.StyleRange;
import org.eclipse.swt.custom.StyledText;
import org.eclipse.swt.events.KeyEvent;
import org.eclipse.swt.events.KeyListener;
import org.eclipse.swt.events.MouseEvent;
import org.eclipse.swt.events.MouseListener;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.events.SelectionListener;
import org.eclipse.swt.events.TraverseEvent;
import org.eclipse.swt.events.TraverseListener;
import org.eclipse.swt.graphics.Color;
import org.eclipse.swt.graphics.RGB;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Link;
import org.eclipse.twig.ui.editor.LineStyleProviderForTwig;
import org.eclipse.ui.IWorkbench;
import org.eclipse.ui.IWorkbenchPreferencePage;
import org.eclipse.ui.PlatformUI;
import org.eclipse.ui.dialogs.PreferencesUtil;
import org.eclipse.wst.sse.core.StructuredModelManager;
import org.eclipse.wst.sse.core.internal.provisional.text.IStructuredDocument;
import org.eclipse.wst.sse.core.internal.provisional.text.IStructuredDocumentRegion;
import org.eclipse.wst.sse.core.internal.provisional.text.ITextRegion;
import org.eclipse.wst.sse.core.internal.provisional.text.ITextRegionCollection;
import org.eclipse.wst.sse.core.internal.provisional.text.ITextRegionContainer;
import org.eclipse.wst.sse.ui.ISemanticHighlighting;
import org.eclipse.wst.sse.ui.ISemanticHighlightingExtension2;
import org.eclipse.wst.sse.ui.internal.SSEUIMessages;
import org.eclipse.wst.sse.ui.internal.preferences.OverlayPreferenceStore;
import org.eclipse.wst.sse.ui.internal.preferences.OverlayPreferenceStore.OverlayKey;
import org.eclipse.wst.sse.ui.internal.preferences.ui.ColorHelper;
import org.eclipse.wst.sse.ui.internal.util.EditorUtility;
import org.eclipse.wst.xml.ui.internal.XMLUIMessages;

@SuppressWarnings("restriction")
public class TwigSyntaxColoringPage extends PreferencePage implements
		IWorkbenchPreferencePage {

	
	private OverlayPreferenceStore fOverlayStore;
	
	private ColorSelector fForegroundColorEditor;
	private ColorSelector fBackgroundColorEditor;
	
	private final LineStyleProviderForTwig fStyleProvider;
	private Collection<String> fStylePreferenceKeys;

	private HashMap<String, String> fStyleToDescriptionMap;

	private Map<String, String> fContextToStyleMap;

	private Color fDefaultForeground;

	private Color fDefaultBackground;

	private StructuredViewer fStylesViewer;

	private Button fEnabler;

	private Label fForegroundLabel;

	private Label fBackgroundLabel;

	private Button fBold;

	private Button fItalic;

	private Button fStrike;

	private Button fUnderline;

	private Button fClearStyle;

	private StyledText fText;

	private IStructuredDocument fDocument;
	
	private static Map<String, Position[]> highlightingPositionMap;
	private static Map<String, HighlightingStyle> highlightingStyleMap;
	
	
	
	
	public TwigSyntaxColoringPage() {
		
		fStyleProvider = new LineStyleProviderForTwig();

	}


	@Override
	public void init(IWorkbench workbench) {

		setDescription(SSEUIMessages.SyntaxColoring_Description);

		fStyleToDescriptionMap = new HashMap<String, String>();
		fContextToStyleMap = new HashMap<String, String>();

		initStyleToDescriptionMap();
		initRegionContextToStyleMap();

		
		IPreferenceStore store = getPreferenceStore();
		OverlayKey[] keys = createOverlayStoreKeys();
		
		fOverlayStore = new OverlayPreferenceStore(store,keys);
		
		
		if (store == null) {
			System.err.println("store is null null");
			return;
		}
		
		if (fOverlayStore == null) {
			System.err.println("null");
			return;
		}
		
		fOverlayStore.load();
		fOverlayStore.start();

		fStyleProvider.setColorPreferences(fOverlayStore);
		

	}

	
	private OverlayKey[] createOverlayStoreKeys() {
		
		List<OverlayKey> overlayKeys = new ArrayList<OverlayKey>();

		Iterator<String> i = getStylePreferenceKeys().iterator();
		while (i.hasNext()) {
			String key = i.next();
			overlayKeys.add(new OverlayPreferenceStore.OverlayKey(
					OverlayPreferenceStore.STRING, key));
			overlayKeys.add(new OverlayPreferenceStore.OverlayKey(
					OverlayPreferenceStore.BOOLEAN, PreferenceConstants
							.getEnabledPreferenceKey(key)));
		}

		for (AbstractSemanticHighlighting rule : SemanticHighlightingManager
				.getInstance().getSemanticHighlightings().values()) {
			overlayKeys.add(new OverlayPreferenceStore.OverlayKey(
					OverlayPreferenceStore.STRING, rule
							.getEnabledPreferenceKey()));
			overlayKeys
					.add(new OverlayPreferenceStore.OverlayKey(
							OverlayPreferenceStore.STRING, rule
									.getColorPreferenceKey()));

			overlayKeys.add(new OverlayPreferenceStore.OverlayKey(
					OverlayPreferenceStore.STRING, rule
							.getBackgroundColorPreferenceKey()));
			overlayKeys
					.add(new OverlayPreferenceStore.OverlayKey(
							OverlayPreferenceStore.STRING, rule
									.getBoldPreferenceKey()));
			overlayKeys.add(new OverlayPreferenceStore.OverlayKey(
					OverlayPreferenceStore.STRING, rule
							.getItalicPreferenceKey()));
			overlayKeys.add(new OverlayPreferenceStore.OverlayKey(
					OverlayPreferenceStore.STRING, rule
							.getStrikethroughPreferenceKey()));
			overlayKeys.add(new OverlayPreferenceStore.OverlayKey(
					OverlayPreferenceStore.STRING, rule
							.getUnderlinePreferenceKey()));
		}

		OverlayPreferenceStore.OverlayKey[] keys = new OverlayPreferenceStore.OverlayKey[overlayKeys
				.size()];

		overlayKeys.toArray(keys);
		return keys;		
	}




	private Collection<String> getStylePreferenceKeys() {
		if (fStylePreferenceKeys == null) {
			List<String> styles = new ArrayList<String>();
			styles.add(PreferenceConstants.EDITOR_NORMAL_COLOR);
			styles.add(PreferenceConstants.EDITOR_BOUNDARYMARKER_COLOR);
			styles.add(PreferenceConstants.EDITOR_KEYWORD_COLOR);
			styles.add(PreferenceConstants.EDITOR_VARIABLE_COLOR);
			styles.add(PreferenceConstants.EDITOR_STRING_COLOR);
			styles.add(PreferenceConstants.EDITOR_NUMBER_COLOR);
			styles.add(PreferenceConstants.EDITOR_HEREDOC_COLOR);
			styles.add(PreferenceConstants.EDITOR_COMMENT_COLOR);
			styles.add(PreferenceConstants.EDITOR_LINE_COMMENT_COLOR);
			styles.add(PreferenceConstants.EDITOR_PHPDOC_COMMENT_COLOR);
			styles.add(PreferenceConstants.EDITOR_PHPDOC_COLOR);
			styles.add(PreferenceConstants.EDITOR_TASK_COLOR);

			styles.addAll(SemanticHighlightingManager.getInstance()
					.getSemanticHighlightings().keySet());

			fStylePreferenceKeys = styles;
		}
		return fStylePreferenceKeys;
	}
	
	private void initStyleToDescriptionMap() {
		fStyleToDescriptionMap.put(PreferenceConstants.EDITOR_NORMAL_COLOR,
				PHPUIMessages.ColorPage_Normal);
		fStyleToDescriptionMap.put(
				PreferenceConstants.EDITOR_BOUNDARYMARKER_COLOR,
				PHPUIMessages.ColorPage_BoundryMaker);
		fStyleToDescriptionMap.put(PreferenceConstants.EDITOR_KEYWORD_COLOR,
				PHPUIMessages.ColorPage_Keyword);
		fStyleToDescriptionMap.put(PreferenceConstants.EDITOR_VARIABLE_COLOR,
				PHPUIMessages.ColorPage_Variable);
		fStyleToDescriptionMap.put(PreferenceConstants.EDITOR_STRING_COLOR,
				PHPUIMessages.ColorPage_String);
		fStyleToDescriptionMap.put(PreferenceConstants.EDITOR_NUMBER_COLOR,
				PHPUIMessages.ColorPage_Number);
		fStyleToDescriptionMap.put(PreferenceConstants.EDITOR_HEREDOC_COLOR,
				PHPUIMessages.ColorPage_Heredoc);
		fStyleToDescriptionMap.put(PreferenceConstants.EDITOR_COMMENT_COLOR,
				PHPUIMessages.ColorPage_Comment);
		fStyleToDescriptionMap.put(
				PreferenceConstants.EDITOR_LINE_COMMENT_COLOR,
				PHPUIMessages.ColorPage_LineComment);
		fStyleToDescriptionMap.put(
				PreferenceConstants.EDITOR_PHPDOC_COMMENT_COLOR,
				PHPUIMessages.ColorPage_PHPDOCComment);
		fStyleToDescriptionMap.put(PreferenceConstants.EDITOR_PHPDOC_COLOR,
				PHPUIMessages.ColorPage_Phpdoc);
		fStyleToDescriptionMap.put(PreferenceConstants.EDITOR_TASK_COLOR,
				PHPUIMessages.ColorPage_TaskTag);
	}
	
	private void initRegionContextToStyleMap() {
		fContextToStyleMap = fStyleProvider.getColorTypesMap();
	}
	
	
	private IPropertyChangeListener fHighlightingChangeListener = new IPropertyChangeListener() {

		public void propertyChange(PropertyChangeEvent event) {
			handleHighlightingPropertyChange(event);
		}
	};



	@Override
	protected Control createContents(final Composite parent) {

		initializeDialogUnits(parent);

		fDefaultForeground = parent.getDisplay().getSystemColor(
				SWT.COLOR_LIST_FOREGROUND);
		fDefaultBackground = parent.getDisplay().getSystemColor(
				SWT.COLOR_LIST_BACKGROUND);
		Composite pageComponent = createComposite(parent, 2);
		PlatformUI
				.getWorkbench()
				.getHelpSystem()
				.setHelp(pageComponent,
						IPHPHelpContextIds.SYNTAX_COLORING_PREFERENCES);

		Link link = new Link(pageComponent, SWT.WRAP);
		link.setText(SSEUIMessages.SyntaxColoring_Link);
		link.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				PreferencesUtil.createPreferenceDialogOn(parent.getShell(),
						e.text, null, null);
			}
		});

		GridData linkData = new GridData(SWT.FILL, SWT.BEGINNING, true, false,
				2, 1);
		linkData.widthHint = 150; // only expand further if anyone else requires
		// it
		link.setLayoutData(linkData);

		new Label(pageComponent, SWT.NONE).setLayoutData(new GridData());
		new Label(pageComponent, SWT.NONE).setLayoutData(new GridData());

		SashForm editor = new SashForm(pageComponent, SWT.VERTICAL);
		GridData gridData2 = new GridData(SWT.FILL, SWT.FILL, true, true);
		gridData2.horizontalSpan = 2;
		editor.setLayoutData(gridData2);
		SashForm top = new SashForm(editor, SWT.HORIZONTAL);
		Composite styleEditor = createComposite(top, 1);
		((GridLayout) styleEditor.getLayout()).marginRight = 5;
		((GridLayout) styleEditor.getLayout()).marginLeft = 0;
		createLabel(styleEditor, XMLUIMessages.SyntaxColoringPage_0);
		fStylesViewer = createStylesViewer(styleEditor);
		GridData gridData = new GridData(SWT.FILL, SWT.FILL, true, true);
		gridData.horizontalIndent = 0;
		Iterator<String> iterator = fStyleToDescriptionMap.values().iterator();
		while (iterator.hasNext()) {
			gridData.widthHint = Math.max(gridData.widthHint,
					convertWidthInCharsToPixels(iterator.next().toString()
							.length()));
		}
		gridData.heightHint = convertHeightInCharsToPixels(5);
		fStylesViewer.getControl().setLayoutData(gridData);

		Composite editingComposite = createComposite(top, 1);
		((GridLayout) styleEditor.getLayout()).marginLeft = 5;
		createLabel(editingComposite, ""); //$NON-NLS-1$

		fEnabler = createCheckbox(editingComposite,
				XMLUIMessages.SyntaxColoringPage_2);
		fEnabler.setEnabled(false);

		Composite editControls = createComposite(editingComposite, 2);
		((GridLayout) editControls.getLayout()).marginLeft = 20;

		fForegroundLabel = createLabel(editControls,
				SSEUIMessages.Foreground_UI_);
		((GridData) fForegroundLabel.getLayoutData()).verticalAlignment = SWT.CENTER;
		fForegroundLabel.setEnabled(false);

		fForegroundColorEditor = new ColorSelector(editControls);
		Button fForegroundColor = fForegroundColorEditor.getButton();
		GridData gd = new GridData(SWT.BEGINNING, SWT.FILL, false, false);
		fForegroundColor.setLayoutData(gd);
		fForegroundColorEditor.setEnabled(false);

		fBackgroundLabel = createLabel(editControls,
				SSEUIMessages.Background_UI_);
		((GridData) fBackgroundLabel.getLayoutData()).verticalAlignment = SWT.CENTER;
		fBackgroundLabel.setEnabled(false);

		fBackgroundColorEditor = new ColorSelector(editControls);
		Button fBackgroundColor = fBackgroundColorEditor.getButton();
		gd = new GridData(SWT.BEGINNING, SWT.FILL, false, false);
		fBackgroundColor.setLayoutData(gd);
		fBackgroundColorEditor.setEnabled(false);

		fBold = createCheckbox(editControls, XMLUIMessages.SyntaxColoringPage_3);
		fBold.setEnabled(false);
		((GridData) fBold.getLayoutData()).horizontalSpan = 2;
		fItalic = createCheckbox(editControls,
				XMLUIMessages.SyntaxColoringPage_4);
		fItalic.setEnabled(false);
		((GridData) fItalic.getLayoutData()).horizontalSpan = 2;
		fStrike = createCheckbox(editControls,
				XMLUIMessages.SyntaxColoringPage_5);
		fStrike.setEnabled(false);
		((GridData) fStrike.getLayoutData()).horizontalSpan = 2;
		fUnderline = createCheckbox(editControls,
				XMLUIMessages.SyntaxColoringPage_6);
		fUnderline.setEnabled(false);
		((GridData) fUnderline.getLayoutData()).horizontalSpan = 2;
		fClearStyle = new Button(editingComposite, SWT.PUSH);
		fClearStyle.setText(SSEUIMessages.Restore_Default_UI_); //$NON-NLS-1$ = "Restore Default"
		fClearStyle.setLayoutData(new GridData(SWT.BEGINNING));
		((GridData) fClearStyle.getLayoutData()).horizontalIndent = 20;
		fClearStyle.setEnabled(false);

		Composite sampleArea = createComposite(editor, 1);

		((GridLayout) sampleArea.getLayout()).marginLeft = 5;
		((GridLayout) sampleArea.getLayout()).marginTop = 5;
		createLabel(sampleArea, SSEUIMessages.Sample_text__UI_); //$NON-NLS-1$ = "&Sample text:"
		SourceViewer viewer = new SourceViewer(sampleArea, null, SWT.BORDER
				| SWT.LEFT_TO_RIGHT | SWT.MULTI | SWT.V_SCROLL | SWT.H_SCROLL
				| SWT.READ_ONLY);
		fText = viewer.getTextWidget();
		GridData gridData3 = new GridData(SWT.FILL, SWT.FILL, true, true);
		gridData3.widthHint = convertWidthInCharsToPixels(20);
		gridData3.heightHint = convertHeightInCharsToPixels(5);
		gridData3.horizontalSpan = 2;
		fText.setLayoutData(gridData3);
		fText.setEditable(false);
		fText.setFont(JFaceResources.getFont("org.eclipse.wst.sse.ui.textfont")); //$NON-NLS-1$
		fText.addKeyListener(getTextKeyListener());
		fText.addSelectionListener(getTextSelectionListener());
		fText.addMouseListener(getTextMouseListener());
		fText.addTraverseListener(getTraverseListener());
		setAccessible(fText, SSEUIMessages.Sample_text__UI_);
		fDocument = StructuredModelManager.getModelManager()
				.createStructuredDocumentFor(
						ContentTypeIdForPHP.ContentTypeID_PHP);
		fDocument.set(getExampleText());
		viewer.setDocument(fDocument);

		top.setWeights(new int[] { 2, 1 });
		editor.setWeights(new int[] { 1, 1 });
		PlatformUI
				.getWorkbench()
				.getHelpSystem()
				.setHelp(pageComponent,
						IPHPHelpContextIds.SYNTAX_COLORING_PREFERENCES);

		fStylesViewer.setInput(getStylePreferenceKeys());

		fOverlayStore.addPropertyChangeListener(fHighlightingChangeListener);
		
		try {
			
			initHighlightingPositions();
			initHighlightingStyles();
			applyStyles();
			
		} catch (Exception e) {
			e.printStackTrace();
		}

		fStylesViewer
				.addSelectionChangedListener(new ISelectionChangedListener() {
					public void selectionChanged(SelectionChangedEvent event) {
						if (!event.getSelection().isEmpty()) {
							Object o = ((IStructuredSelection) event
									.getSelection()).getFirstElement();
							String namedStyle = o.toString();
							activate(namedStyle);
							if (namedStyle == null)
								return;
						}
					}
				});

		fForegroundColorEditor.addListener(new IPropertyChangeListener() {
			public void propertyChange(PropertyChangeEvent event) {
				if (event.getProperty().equals(ColorSelector.PROP_COLORCHANGE)) {
					Object o = ((IStructuredSelection) fStylesViewer
							.getSelection()).getFirstElement();
					String namedStyle = o.toString();

					if (SemanticHighlightingManager.getInstance()
							.getSemanticHighlightings().containsKey(namedStyle)) {
						AbstractSemanticHighlighting semanticHighlighting = SemanticHighlightingManager
								.getInstance().getSemanticHighlightings()
								.get(namedStyle);
						String oldValue = getOverlayStore().getString(
								semanticHighlighting.getColorPreferenceKey());
						String newValue = ColorHelper
								.toRGBString(fForegroundColorEditor
										.getColorValue());

						if (!newValue.equals(oldValue)) {
							getOverlayStore().setValue(
									semanticHighlighting
											.getColorPreferenceKey(), newValue);
							applyStyles();
							fText.redraw();
						}
						return;
					}

					String prefString = getOverlayStore().getString(namedStyle);
					String[] stylePrefs = ColorHelper
							.unpackStylePreferences(prefString);
					if (stylePrefs != null) {
						String oldValue = stylePrefs[0];
						// open color dialog to get new color
						String newValue = ColorHelper
								.toRGBString(fForegroundColorEditor
										.getColorValue());

						if (!newValue.equals(oldValue)) {
							stylePrefs[0] = newValue;
							String newPrefString = ColorHelper
									.packStylePreferences(stylePrefs);
							getOverlayStore().setValue(namedStyle,
									newPrefString);
							applyStyles();
							fText.redraw();
						}
					}
				}
			}
		});

		fBackgroundColorEditor.addListener(new IPropertyChangeListener() {
			public void propertyChange(PropertyChangeEvent event) {
				if (event.getProperty().equals(ColorSelector.PROP_COLORCHANGE)) {
					Object o = ((IStructuredSelection) fStylesViewer
							.getSelection()).getFirstElement();
					String namedStyle = o.toString();

					if (SemanticHighlightingManager.getInstance()
							.getSemanticHighlightings().containsKey(namedStyle)) {
						AbstractSemanticHighlighting semanticHighlighting = SemanticHighlightingManager
								.getInstance().getSemanticHighlightings()
								.get(namedStyle);
						String oldValue = getOverlayStore().getString(
								semanticHighlighting
										.getBackgroundColorPreferenceKey());
						String newValue = ColorHelper
								.toRGBString(fBackgroundColorEditor
										.getColorValue());

						if (!newValue.equals(oldValue)) {
							getOverlayStore().setValue(
									semanticHighlighting
											.getBackgroundColorPreferenceKey(),
									newValue);
							applyStyles();
							fText.redraw();
						}
						return;
					}

					String prefString = getOverlayStore().getString(namedStyle);
					String[] stylePrefs = ColorHelper
							.unpackStylePreferences(prefString);
					if (stylePrefs != null) {
						String oldValue = stylePrefs[1];
						// open color dialog to get new color
						String newValue = ColorHelper
								.toRGBString(fBackgroundColorEditor
										.getColorValue());

						if (!newValue.equals(oldValue)) {
							stylePrefs[1] = newValue;
							String newPrefString = ColorHelper
									.packStylePreferences(stylePrefs);
							getOverlayStore().setValue(namedStyle,
									newPrefString);
							applyStyles();
							fText.redraw();
							activate(namedStyle);
						}
					}
				}
			}
		});

		fBold.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				super.widgetSelected(e);
				// get current (newly old) style
				Object o = ((IStructuredSelection) fStylesViewer.getSelection())
						.getFirstElement();
				String namedStyle = o.toString();

				if (SemanticHighlightingManager.getInstance()
						.getSemanticHighlightings().containsKey(namedStyle)) {
					AbstractSemanticHighlighting semanticHighlighting = SemanticHighlightingManager
							.getInstance().getSemanticHighlightings()
							.get(namedStyle);
					String oldValue = getOverlayStore().getString(
							semanticHighlighting.getBoldPreferenceKey());
					String newValue = String.valueOf(fBold.getSelection());

					if (!newValue.equals(oldValue)) {
						getOverlayStore().setValue(
								semanticHighlighting.getBoldPreferenceKey(),
								newValue);
						applyStyles();
						fText.redraw();
					}
					return;
				}

				String prefString = getOverlayStore().getString(namedStyle);
				String[] stylePrefs = ColorHelper
						.unpackStylePreferences(prefString);
				if (stylePrefs != null) {
					String oldValue = stylePrefs[2];
					String newValue = String.valueOf(fBold.getSelection());
					if (!newValue.equals(oldValue)) {
						stylePrefs[2] = newValue;
						String newPrefString = ColorHelper
								.packStylePreferences(stylePrefs);
						getOverlayStore().setValue(namedStyle, newPrefString);
						applyStyles();
						fText.redraw();
					}
				}
			}
		});

		fItalic.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				super.widgetSelected(e);
				// get current (newly old) style
				Object o = ((IStructuredSelection) fStylesViewer.getSelection())
						.getFirstElement();
				String namedStyle = o.toString();
				if (SemanticHighlightingManager.getInstance()
						.getSemanticHighlightings().containsKey(namedStyle)) {
					AbstractSemanticHighlighting semanticHighlightingType = SemanticHighlightingManager
							.getInstance().getSemanticHighlightings()
							.get(namedStyle);
					String oldValue = getOverlayStore().getString(
							semanticHighlightingType.getItalicPreferenceKey());
					String newValue = String.valueOf(fItalic.getSelection());

					if (!newValue.equals(oldValue)) {
						getOverlayStore().setValue(
								semanticHighlightingType
										.getItalicPreferenceKey(), newValue);
						applyStyles();
						fText.redraw();
					}
					return;
				}
				String prefString = getOverlayStore().getString(namedStyle);
				String[] stylePrefs = ColorHelper
						.unpackStylePreferences(prefString);
				if (stylePrefs != null) {
					String oldValue = stylePrefs[3];
					String newValue = String.valueOf(fItalic.getSelection());
					if (!newValue.equals(oldValue)) {
						stylePrefs[3] = newValue;
						String newPrefString = ColorHelper
								.packStylePreferences(stylePrefs);
						getOverlayStore().setValue(namedStyle, newPrefString);
						applyStyles();
						fText.redraw();
					}
				}
			}
		});

		fStrike.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				super.widgetSelected(e);
				// get current (newly old) style
				Object o = ((IStructuredSelection) fStylesViewer.getSelection())
						.getFirstElement();
				String namedStyle = o.toString();
				if (SemanticHighlightingManager.getInstance()
						.getSemanticHighlightings().containsKey(namedStyle)) {
					AbstractSemanticHighlighting semanticHighlighting = SemanticHighlightingManager
							.getInstance().getSemanticHighlightings()
							.get(namedStyle);
					String oldValue = getOverlayStore().getString(
							semanticHighlighting
									.getStrikethroughPreferenceKey());
					String newValue = String.valueOf(fStrike.getSelection());

					if (!newValue.equals(oldValue)) {
						getOverlayStore().setValue(
								semanticHighlighting
										.getStrikethroughPreferenceKey(),
								newValue);
						applyStyles();
						fText.redraw();
					}
					return;
				}
				String prefString = getOverlayStore().getString(namedStyle);
				String[] stylePrefs = ColorHelper
						.unpackStylePreferences(prefString);
				if (stylePrefs != null) {
					String oldValue = stylePrefs[4];
					String newValue = String.valueOf(fStrike.getSelection());
					if (!newValue.equals(oldValue)) {
						stylePrefs[4] = newValue;
						String newPrefString = ColorHelper
								.packStylePreferences(stylePrefs);
						getOverlayStore().setValue(namedStyle, newPrefString);
						applyStyles();
						fText.redraw();
					}
				}
			}
		});

		fUnderline.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				super.widgetSelected(e);
				// get current (newly old) style
				Object o = ((IStructuredSelection) fStylesViewer.getSelection())
						.getFirstElement();
				String namedStyle = o.toString();

				if (SemanticHighlightingManager.getInstance()
						.getSemanticHighlightings().containsKey(namedStyle)) {
					AbstractSemanticHighlighting semanticHighlighting = SemanticHighlightingManager
							.getInstance().getSemanticHighlightings()
							.get(namedStyle);
					String oldValue = getOverlayStore().getString(
							semanticHighlighting.getUnderlinePreferenceKey());
					String newValue = String.valueOf(fUnderline.getSelection());

					if (!newValue.equals(oldValue)) {
						getOverlayStore().setValue(
								semanticHighlighting
										.getUnderlinePreferenceKey(), newValue);
						applyStyles();
						fText.redraw();
					}
					return;
				}

				String prefString = getOverlayStore().getString(namedStyle);
				String[] stylePrefs = ColorHelper
						.unpackStylePreferences(prefString);
				if (stylePrefs != null) {
					String oldValue = stylePrefs[5];
					String newValue = String.valueOf(fUnderline.getSelection());
					if (!newValue.equals(oldValue)) {
						stylePrefs[5] = newValue;
						String newPrefString = ColorHelper
								.packStylePreferences(stylePrefs);
						getOverlayStore().setValue(namedStyle, newPrefString);
						applyStyles();
						fText.redraw();
					}
				}
			}
		});

		fClearStyle.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				if (fStylesViewer.getSelection().isEmpty())
					return;
				String namedStyle = ((IStructuredSelection) fStylesViewer
						.getSelection()).getFirstElement().toString();
				if (SemanticHighlightingManager.getInstance()
						.getSemanticHighlightings().containsKey(namedStyle)) {
					AbstractSemanticHighlighting semanticHighlighting = SemanticHighlightingManager
							.getInstance().getSemanticHighlightings()
							.get(namedStyle);
					getOverlayStore().setToDefault(
							semanticHighlighting.getBoldPreferenceKey());
					getOverlayStore().setToDefault(
							semanticHighlighting.getColorPreferenceKey());
					getOverlayStore().setToDefault(
							semanticHighlighting
									.getBackgroundColorPreferenceKey());
					getOverlayStore().setToDefault(
							semanticHighlighting.getEnabledPreferenceKey());
					getOverlayStore().setToDefault(
							semanticHighlighting.getItalicPreferenceKey());
					getOverlayStore().setToDefault(
							semanticHighlighting
									.getStrikethroughPreferenceKey());
					getOverlayStore().setToDefault(
							semanticHighlighting.getUnderlinePreferenceKey());
					boolean enablement = getOverlayStore().getDefaultBoolean(
							semanticHighlighting.getEnabledPreferenceKey());
					switchEnablement(enablement);
				} else {
					getOverlayStore().setToDefault(namedStyle);
				}
				applyStyles();
				fText.redraw();
				activate(namedStyle);
			}
		});

		fEnabler.addSelectionListener(new SelectionAdapter() {

			public void widgetSelected(SelectionEvent e) {
				// get current (newly old) style
				Object o = ((IStructuredSelection) fStylesViewer.getSelection())
						.getFirstElement();
				String namedStyle = o.toString();

				Map<String, AbstractSemanticHighlighting> highlightingMap = SemanticHighlightingManager
						.getInstance().getSemanticHighlightings();
				if (highlightingMap.containsKey(namedStyle)) {
					AbstractSemanticHighlighting semantic = highlightingMap
							.get(namedStyle);
					boolean enablement = fEnabler.getSelection();
					semantic.getStyle().setEnabledByDefault(enablement);
					switchEnablement(enablement);
					getOverlayStore().setValue(
							semantic.getEnabledPreferenceKey(), enablement);

				} else if (getStylePreferenceKeys().contains(namedStyle)) {
					boolean enablement = fEnabler.getSelection();
					switchEnablement(enablement);
					getOverlayStore().setValue(
							PreferenceConstants
									.getEnabledPreferenceKey(namedStyle),
							enablement);
				}
			}

		});

		switchEnablement(false);
		return pageComponent;
	}
	
	private void initHighlightingStyles() {
		// TODO Auto-generated method stub
		
	}


	private void initHighlightingPositions() {
		// TODO Auto-generated method stub
		
	}


	private Composite createComposite(Composite parent, int numColumns) {
		Composite composite = new Composite(parent, SWT.NULL);

		// GridLayout
		GridLayout layout = new GridLayout();
		layout.numColumns = numColumns;
		layout.makeColumnsEqualWidth = false;
		layout.marginHeight = 0;
		layout.marginWidth = 0;
		composite.setLayout(layout);

		// GridData
		GridData data = new GridData(SWT.FILL, SWT.FILL, true, false);
		composite.setLayoutData(data);
		return composite;
	}

	
	
	private Label createLabel(Composite parent, String text) {
		Label label = new Label(parent, SWT.WRAP);
		label.setText(text);
		GridData data = new GridData(SWT.FILL, SWT.FILL, false, false);
		label.setLayoutData(data);
		label.setBackground(parent.getBackground());
		return label;
	}
	
	private StructuredViewer createStylesViewer(Composite parent) {
		StructuredViewer stylesViewer = new ListViewer(parent, SWT.SINGLE
				| SWT.V_SCROLL | SWT.H_SCROLL | SWT.BORDER);
		stylesViewer
				.setComparator(new ViewerComparator(Collator.getInstance()));
		stylesViewer.setLabelProvider(new LabelProvider() {
			@Override
			public String getText(Object element) {
				Object description = fStyleToDescriptionMap.get(element);
				if (description != null)
					return description.toString();
				else if (SemanticHighlightingManager.getInstance()
						.getSemanticHighlightings().containsKey(element)) {
					AbstractSemanticHighlighting semanticHighlighting = SemanticHighlightingManager
							.getInstance().getSemanticHighlightings()
							.get(element);
					return semanticHighlighting.getDisplayName();
				}
				return super.getText(element);
			}
		});
		stylesViewer.setContentProvider(new ITreeContentProvider() {
			public void dispose() {
			}

			public Object[] getChildren(Object parentElement) {
				return getStylePreferenceKeys().toArray();
			}

			public Object[] getElements(Object inputElement) {
				return getChildren(inputElement);
			}

			public Object getParent(Object element) {
				return getStylePreferenceKeys();
			}

			public boolean hasChildren(Object element) {
				return false;
			}

			public void inputChanged(Viewer viewer, Object oldInput,
					Object newInput) {
			}
		});
		return stylesViewer;
	}
	
	
	Button createCheckbox(Composite parent, String label) {
		Button button = new Button(parent, SWT.CHECK);
		button.setText(label);
		button.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, false));
		return button;
	}
	
	private KeyListener getTextKeyListener() {
		return new KeyListener() {
			public void keyPressed(KeyEvent e) {
				if (e.widget instanceof StyledText) {
					int x = ((StyledText) e.widget).getCaretOffset();
					selectColorAtOffset(x);
				}
			}

			public void keyReleased(KeyEvent e) {
				if (e.widget instanceof StyledText) {
					int x = ((StyledText) e.widget).getCaretOffset();
					selectColorAtOffset(x);
				}
			}
		};
	}

	private MouseListener getTextMouseListener() {
		return new MouseListener() {
			public void mouseDoubleClick(MouseEvent e) {
			}

			public void mouseDown(MouseEvent e) {
			}

			public void mouseUp(MouseEvent e) {
				if (e.widget instanceof StyledText) {
					int x = ((StyledText) e.widget).getCaretOffset();
					selectColorAtOffset(x);
				}
			}
		};
	}

	private SelectionListener getTextSelectionListener() {
		return new SelectionListener() {
			public void widgetDefaultSelected(SelectionEvent e) {
				selectColorAtOffset(e.x);
				if (e.widget instanceof StyledText) {
					((StyledText) e.widget).setSelection(e.x);
				}
			}

			public void widgetSelected(SelectionEvent e) {
				selectColorAtOffset(e.x);
				if (e.widget instanceof StyledText) {
					((StyledText) e.widget).setSelection(e.x);
				}
			}
		};
	}

	private TraverseListener getTraverseListener() {
		return new TraverseListener() {
			/**
			 * @see org.eclipse.swt.events.TraverseListener#keyTraversed(TraverseEvent)
			 */
			public void keyTraversed(TraverseEvent e) {
				if (e.widget instanceof StyledText) {
					if ((e.detail == SWT.TRAVERSE_TAB_NEXT)
							|| (e.detail == SWT.TRAVERSE_TAB_PREVIOUS))
						e.doit = true;
				}
			}
		};
	}	
	
	
	private void selectColorAtOffset(int offset) {
		String namedStyle = getNamedStyleAtOffset(offset);
		if (namedStyle != null) {
			fStylesViewer.setSelection(new StructuredSelection(namedStyle));
			fStylesViewer.reveal(namedStyle);
		} else {
			fStylesViewer.setSelection(StructuredSelection.EMPTY);
		}
		activate(namedStyle);
	}
	
	private String getNamedStyleAtOffset(int offset) {
		// ensure the offset is clean
		if (offset >= fDocument.getLength())
			return getNamedStyleAtOffset(fDocument.getLength() - 1);
		else if (offset < 0)
			return getNamedStyleAtOffset(0);

		if (highlightingPositionMap == null) {
			initHighlightingPositions();
		}

		for (Iterator iterator = highlightingPositionMap.keySet().iterator(); iterator
				.hasNext();) {
			String type = (String) iterator.next();
			Position[] positions = highlightingPositionMap.get(type);
			for (int i = 0; i < positions.length; i++) {
				if (offset >= positions[i].offset
						&& offset < positions[i].offset + positions[i].length) {
					return type;
				}
			}
		}

		IStructuredDocumentRegion documentRegion = fDocument
				.getFirstStructuredDocumentRegion();
		while (documentRegion != null && !documentRegion.containsOffset(offset)) {
			documentRegion = documentRegion.getNext();
		}

		if (documentRegion != null) {
			String regionContext;
			ITextRegion interest = documentRegion
					.getRegionAtCharacterOffset(offset);

			ITextRegionCollection container = documentRegion;
			if (interest instanceof ITextRegionContainer) {
				container = (ITextRegionContainer) interest;
				interest = container.getRegionAtCharacterOffset(offset);
			}

			if (interest.getType() == PHPRegionContext.PHP_CONTENT) {
				IPhpScriptRegion phpScript = (IPhpScriptRegion) interest;
				try {
					regionContext = phpScript
							.getPhpTokenType(offset
									- container.getStartOffset()
									- phpScript.getStart());
				} catch (BadLocationException e) {
					assert false;
					return null;
				}
			} else if (interest.getType() == PHPRegionContext.PHP_OPEN) {
				regionContext = PHPRegionTypes.PHP_OPENTAG;
			} else if (interest.getType() == PHPRegionContext.PHP_CLOSE) {
				regionContext = PHPRegionTypes.PHP_CLOSETAG;
			} else {
				regionContext = interest.getType();
			}

			// find the named style (internal/selectable name) for that
			// context
			String namedStyle = fContextToStyleMap.get(regionContext);
			return namedStyle;
		}
		return null;
	}
	
	private void activate(String namedStyle) {
		Color foreground = fDefaultForeground;
		Color background = fDefaultBackground;
		if (namedStyle == null) {
			fEnabler.setEnabled(false);
			fClearStyle.setEnabled(false);
			fBold.setEnabled(false);
			fItalic.setEnabled(false);
			fStrike.setEnabled(false);
			fUnderline.setEnabled(false);
			fForegroundLabel.setEnabled(false);
			fBackgroundLabel.setEnabled(false);
			fForegroundColorEditor.setEnabled(false);
			fBackgroundColorEditor.setEnabled(false);
			fBold.setSelection(false);
			fItalic.setSelection(false);
			fStrike.setSelection(false);
			fUnderline.setSelection(false);
		} else {
			TextAttribute attribute = getAttributeFor(namedStyle);
			AbstractSemanticHighlighting semanticType = SemanticHighlightingManager
					.getInstance().getSemanticHighlightings().get(namedStyle);
			boolean enabled = true;
			if (semanticType != null) {
				enabled = getOverlayStore().getBoolean(
						semanticType.getEnabledPreferenceKey());
			} else {
				enabled = getOverlayStore()
						.getBoolean(
								PreferenceConstants
										.getEnabledPreferenceKey(namedStyle));
			}
			fEnabler.setSelection(enabled);
			fEnabler.setEnabled(true);
			fClearStyle.setEnabled(true);
			fBold.setEnabled(enabled);
			fItalic.setEnabled(enabled);
			fStrike.setEnabled(enabled);
			fUnderline.setEnabled(enabled);
			fForegroundLabel.setEnabled(enabled);
			fBackgroundLabel.setEnabled(enabled);
			fForegroundColorEditor.setEnabled(enabled);
			fBackgroundColorEditor.setEnabled(enabled);
			fBold.setSelection((attribute.getStyle() & SWT.BOLD) != 0);
			fItalic.setSelection((attribute.getStyle() & SWT.ITALIC) != 0);
			fStrike.setSelection((attribute.getStyle() & TextAttribute.STRIKETHROUGH) != 0);
			fUnderline
					.setSelection((attribute.getStyle() & TextAttribute.UNDERLINE) != 0);
			if (attribute.getForeground() != null) {
				foreground = attribute.getForeground();
			}
			if (attribute.getBackground() != null) {
				background = attribute.getBackground();
			}
		}

		fForegroundColorEditor.setColorValue(foreground.getRGB());
		fBackgroundColorEditor.setColorValue(background.getRGB());

	}
	
	private OverlayPreferenceStore getOverlayStore() {
		return fOverlayStore;
	}
	
	private TextAttribute getAttributeFor(String namedStyle) {
		TextAttribute ta = new TextAttribute(fDefaultForeground,
				fDefaultBackground, SWT.NORMAL);

		if (namedStyle != null && fOverlayStore != null) {

			if (SemanticHighlightingManager.getInstance()
					.getSemanticHighlightings().containsKey(namedStyle)) {
				AbstractSemanticHighlighting semanticHighlightingType = SemanticHighlightingManager
						.getInstance().getSemanticHighlightings()
						.get(namedStyle);

				int fontModifier = SWT.NORMAL;
				boolean on = getOverlayStore().getBoolean(
						semanticHighlightingType.getBoldPreferenceKey());
				if (on)
					fontModifier = fontModifier | SWT.BOLD;

				on = getOverlayStore().getBoolean(
						semanticHighlightingType.getItalicPreferenceKey());
				if (on)
					fontModifier = fontModifier | SWT.ITALIC;

				on = getOverlayStore().getBoolean(
						semanticHighlightingType
								.getStrikethroughPreferenceKey());
				if (on)
					fontModifier = fontModifier | TextAttribute.STRIKETHROUGH;

				on = getOverlayStore().getBoolean(
						semanticHighlightingType.getUnderlinePreferenceKey());
				if (on)
					fontModifier = fontModifier | TextAttribute.UNDERLINE;

				String color = getOverlayStore().getString(
						semanticHighlightingType.getColorPreferenceKey());
				RGB foreground = ColorHelper.toRGB(color);
				String bgcolor = getOverlayStore().getString(
						semanticHighlightingType
								.getBackgroundColorPreferenceKey());
				RGB bgforeground = ColorHelper.toRGB(bgcolor);
				ta = new TextAttribute(
						(foreground != null) ? EditorUtility.getColor(foreground)
								: null,
						(bgforeground != null) ? EditorUtility
								.getColor(bgforeground) : null, fontModifier);
				return ta;
			}

			// note: "namedStyle" *is* the preference key
			String prefString = getOverlayStore().getString(namedStyle);
			String[] stylePrefs = ColorHelper
					.unpackStylePreferences(prefString);
			if (stylePrefs != null) {
				RGB foreground = ColorHelper.toRGB(stylePrefs[0]);
				RGB background = ColorHelper.toRGB(stylePrefs[1]);

				int fontModifier = SWT.NORMAL;

				if (stylePrefs.length > 2) {
					boolean on = Boolean.valueOf(stylePrefs[2]).booleanValue();
					if (on)
						fontModifier = fontModifier | SWT.BOLD;
				}
				if (stylePrefs.length > 3) {
					boolean on = Boolean.valueOf(stylePrefs[3]).booleanValue();
					if (on)
						fontModifier = fontModifier | SWT.ITALIC;
				}
				if (stylePrefs.length > 4) {
					boolean on = Boolean.valueOf(stylePrefs[4]).booleanValue();
					if (on)
						fontModifier = fontModifier
								| TextAttribute.STRIKETHROUGH;
				}
				if (stylePrefs.length > 5) {
					boolean on = Boolean.valueOf(stylePrefs[5]).booleanValue();
					if (on)
						fontModifier = fontModifier | TextAttribute.UNDERLINE;
				}

				ta = new TextAttribute(
						(foreground != null) ? EditorUtility.getColor(foreground)
								: null,
						(background != null) ? EditorUtility
								.getColor(background) : null, fontModifier);
			}
		}
		return ta;
	}
	
	private void setAccessible(Control control, String name) {
		if (control == null)
			return;
		final String n = name;
		control.getAccessible().addAccessibleListener(new AccessibleAdapter() {
			@Override
			public void getName(AccessibleEvent e) {
				if (e.childID == ACC.CHILDID_SELF)
					e.result = n;
			}
		});
	}

	private void switchEnablement(boolean b) {
		fBold.setEnabled(b);
		fItalic.setEnabled(b);
		fUnderline.setEnabled(b);
		fStrike.setEnabled(b);
		fBackgroundColorEditor.setEnabled(b);
		fForegroundColorEditor.setEnabled(b);
	}
	
	private String getExampleText() {
		return TwigUIMessages.ColorPage_CodeExample_0;
	}
	
	
	private void handleHighlightingPropertyChange(PropertyChangeEvent event) {
		String property = event.getProperty();
		if (property == null)
			return;
		for (Iterator iterator = SemanticHighlightingManager.getInstance()
				.getSemanticHighlightings().keySet().iterator(); iterator
				.hasNext();) {
			String type = (String) iterator.next();
			ISemanticHighlighting highlighting = SemanticHighlightingManager
					.getInstance().getSemanticHighlightings().get(type);
			HighlightingStyle style = highlightingStyleMap.get(type);
			if (property.equals(highlighting.getBoldPreferenceKey())) {
				adaptToTextStyleChange(style, event, SWT.BOLD);
			} else if (property.equals(highlighting.getColorPreferenceKey())) {
				adaptToTextForegroundChange(style, event);
			} else if ((highlighting instanceof ISemanticHighlightingExtension2)
					&& property
							.equals(((ISemanticHighlightingExtension2) highlighting)
									.getBackgroundColorPreferenceKey())) {
				adaptToTextBackgroundChange(style, event);
			} else if (property.equals(highlighting.getEnabledPreferenceKey())) {
				adaptToEnablementChange(style, event);
			} else if (property.equals(highlighting.getItalicPreferenceKey())) {
				adaptToTextStyleChange(style, event, SWT.ITALIC);
			} else if (property.equals(highlighting
					.getStrikethroughPreferenceKey())) {
				adaptToTextStyleChange(style, event,
						TextAttribute.STRIKETHROUGH);
			} else if (property
					.equals(highlighting.getUnderlinePreferenceKey())) {
				adaptToTextStyleChange(style, event, TextAttribute.UNDERLINE);
			}
		}
	}

	private void adaptToEnablementChange(HighlightingStyle highlighting,
			PropertyChangeEvent event) {
		Object value = event.getNewValue();
		boolean eventValue;
		if (value instanceof Boolean)
			eventValue = ((Boolean) value).booleanValue();
		else if (IPreferenceStore.TRUE.equals(value))
			eventValue = true;
		else
			eventValue = false;
		highlighting.setEnabled(eventValue);
	}

	private void adaptToTextForegroundChange(HighlightingStyle highlighting,
			PropertyChangeEvent event) {
		RGB rgb = null;

		Object value = event.getNewValue();
		if (value instanceof RGB)
			rgb = (RGB) value;
		else if (value instanceof String)
			rgb = ColorHelper.toRGB((String) value);

		if (rgb != null) {
			Color color = EditorUtility.getColor(rgb);
			TextAttribute oldAttr = highlighting.getTextAttribute();
			highlighting.setTextAttribute(new TextAttribute(color, oldAttr
					.getBackground(), oldAttr.getStyle()));
		}
	}

	private void adaptToTextBackgroundChange(HighlightingStyle highlighting,
			PropertyChangeEvent event) {
		RGB rgb = null;

		Object value = event.getNewValue();
		if (value instanceof RGB)
			rgb = (RGB) value;
		else if (value instanceof String)
			rgb = ColorHelper.toRGB((String) value);

		if (rgb != null) {
			Color color = EditorUtility.getColor(rgb);
			TextAttribute oldAttr = highlighting.getTextAttribute();
			highlighting.setTextAttribute(new TextAttribute(oldAttr
					.getForeground(), color, oldAttr.getStyle()));
		}
	}

	private void adaptToTextStyleChange(HighlightingStyle highlighting,
			PropertyChangeEvent event, int styleAttribute) {
		boolean eventValue = false;
		Object value = event.getNewValue();
		if (value instanceof Boolean)
			eventValue = ((Boolean) value).booleanValue();
		else if (IPreferenceStore.TRUE.equals(value))
			eventValue = true;

		TextAttribute oldAttr = highlighting.getTextAttribute();
		boolean activeValue = (oldAttr.getStyle() & styleAttribute) == styleAttribute;

		if (activeValue != eventValue)
			highlighting.setTextAttribute(new TextAttribute(oldAttr
					.getForeground(), oldAttr.getBackground(),
					eventValue ? oldAttr.getStyle() | styleAttribute : oldAttr
							.getStyle() & ~styleAttribute));
	}
	
	void applyStyles() {
		if (fText == null || fText.isDisposed())
			return;

		fStyleProvider.loadColors();

		IStructuredDocumentRegion documentRegion = fDocument
				.getFirstStructuredDocumentRegion();
		while (documentRegion != null) {
			final Collection<StyleRange> holdResults = new ArrayList<StyleRange>();
			fStyleProvider.prepareTextRegions(documentRegion, 0,
					documentRegion.getEnd(), holdResults);

			for (Iterator<StyleRange> iter = holdResults.iterator(); iter
					.hasNext();) {
				StyleRange element = iter.next();

				fText.setStyleRange(element);
			}

			for (Iterator iterator = SemanticHighlightingManager.getInstance()
					.getSemanticHighlightings().keySet().iterator(); iterator
					.hasNext();) {
				String type = (String) iterator.next();
				ISemanticHighlighting highlighting = SemanticHighlightingManager
						.getInstance().getSemanticHighlightings().get(type);
				HighlightingStyle highlightingStyle = highlightingStyleMap
						.get(type);
				if (highlightingStyle.isEnabled()) {
					Position[] positions = highlightingPositionMap.get(type);
					if (positions != null) {
						for (int i = 0; i < positions.length; i++) {
							Position position = positions[i];
							StyleRange styleRange = createStyleRange(
									highlightingStyle.getTextAttribute(),
									position);
							fText.setStyleRange(styleRange);
						}
					}
				}

			}
			documentRegion = documentRegion.getNext();
		}
	}
	
	
	private StyleRange createStyleRange(TextAttribute attr, Position position) {
		StyleRange result = new StyleRange(position.getOffset(),
				position.getLength(), attr.getForeground(),
				attr.getBackground(), attr.getStyle());
		if ((attr.getStyle() & TextAttribute.UNDERLINE) != 0) {
			result.underline = true;
			result.fontStyle &= ~TextAttribute.UNDERLINE;
		}
		if ((attr.getStyle() & TextAttribute.STRIKETHROUGH) != 0) {
			result.strikeout = true;
			result.fontStyle &= ~TextAttribute.STRIKETHROUGH;
		}
		return result;
	}
	

}
