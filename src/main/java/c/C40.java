package c;

import a.GameUtils;
import javax.microedition.lcdui.Graphics;

public final class C40 implements IComponent {
   private int C40_f623 = -1;
   private int C40_f624 = 0;
   private int C40_f625 = 0;
   private int C40_f626 = 0;
   private int C40_f627 = 0;
   private int C40_f628 = 1;
   private DialogData C40_f629 = new DialogData();
   private int C40_f630 = -1;
   private byte C40_f631 = 9;
   private Component C40_f632 = null;
   private IComponent[] C40_f633 = null;
   public byte C40_f634 = -1;
   public byte C40_f635 = -1;
   DialogConfig C40_f636;
   private boolean C40_f637 = true;

   public C40() {
      this.C40_f637 = true;
   }

   public final void a() {
      C35 var1 = new C35(this.C40_f624, this.C40_f625, this.C40_f626, this.C40_f627);
      this.C40_f629.a(var1);
      this.C40_f629.a();
   }

   public final void render(Graphics var1, boolean var2, boolean var3, IComponent var4, int[] var5) {
      if (this.C40_f637) {
         if (this.C40_f629 != null) {
            this.C40_f629.a(var1, this.C40_f624, this.C40_f625, this.C40_f626, this.C40_f627, var2, this.C40_f634, this.C40_f635, this.C40_f636);
         }

      }
   }

   public final void update(boolean var1, boolean var2, IComponent var3, int[] var4) {
      if (this.C40_f629 != null) {
         var2 = var1;
         DialogData var5 = this.C40_f629;
         if (var2) {
            if (var5.C12_f191 != null) {
               var5.C12_f191.b();
               return;
            }
         } else if (var5.C12_f195 != null) {
            var5.C12_f195.b();
         }
      }

   }

   public final void setVisible(boolean var1) {
      this.C40_f637 = var1;
   }

   public final int getSelectedComponentId() {
      return this.C40_f623;
   }

   public final void a(int var1) {
      this.C40_f623 = var1;
   }

   public final int getOffsetX() {
      return this.C40_f624;
   }

   public final void setOffsetX(int var1, IComponent var2) {
      this.C40_f624 = var1;
      this.setActiveComponent(var2);
   }

   public final int getOffsetY() {
      return this.C40_f625;
   }

   public final void setOffsetY(int var1, IComponent var2) {
      this.C40_f625 = var1;
      this.setActiveComponent(var2);
   }

   public final int getWidth() {
      return this.C40_f626;
   }

   public final void setWidth(int var1, IComponent var2) {
      this.C40_f626 = var1;
      this.setActiveComponent(var2);
   }

   public final int getHeight() {
      return this.C40_f627;
   }

   public final void setHeight(int var1, IComponent var2) {
      this.C40_f627 = var1;
      this.setActiveComponent(var2);
   }

   public final Component getChildComponent() {
      return this.C40_f632;
   }

   public final void a(Component var1) {
      this.C40_f632 = var1;
   }

   public final IComponent[] getComponents() {
      return null;
   }

   public final DialogData getComponentData() {
      return this.C40_f629;
   }

   public final void setComponentData(DialogData var1) {
      this.C40_f629 = var1;
   }

   public final int getZIndex() {
      return this.C40_f628;
   }

   public final void b(int var1) {
      this.C40_f628 = var1;
   }

   public final int getActiveComponentId() {
      return this.C40_f630;
   }

   public final void c(int var1) {
      this.C40_f630 = var1;
   }

   public final void setActiveComponent(IComponent var1) {
      if (var1 != null && this.C40_f630 > 0 && this.C40_f631 != 9) {
         var1 = GameUtils.findChildById(var1, this.C40_f630);
         switch(this.C40_f631) {
         case 0:
            this.C40_f624 = var1.getOffsetX();
            this.C40_f625 = var1.getOffsetY();
            return;
         case 1:
            this.C40_f624 = var1.getOffsetX() + (var1.getWidth() - this.C40_f626) / 2;
            this.C40_f625 = var1.getOffsetY();
            this.C40_f626 = var1.getWidth();
            break;
         case 2:
            this.C40_f624 = var1.getOffsetX() + (var1.getWidth() - this.C40_f626);
            this.C40_f625 = var1.getOffsetY();
            return;
         case 3:
            this.C40_f624 = var1.getOffsetX();
            this.C40_f625 = var1.getOffsetY() + (var1.getHeight() - this.C40_f627) / 2;
            this.C40_f627 = var1.getHeight();
            return;
         case 4:
            this.C40_f624 = var1.getOffsetX();
            this.C40_f625 = var1.getOffsetY();
            this.C40_f626 = var1.getWidth();
            this.C40_f627 = var1.getHeight();
            return;
         case 5:
            this.C40_f624 = var1.getOffsetX() + (var1.getWidth() - this.C40_f626);
            this.C40_f625 = var1.getOffsetY() + (var1.getHeight() - this.C40_f627) / 2;
            this.C40_f627 = var1.getHeight();
            return;
         case 6:
            this.C40_f624 = var1.getOffsetX();
            this.C40_f625 = var1.getOffsetY() + (var1.getHeight() - this.C40_f627);
            return;
         case 7:
            this.C40_f624 = var1.getOffsetX() + (var1.getWidth() - this.C40_f626) / 2;
            this.C40_f625 = var1.getOffsetY() + (var1.getHeight() - this.C40_f627);
            this.C40_f626 = var1.getWidth();
            return;
         case 8:
            this.C40_f624 = var1.getOffsetX() + (var1.getWidth() - this.C40_f626);
            this.C40_f625 = var1.getOffsetY() + (var1.getHeight() - this.C40_f627);
            return;
         }
      }

   }

   public final void cleanUp() {
      if (this.C40_f632 != null) {
         this.C40_f632 = null;
      }

      if (this.C40_f629 != null) {
         this.C40_f629.c();
         this.C40_f629 = null;
      }

      if (this.C40_f636 != null) {
         this.C40_f636 = null;
      }

   }
}
