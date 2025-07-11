package c;

import a.GameEngineBase;
import java.util.Enumeration;
import java.util.Hashtable;
import java.util.Vector;
import javax.microedition.lcdui.Graphics;

public final class DialogManager {
   private static DialogManager instance;
   private Hashtable dialogsMap = new Hashtable();
   private Vector activeDialogs = new Vector();
   private Vector dialogOrder = new Vector();
   public Dialog currentDialog;
   public DialogConfig dialogConfig = new DialogConfig();

   private DialogManager() {
      DialogConfig var10000 = this.dialogConfig;
   }

   public static DialogManager getInstance() {
      if (instance == null) {
         instance = new DialogManager();
      }

      return instance;
   }

   public final void clearDialogs() {
      if (instance != null) {
         DialogManager var1 = instance;
         Enumeration var2 = var1.dialogsMap.elements();

         while(var2.hasMoreElements()) {
            ((Dialog)var2.nextElement()).clean();
         }

         var1.dialogsMap.clear();
         var1.activeDialogs.removeAllElements();
      }

      this.currentDialog = null;
      System.gc();
   }

   public final void render(Graphics var1) {
      if (this.activeDialogs != null) {
         for(int var2 = 0; var2 < this.activeDialogs.size(); ++var2) {
            ((Dialog)this.activeDialogs.elementAt(var2)).render(var1);
         }
      }

      var1.setClip(0, 0, GameEngineBase.getScreenWidth(), GameEngineBase.getScreenHeight());
   }

   public final void closeCurrentDialog() {
      if (this.currentDialog != null) {
         this.currentDialog.closeDialog();
      }

   }

   public final void showDialog(String dialogId, int param, DialogHandler handler) {
      Dialog dialog = (Dialog)this.dialogsMap.get(dialogId);
      if (dialog != null) {
         if (!dialogId.equals("/data/ui/dialog.ui")) {
            this.dialogsMap.remove(dialogId);
         }

         this.activeDialogs.removeElement(dialog);
         if (!dialogId.equals("/data/ui/dialog.ui")) {
            dialog.clean();
            dialog = null;
         }

         if (dialogId.equals("/data/ui/dialog.ui")) {
            this.activeDialogs.addElement(dialog);
            this.currentDialog = dialog;
            this.dialogOrder.addElement(dialogId);
         }
      }

      if (dialog == null) {
         dialog = new Dialog(handler);
         dialog.setConfig(this.dialogConfig);
         dialog.loadDialog(dialogId, param, false);
         this.dialogsMap.put(dialogId, dialog);
         this.activeDialogs.addElement(dialog);
         this.currentDialog = dialog;
         this.dialogOrder.addElement(dialogId);
      }

   }

   public final void removeDialog(String var1) {
      Dialog var2 = (Dialog)this.dialogsMap.get(var1);
      if (var2 != null) {
         if (this.currentDialog.equals(var2)) {
            this.currentDialog = null;
         }

         if (!var1.equals("/data/ui/dialog.ui")) {
            this.dialogsMap.remove(var1);
         }

         this.activeDialogs.removeElement(var2);
         this.dialogOrder.removeElement(var1);
         if (!var1.equals("/data/ui/dialog.ui")) {
            var2.clean();
         }
      }

      if (!this.dialogsMap.isEmpty() && !this.activeDialogs.isEmpty()) {
         this.currentDialog = (Dialog)this.activeDialogs.lastElement();
      }

   }

   public boolean isTopDialog(String var1) {
      return !this.dialogOrder.isEmpty() && this.dialogOrder.lastElement().equals(var1);
   }

   public boolean containsDialog(String var1) {
      return !this.dialogOrder.isEmpty() && this.dialogOrder.contains(var1);
   }

   public Dialog getDialog(String var1) {
      return (Dialog)this.dialogsMap.get(var1);
   }
}
