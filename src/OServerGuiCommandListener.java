
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JTextField;

class OServerGuiCommandListener implements ActionListener {

   final JTextField a;
   final OServerGUI b;


   OServerGuiCommandListener(OServerGUI var1, JTextField var2) {
      this.b = var1;
      this.a = var2;
   }

   public void actionPerformed(ActionEvent var1) {
      String var2 = this.a.getText().trim();
      // CanaryMod: parse our commands first.
      if(var2.length() > 0 && etc.getMCServer() != null) {
         if (!etc.getInstance().parseConsoleCommand(var2, etc.getMCServer()))
            etc.getMCServer().a(var2, this.b);
      }

      this.a.setText("");
   }
}
