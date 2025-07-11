package a.a;

import a.GameEngineBase;
import a.b.C6;
import a.b.C60;
import a.b.ResourceManager;
import a.b.C68;
import game.C18;
import game.C25;
import game.C53;
import java.util.Vector;
import javax.microedition.lcdui.Graphics;

public final class C46 {
   private C68 C46_f722;
   private Vector C46_f723 = new Vector();
   private Vector C46_f724 = new Vector();
   private Vector C46_f725 = new Vector();
   private C6 C46_f726;

   public final void a(C68 var1) {
      this.C46_f722 = var1;
   }

   public final void a(C60 var1) {
      switch(var1.C60_f871) {
      case 0:
         this.C46_f723.addElement(var1);
         return;
      case 1:
         this.C46_f724.addElement(var1);
         return;
      case 2:
         this.C46_f725.addElement(var1);
      default:
      }
   }

   public final void b(C60 var1) {
      switch(var1.C60_f871) {
      case 0:
         this.C46_f723.removeElement(var1);
         return;
      case 1:
         this.C46_f724.removeElement(var1);
         return;
      case 2:
         this.C46_f725.removeElement(var1);
      default:
      }
   }

   public final void a(C60 var1, int var2) {
      this.b(var1);
      this.C46_f725.addElement(var1);
   }

   public final void a(C6 var1) {
      this.C46_f726 = var1;
   }

   public final void a() {
      this.C46_f722 = null;
      this.C46_f726 = null;
      this.C46_f724.removeAllElements();
      this.C46_f723.removeAllElements();
      this.C46_f725.removeAllElements();
   }

   public final void b() {
      this.C46_f726.c();
      this.C46_f722.a(this.C46_f726.C60_f861, this.C46_f726.C60_f862);
      this.C46_f722.d();

      int var1;
      int var2;
      Object var3;
      for(var1 = 0; var1 < this.C46_f723.size(); ++var1) {
         for(var2 = 0; var2 < this.C46_f723.size() - var1 - 1; ++var2) {
            if (((C60)this.C46_f723.elementAt(var2)).C60_f862 > ((C60)this.C46_f723.elementAt(var2 + 1)).C60_f862) {
               var3 = this.C46_f723.elementAt(var2);
               this.C46_f723.setElementAt(this.C46_f723.elementAt(var2 + 1), var2);
               this.C46_f723.setElementAt(var3, var2 + 1);
            }
         }
      }

      for(var1 = 0; var1 < this.C46_f723.size(); ++var1) {
         ((C20)this.C46_f723.elementAt(var1)).a();
      }

      for(var1 = 0; var1 < this.C46_f724.size(); ++var1) {
         for(var2 = 0; var2 < this.C46_f724.size() - var1 - 1; ++var2) {
            if (((C60)this.C46_f724.elementAt(var2)).C60_f862 > ((C60)this.C46_f724.elementAt(var2 + 1)).C60_f862) {
               var3 = this.C46_f724.elementAt(var2);
               this.C46_f724.setElementAt(this.C46_f724.elementAt(var2 + 1), var2);
               this.C46_f724.setElementAt(var3, var2 + 1);
            }
         }
      }

      for(var1 = 0; var1 < this.C46_f724.size(); ++var1) {
         ((C20)this.C46_f724.elementAt(var1)).a();
         if (this.C46_f724.elementAt(var1) instanceof C18) {
            if (((C18)this.C46_f724.elementAt(var1)).C18_f246 != null && ((C18)this.C46_f724.elementAt(var1)).C18_f246.j()) {
               ((C18)this.C46_f724.elementAt(var1)).C18_f246.a();
            } else if (((C18)this.C46_f724.elementAt(var1)).C18_f247 != null && ((C18)this.C46_f724.elementAt(var1)).C18_f247.j()) {
               ((C18)this.C46_f724.elementAt(var1)).C18_f247.a();
            }
         }
      }

      for(var1 = 0; var1 < this.C46_f725.size(); ++var1) {
         for(var2 = 0; var2 < this.C46_f725.size() - var1 - 1; ++var2) {
            if (((C60)this.C46_f725.elementAt(var2)).C60_f862 > ((C60)this.C46_f725.elementAt(var2 + 1)).C60_f862) {
               var3 = this.C46_f725.elementAt(var2);
               this.C46_f725.setElementAt(this.C46_f725.elementAt(var2 + 1), var2);
               this.C46_f725.setElementAt(var3, var2 + 1);
            }
         }
      }

      for(var1 = 0; var1 < this.C46_f725.size(); ++var1) {
         ((C20)this.C46_f725.elementAt(var1)).a();
      }

   }

