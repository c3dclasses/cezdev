//--------------------------------------------------------------
// name: CComboBoxInstructions
// desc: implements JComboBox instruction set
//--------------------------------------------------------------
package c3dclasses;

import javax.swing.JComboBox;
import javax.swing.JComponent;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.*;

class CComboBoxInstructions extends CInstructions {
    CControlInstructions m_ccontrolinstructions;

    public CComboBoxInstructions(CProcessor cprocessor) {
        super(cprocessor);

        final CControlInstructions ccontrolinstructions =
            this.m_ccontrolinstructions = (CControlInstructions) cprocessor.getCInstructions("CControlInstrunctions");
        final CComboBoxInstructions _this = this;

        CFunction fnCreateJComboBox = new CFunction() {
    @SuppressWarnings("unchecked")
    public CReturn call(CObject obj) {
        CControl control = (CControl) obj;

        CHash options = null;
        CHash params = (CHash) control._("m_params");
        if (params != null) options = (CHash) params._("m_options");
        
        // set the options
        control._("m_options", options);

        // the control options
        if (options != null) {
            __.print("The control options: " + options.toString());
        }

        // Expect a HashMap<String,String> of items
        HashMap<String, String> items = null;
        if (options != null) {
            items = (HashMap<String, String>) options.valueOf();
        }

        JComboBox<String> comboBox;
        if (items != null && !items.isEmpty()) {
            // Convert HashMap values (labels) to String[]
            String[] labels = items.keySet().toArray(new String[0]);
            comboBox = new JComboBox<>(labels);

            // Store the HashMap for later use (key lookup)
            control._("m_itemmap", items);
        } else {
            comboBox = new JComboBox<>(new String[]{});
        }

        // Register the Swing control
        ccontrolinstructions.createJControl(control, comboBox);

        return CReturn._done(control);
    }
};

        // --- GET SELECTED ITEM ---
        CFunction fnGetSelectedItem = new CFunction() {
            public CReturn call(CObject obj) {
                CControl ccontrol = (CControl) obj;
                JComboBox<?> combo = (JComboBox<?>) ccontrol._("m_jcontrol");
                Object value = combo.getSelectedItem();
                ccontrol._("m_propvalue", value != null ? value.toString() : null);
                return null;
            }
        };

        // --- SET SELECTED ITEM ---
        CFunction fnSetSelectedItem = new CFunction() {
            public CReturn call(CObject obj) {
                CControl ccontrol = (CControl) obj;
                JComboBox<?> combo = (JComboBox<?>) ccontrol._("m_jcontrol");
                String value = (String) ccontrol._("m_propvalue");
                combo.setSelectedItem(value);
                return null;
            }
        };

        // --- ONCHANGE HANDLER ---
        CFunction fnOnChange = new CFunction() {
            public CReturn call(CObject obj) {
                final CControl ccontrol = (CControl) obj;
                JComponent jcomponent = (JComponent) ccontrol._("m_jcontrol");
                JComboBox<?> combo = (JComboBox<?>) jcomponent;
                final String selected = (String) combo.getSelectedItem();
                final String command = (String) ccontrol._("m_propvalue") + " " + (String) ccontrol._("m_strid") + " " + selected;
                //CHash options =  ccontrol._("m_options");

                if (jcomponent instanceof JComboBox && command != null && !command.isEmpty()) {
                    ((JComboBox<?>) jcomponent).addActionListener(new ActionListener() {
                        public void actionPerformed(ActionEvent e) {
                             // The source of the event is the JComboBox
                            JComboBox<?> combo = (JComboBox<?>) e.getSource();
        
                            // Get the selected item at the moment of the event
                            Object selected = combo.getSelectedItem();
                            CHash hash = (CHash) ccontrol._("m_options");
                            String selectedValue = (String) hash._(selected);

                            // Build the command string dynamically
                            String command = (String) ccontrol._("m_propvalue") + " " 
                                        + (String) ccontrol._("m_strid") + " " 
                                        + selected + " " + selectedValue;
                            
                            String command2 = "setvar " + (String) ccontrol._("m_strid") + " " + selectedValue ;

                            __.exec_command(command2);

                            __.exec_command(command);

                        }
                    });
                }
                return null;
            }
        };

        // --- REGISTER COMMANDS ---
        cprocessor._("combobox->create", fnCreateJComboBox);
        cprocessor._("combobox->get->selected", fnGetSelectedItem);
        cprocessor._("combobox->set->selected", fnSetSelectedItem);
        cprocessor._("combobox->set->onchange", fnOnChange);
        cprocessor._("select->create", fnCreateJComboBox);
        cprocessor._("select->get->selected", fnGetSelectedItem);
        cprocessor._("select->set->selected", fnSetSelectedItem);
        cprocessor._("select->set->onchange", fnOnChange);
    
    }
}
