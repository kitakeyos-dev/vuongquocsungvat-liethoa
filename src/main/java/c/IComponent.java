package c;

import javax.microedition.lcdui.Graphics;

public interface IComponent {
   void setVisible(boolean var1);

   void render(Graphics var1, boolean var2, boolean var3, IComponent var4, int[] var5);

   void update(boolean var1, boolean var2, IComponent var3, int[] var4);

   int getSelectedComponentId();

   int getOffsetX();

   void setOffsetX(int var1, IComponent var2);

   int getOffsetY();

   void setOffsetY(int var1, IComponent var2);

   int getWidth();

   void setWidth(int var1, IComponent var2);

   int getHeight();

   void setHeight(int var1, IComponent var2);

   Component getChildComponent();

   IComponent[] getComponents();

   DialogData getComponentData();

   void setComponentData(DialogData var1);

   int getZIndex();

   int getActiveComponentId();

   void setActiveComponent(IComponent var1);

   void cleanUp();
}