   public final void a(Graphics var1) {
      int var2;
      for(var2 = 0; var2 < this.C46_f724.size(); ++var2) {
         ((C20)this.C46_f724.elementAt(var2)).b(var1, this.C46_f722.C68_f943, this.C46_f722.C68_f944);
      }

      this.C46_f722.a(var1, 1);
      this.C46_f722.a(var1, 2);

      for(var2 = 0; var2 < this.C46_f725.size(); ++var2) {
         if (((C20)this.C46_f725.elementAt(var2)).l()) {
            ((C20)this.C46_f725.elementAt(var2)).a(var1, this.C46_f722.C68_f943, this.C46_f722.C68_f944);
         }
      }

      if (C25.B().C25_f286.C53_f793[2] == 2) {
         for(var2 = 0; var2 < this.C46_f724.size(); ++var2) {
            if (((C20)this.C46_f724.elementAt(var2)).l()) {
               if (this.C46_f724.elementAt(var2) instanceof C53) {
                  continue;
               }

               ((C20)this.C46_f724.elementAt(var2)).a(var1, this.C46_f722.C68_f943, this.C46_f722.C68_f944);
            }

            if (this.C46_f724.elementAt(var2) instanceof C18 && ((C18)this.C46_f724.elementAt(var2)).C18_f225 == 14) {
               ((C18)this.C46_f724.elementAt(var2)).c(var1, this.C46_f722.C68_f943, this.C46_f722.C68_f944);
            }

            if (this.C46_f724.elementAt(var2) instanceof C18) {
               if (((C18)this.C46_f724.elementAt(var2)).C18_f246 != null && ((C18)this.C46_f724.elementAt(var2)).C18_f246.k()) {
                  ((C18)this.C46_f724.elementAt(var2)).C18_f246.a(var1, this.C46_f722.C68_f943, this.C46_f722.C68_f944);
               } else if (((C18)this.C46_f724.elementAt(var2)).C18_f247 != null && ((C18)this.C46_f724.elementAt(var2)).C18_f247.k()) {
                  ((C18)this.C46_f724.elementAt(var2)).C18_f247.a(var1, this.C46_f722.C68_f943, this.C46_f722.C68_f944);
               }
            }
         }

         if (C68.a().a(0, C53.p().C60_f861, C53.p().C60_f862) != 1 && C25.B().C25_f286.C20_f262 != null && C25.B().C25_f286.k()) {
            C25.B().C25_f286.C20_f262.a(var1, this.C46_f722.C68_f943, this.C46_f722.C68_f944);
         }

         C25.B().C25_f286.a(var1, this.C46_f722.C68_f943, this.C46_f722.C68_f944);
      } else {
         for(var2 = 0; var2 < this.C46_f724.size(); ++var2) {
            if (((C20)this.C46_f724.elementAt(var2)).l()) {
               if (((C20)this.C46_f724.elementAt(var2)).C20_f262 != null && ((C20)this.C46_f724.elementAt(var2)).k()) {
                  ((C20)this.C46_f724.elementAt(var2)).C20_f262.a(var1, this.C46_f722.C68_f943, this.C46_f722.C68_f944);
               }

               ((C20)this.C46_f724.elementAt(var2)).a(var1, this.C46_f722.C68_f943, this.C46_f722.C68_f944);
            }

            if (this.C46_f724.elementAt(var2) instanceof C18 && ((C18)this.C46_f724.elementAt(var2)).C18_f225 == 14) {
               ((C18)this.C46_f724.elementAt(var2)).c(var1, this.C46_f722.C68_f943, this.C46_f722.C68_f944);
            }

            if (this.C46_f724.elementAt(var2) instanceof C18) {
               if (((C18)this.C46_f724.elementAt(var2)).C18_f246 != null && ((C18)this.C46_f724.elementAt(var2)).C18_f246.k()) {
                  ((C18)this.C46_f724.elementAt(var2)).C18_f246.a(var1, this.C46_f722.C68_f943, this.C46_f722.C68_f944);
               } else if (((C18)this.C46_f724.elementAt(var2)).C18_f247 != null && ((C18)this.C46_f724.elementAt(var2)).C18_f247.k()) {
                  ((C18)this.C46_f724.elementAt(var2)).C18_f247.a(var1, this.C46_f722.C68_f943, this.C46_f722.C68_f944);
               }
            }
         }
      }

      this.C46_f722.a(var1, 3);

      for(var2 = 0; var2 < this.C46_f723.size(); ++var2) {
         if (((C20)this.C46_f723.elementAt(var2)).l()) {
            ((C20)this.C46_f723.elementAt(var2)).a(var1, this.C46_f722.C68_f943, this.C46_f722.C68_f944);
         }
      }

   }

   public final void b(Graphics var1) {
      C25.B();
      C25.b(var1);
      this.C46_f722.a(var1, 1);
      this.C46_f722.a(var1, 2);

      int var2;
      for(var2 = 0; var2 < this.C46_f725.size(); ++var2) {
         if (((C20)this.C46_f725.elementAt(var2)).l() && this.C46_f725.elementAt(var2) instanceof C18 && ((C18)this.C46_f725.elementAt(var2)).C18_f225 == 0) {
            ((C20)this.C46_f725.elementAt(var2)).a(var1, this.C46_f722.C68_f943, this.C46_f722.C68_f944);
         }
      }

      for(var2 = 0; var2 < this.C46_f724.size(); ++var2) {
         if (((C20)this.C46_f724.elementAt(var2)).l() && this.C46_f724.elementAt(var2) instanceof C18 && ((C18)this.C46_f724.elementAt(var2)).C18_f225 == 0) {
            ((C20)this.C46_f724.elementAt(var2)).a(var1, this.C46_f722.C68_f943, this.C46_f722.C68_f944);
         }
      }

      this.C46_f722.a(var1, 3);

      for(var2 = 0; var2 < this.C46_f723.size(); ++var2) {
         if (((C20)this.C46_f723.elementAt(var2)).l() && this.C46_f723.elementAt(var2) instanceof C18 && ((C18)this.C46_f723.elementAt(var2)).C18_f225 == 0) {
            ((C20)this.C46_f723.elementAt(var2)).a(var1, this.C46_f722.C68_f943, this.C46_f722.C68_f944);
         }
      }

      for(var2 = 0; var2 < GameEngineBase.getScreenWidth() / ResourceManager.backgroundImage.getWidth(); ++var2) {
         for(int var3 = 0; var3 < GameEngineBase.getScreenHeight() / ResourceManager.backgroundImage.getHeight(); ++var3) {
            var1.drawImage(ResourceManager.backgroundImage, var2 * ResourceManager.backgroundImage.getWidth(), var3 * ResourceManager.backgroundImage.getHeight(), 20);
         }
      }

   }
}
