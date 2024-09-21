package c;

import a.GameUtils;
import javax.microedition.lcdui.Graphics;

public final class RootComponent implements IComponent {
   private int selectedComponentId = -1;
   private int offsetX = 0;
   private int offsetY = 0;
   private int width = 0;
   private int height = 0;
   private int zIndex = 0;
   private byte[][] dialogData = null;
   private DialogData componentData = new DialogData();
   private int activeComponentId = -1;
   private byte alignment = 9;
   private Component childComponent = null;
   private IComponent[] components = new IComponent[60];
   public Component otherChildComponent = null;
   public Component additionalChildComponent = null;
   private boolean isVisible = true;

   public RootComponent() {
      this.isVisible = true;
   }

   public final void setVisible(boolean var1) {
      this.isVisible = var1;
   }

   public final void setDialogData(byte[][] var1) {
      this.dialogData = var1;
   }

   public final byte[][] getDialogData() {
      return this.dialogData;
   }

   public final void render(Graphics var1, boolean var2, boolean var3, IComponent var4, int[] var5) {
      if (this.isVisible) {
         for(int var6 = 0; var6 < this.components.length && this.components[var6] != null; ++var6) {
            if (this.components[var6].getChildComponent() != null) {
               boolean var7 = false;
               int var8;
               if ((var8 = GameUtils.findFirstAvailableSlot(var5)) > 0 && var5[var8 - 1] == this.selectedComponentId) {
                  var7 = true;
               }

               this.components[var6].getChildComponent().a(var1, this.components[var6].getSelectedComponentId(), var7, var5, var3, var4);
            } else {
               this.components[var6].render(var1, var2, var3, var4, var5);
            }
         }

      }
   }

   public final void update(boolean var1, boolean var2, IComponent var3, int[] var4) {
      for(int var5 = 0; var5 < this.components.length && this.components[var5] != null; ++var5) {
         if (this.components[var5].getChildComponent() != null) {
            if (GameUtils.findFirstAvailableSlot(var4) > 0) {
               int var10000 = this.selectedComponentId;
            }

            this.components[var5].getChildComponent().update(this.components[var5].getSelectedComponentId(), var4, var2, var3);
         } else {
            this.components[var5].update(var1, var2, var3, var4);
         }
      }

   }

   public final int getSelectedComponentId() {
      return this.selectedComponentId;
   }

   public final void setSelectedComponentId(int var1) {
      this.selectedComponentId = var1;
   }

   public final int getOffsetX() {
      return this.offsetX;
   }

   public final void setOffsetX(int var1, IComponent var2) {
      this.offsetX = var1;
      this.setActiveComponent(var2);
   }

   public final int getOffsetY() {
      return this.offsetY;
   }

   public final void setOffsetY(int var1, IComponent var2) {
      this.offsetY = var1;
      this.setActiveComponent(var2);
   }

   public final int getWidth() {
      return this.width;
   }

   public final void setWidth(int var1, IComponent var2) {
      this.width = var1;
      this.setActiveComponent(var2);
   }

   public final int getHeight() {
      return this.height;
   }

   public final void setHeight(int var1, IComponent var2) {
      this.height = var1;
      this.setActiveComponent(var2);
   }

   public final Component getChildComponent() {
      return this.childComponent;
   }

   public final void setChildComponent(Component var1) {
      this.childComponent = var1;
   }

   public final IComponent[] getComponents() {
      return this.components;
   }

   public final DialogData getComponentData() {
      return this.componentData;
   }

   public final void setComponentData(DialogData var1) {
      this.componentData = var1;
   }

   public final int getZIndex() {
      return this.zIndex;
   }

   public final void setZIndex(int var1) {
      this.zIndex = var1;
   }

   public final int getActiveComponentId() {
      return this.activeComponentId;
   }

   public final void setActiveComponentId(int var1) {
      this.activeComponentId = var1;
   }

   public final void setActiveComponent(IComponent var1) {
      if (var1 != null) {
         if (this.activeComponentId > 0 && this.alignment != 9) {
            IComponent var2 = GameUtils.findChildById(var1, this.activeComponentId);
            switch(this.alignment) {
            case 0:
               this.offsetX = var2.getOffsetX();
               this.offsetY = var2.getOffsetY();
               break;
            case 1:
               this.offsetX = var2.getOffsetX() + (var2.getWidth() - this.width) / 2;
               this.offsetY = var2.getOffsetY();
               this.width = var2.getWidth();
               break;
            case 2:
               this.offsetX = var2.getOffsetX() + (var2.getWidth() - this.width);
               this.offsetY = var2.getOffsetY();
               break;
            case 3:
               this.offsetX = var2.getOffsetX();
               this.offsetY = var2.getOffsetY() + (var2.getHeight() - this.height) / 2;
               this.height = var2.getHeight();
               break;
            case 4:
               this.offsetX = var2.getOffsetX();
               this.offsetY = var2.getOffsetY();
               this.width = var2.getWidth();
               this.height = var2.getHeight();
               break;
            case 5:
               this.offsetX = var2.getOffsetX() + (var2.getWidth() - this.width);
               this.offsetY = var2.getOffsetY() + (var2.getHeight() - this.height) / 2;
               this.height = var2.getHeight();
               break;
            case 6:
               this.offsetX = var2.getOffsetX();
               this.offsetY = var2.getOffsetY() + (var2.getHeight() - this.height);
               break;
            case 7:
               this.offsetX = var2.getOffsetX() + (var2.getWidth() - this.width) / 2;
               this.offsetY = var2.getOffsetY() + (var2.getHeight() - this.height);
               this.width = var2.getWidth();
               break;
            case 8:
               this.offsetX = var2.getOffsetX() + (var2.getWidth() - this.width);
               this.offsetY = var2.getOffsetY() + (var2.getHeight() - this.height);
            }
         }

         if (this.components != null) {
            for(int var3 = 0; var3 < this.components.length && this.components[var3] != null; ++var3) {
               this.components[var3].setActiveComponent(var1);
            }
         }
      }

   }

   public final void cleanUp() {
      if (this.additionalChildComponent != null) {
         this.additionalChildComponent.a();
         this.additionalChildComponent = null;
      }

      if (this.otherChildComponent != null) {
         this.otherChildComponent.a();
         this.otherChildComponent = null;
      }

      if (this.components != null) {
         for(int var1 = 0; var1 < this.components.length; ++var1) {
            if (this.components[var1] != null) {
               this.components[var1].cleanUp();
            }

            this.components[var1] = null;
         }

         this.components = null;
      }

      if (this.childComponent != null) {
         this.childComponent = null;
      }

      if (this.dialogData != null) {
         this.dialogData = null;
      }

      if (this.componentData != null) {
         this.componentData.c();
         this.componentData = null;
      }

   }
}
