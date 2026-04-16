//--------------------------------------------------------------
// name: CCheckboxInstructions
// desc: Instruction set for JCheckBox (checkbox) and checkbox menu item state helpers
//--------------------------------------------------------------
package c3dclasses;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.AbstractButton;
import javax.swing.JCheckBox;
import javax.swing.JComponent;

class CCheckboxInstructions extends CInstructions {
    CControlInstructions m_ccontrolinstructions;

    public CCheckboxInstructions(CProcessor cprocessor) {
        super(cprocessor);

        this.m_ccontrolinstructions =
            (CControlInstructions) cprocessor.getCInstructions("CControlInstrunctions");

        CFunction fnCreateJCheckbox = new CFunction() {
            public CReturn call(CObject obj) {
                CControl control = (CControl) obj;
                m_ccontrolinstructions.createJControl(control, new JCheckBox((String) control._("m_value")));
                return CReturn._done(control);
            }
        };

        CFunction fnSetSelected = new CFunction() {
            public CReturn call(CObject obj) {
                CControl ccontrol = (CControl) obj;
                AbstractButton jcontrol = (AbstractButton) ccontrol._("m_jcontrol");
                Boolean value = (Boolean) ccontrol._("m_propvalue");
                jcontrol.setSelected(value != null && value);
                return null;
            }
        };

        CFunction fnGetSelected = new CFunction() {
            public CReturn call(CObject obj) {
                CControl ccontrol = (CControl) obj;
                AbstractButton jcontrol = (AbstractButton) ccontrol._("m_jcontrol");
                ccontrol._("m_propvalue", jcontrol.isSelected());
                return null;
            }
        };

        CFunction fnOnClick = new CFunction() {
            public CReturn call(CObject obj) {
                CControl ccontrol = (CControl) obj;
                JComponent jcomponent = (JComponent) ccontrol._("m_jcontrol");
                final String command = (String) ccontrol._("m_propvalue") + " " + (String) ccontrol._("m_strid");

                if (jcomponent instanceof AbstractButton && command != null && !command.isEmpty()) {
                    ((AbstractButton) jcomponent).addActionListener(new ActionListener() {
                        public void actionPerformed(ActionEvent e) {
                            __.exec_command(command);
                        }
                    });
                }
                return null;
            }
        };

        cprocessor._("checkbox->create", fnCreateJCheckbox);
        cprocessor._("checkbox->set->selected", fnSetSelected);
        cprocessor._("checkbox->get->selected", fnGetSelected);
        cprocessor._("checkbox->set->onclick", fnOnClick);
    }
}
