package husacct.define.presentation.tables;

import java.util.Locale;

import husacct.control.ILocaleChangeListener;
import husacct.define.abstraction.language.DefineTranslator;

import javax.swing.table.TableColumn;


public class JTableException extends AbstractJTable implements ILocaleChangeListener {

	private static final long serialVersionUID = 3535559394466714205L;

	public JTableException() {
		super();
//		this.fillComboBoxSettings();
	}
	
	@Override
	protected void setDefaultTableSettings() {
		JTableTableModel tablemodel = (JTableTableModel) getModel();
		tablemodel.setEditable(true);
	}
	
	@Override
	protected void setColumnHeaders() {
		tablemodel.addColumn(DefineTranslator.translate("FromModule"));
		tablemodel.addColumn(DefineTranslator.translate("ToModule"));
		tablemodel.addColumn(DefineTranslator.translate("Description"));
		tablemodel.addColumn(DefineTranslator.translate("Enabled"));
	}
	
	@Override
	protected void setColumnWidths() {
		TableColumn column = null;
		for (int i = 0; i < getColumnCount(); i++) {
			column = getColumnModel().getColumn(i);
			if (i == 0) {
				column.setPreferredWidth(75); // From module
			} else if (i == 1) {
				column.setPreferredWidth(75); // To module
			} else if (i == 2) {
				column.setPreferredWidth(75); // Description
			} else if (i == 3) {
				column.setPreferredWidth(25); // Enabled
			}
		}
	}

	@Override
	public void update(Locale newLocale) {
		this.setColumnHeaders();
		
	}
	
//	@Deprecated
//	private void fillComboBoxSettings() {
//		// old
//		// String[] values = new String[] { SoftwareUnitDefinition.PACKAGE, SoftwareUnitDefinition.CLASS, SoftwareUnitDefinition.METHOD };
//		String[] values = new String[] { "package", "class", "method" };
//		// Set the combobox editor on the 1st visible column
//		TableColumn col = getColumnModel().getColumn(1);
//		col.setCellEditor(new MyComboBoxEditor(values));
//	}
}
