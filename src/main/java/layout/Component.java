// Decompiled with: Procyon 0.6.0
// Class Version: 3
package layout;

import javax.microedition.lcdui.Graphics;
import a.GameUtils;
import java.util.Vector;

public final class Component
{
   public int C38_f574;
   public int[] C38_f575;
   public int[][][] C38_f576;
   public int C38_f577;
   private Rectangle[] C38_f578;
   public int C38_f579;
   public int C38_f580;
   public boolean C38_f581;
   public int C38_f582;
   public int C38_f583;
   public int C38_f584;
   public int C38_f585;
   public int C38_f586;
   public int C38_f587;
   private int C38_f588;
   public int[][] C38_f589;
   private int C38_f590;
   private Vector C38_f591;
   public Vector C38_f592;

   public Component(final int c38_f584) {
      this.C38_f582 = 0;
      this.C38_f583 = 0;
      this.C38_f585 = 0;
      this.C38_f586 = 10;
      this.C38_f587 = 2;
      this.C38_f588 = -1;
      this.C38_f589 = null;
      this.C38_f590 = -1;
      this.C38_f591 = new Vector();
      this.C38_f592 = new Vector();
      this.C38_f574 = 0;
      this.C38_f575 = null;
      final int[] c38_f585 = this.C38_f575;
      this.C38_f575 = GameUtils.initializeArrayWithMinusOne(50);
      this.C38_f577 = 0;
      this.C38_f578 = new Rectangle[20];
      this.C38_f579 = 0;
      this.C38_f580 = 0;
      this.C38_f581 = true;
      this.C38_f584 = c38_f584;
      this.C38_f588 = -1;
   }

   public final void a(final int n, final IComponent c23) {
      if (this.C38_f574 <= 1) {
         this.C38_f580 = 0;
         this.C38_f579 = 0;
         return;
      }
      if (this.C38_f581) {
         if (this.C38_f582 == 0) {
            this.C38_f580 += n;
            if (this.C38_f580 >= this.C38_f574) {
               this.C38_f580 %= this.C38_f574;
               if (this.C38_f580 >= this.C38_f579 + this.C38_f577 || this.C38_f580 < this.C38_f579) {
                  this.C38_f579 = this.C38_f580;
               }
               this.a(c23);
               return;
            }
            if (this.C38_f580 >= this.C38_f579 + this.C38_f577) {
               this.C38_f579 += n;
               if (this.C38_f579 + this.C38_f577 >= this.C38_f574) {
                  this.C38_f579 = this.C38_f574 - this.C38_f577;
               }
               this.a(c23);
            }
         }
         else if (this.C38_f582 == 1) {
            this.C38_f580 += n;
            if (this.C38_f580 >= this.C38_f574) {
               this.C38_f580 %= this.C38_f574;
            }
            this.C38_f579 = ((this.C38_f580 - this.C38_f583 < 0) ? (this.C38_f574 + (this.C38_f580 - this.C38_f583)) : (this.C38_f580 - this.C38_f583));
            this.a(c23);
         }
      }
      else {
         this.C38_f580 += n;
         if (this.C38_f580 >= this.C38_f574) {
            this.C38_f580 = this.C38_f574 - 1;
            if (this.C38_f574 >= this.C38_f577) {
               this.C38_f579 = this.C38_f574 - this.C38_f577;
            }
            this.a(c23);
            return;
         }
         if (this.C38_f580 >= this.C38_f579 + this.C38_f577) {
            this.C38_f579 += n;
            this.a(c23);
         }
      }
   }

   public final void b(final int n, final IComponent c23) {
      if (this.C38_f574 <= 1) {
         this.C38_f580 = 0;
         this.C38_f579 = 0;
         return;
      }
      if (this.C38_f581) {
         if (this.C38_f582 == 0) {
            this.C38_f580 -= n;
            if (this.C38_f580 < 0) {
               this.C38_f580 = this.C38_f574 + this.C38_f580 % this.C38_f574;
               if (this.C38_f580 >= this.C38_f579 + this.C38_f577 || this.C38_f580 < this.C38_f579) {
                  this.C38_f579 = this.C38_f574 - this.C38_f577 - (this.C38_f574 - this.C38_f580 - 1);
               }
               this.a(c23);
               return;
            }
            if (this.C38_f580 < this.C38_f579) {
               this.C38_f579 = this.C38_f580;
               this.a(c23);
            }
         }
         else if (this.C38_f582 == 1) {
            this.C38_f580 -= n;
            if (this.C38_f580 < 0) {
               this.C38_f580 = this.C38_f574 + this.C38_f580 % this.C38_f574;
            }
            this.C38_f579 = ((this.C38_f580 - this.C38_f583 < 0) ? (this.C38_f574 + (this.C38_f580 - this.C38_f583)) : (this.C38_f580 - this.C38_f583));
            this.a(c23);
         }
      }
      else {
         this.C38_f580 -= n;
         if (this.C38_f580 < 0) {
            this.C38_f580 = 0;
            this.C38_f579 = 0;
            this.a(c23);
            return;
         }
         if (this.C38_f580 < this.C38_f579) {
            this.C38_f579 -= n;
            this.a(c23);
         }
      }
   }

   private void a(final IComponent c23) {
      if (this.C38_f588 == 1) {
         if (this.C38_f590 == 1) {
            for (int i = 0; i < this.C38_f577; ++i) {
                this.C38_f591.elementAt((this.C38_f579 + i) % this.C38_f574);
            }
            return;
         }
         if (this.C38_f590 == 2) {
            for (int k = 0; k < this.C38_f577; ++k) {
                this.C38_f591.elementAt((this.C38_f579 + k) % this.C38_f574);
            }
         }
      }
   }

   public final void a(final Graphics graphics, final int n, final boolean b, final int[] array, final boolean b2, final IComponent c23) {
      if (this.C38_f578 != null) {
         this.C38_f578 = new Rectangle[20];
         for (int i = 0; i < this.C38_f577; ++i) {
            final IComponent a = GameUtils.findChildById(c23, this.C38_f575[i]);
            this.C38_f578[i] = new Rectangle(a.getOffsetX(), a.getOffsetY(), a.getWidth(), a.getHeight());
         }
      }
      int n2 = -1;
      for (int n3 = 0; n3 < this.C38_f575.length && this.C38_f575[n3] != -1; ++n3) {
         if (this.C38_f575[n3] == n) {
            n2 = n3;
            break;
         }
      }
      final int[] c24 = GameUtils.initializeArrayWithMinusOne(50);
      if (this.C38_f588 == 1) {
         for (int j = 0; j < this.C38_f577; ++j) {
            c24[j] = j;
         }
      }
      else if (this.C38_f588 == -1) {
         for (int k = 0; k < this.C38_f577; ++k) {
            c24[k] = (this.C38_f579 + k) % this.C38_f575.length;
         }
      }
      for (int n4 = 0; n4 < c24.length && c24[n4] != -1; ++n4) {
         if (n2 == c24[n4]) {
            final IComponent a2;
            final int n5 = (a2 = GameUtils.findChildById(c23, n)).getOffsetX() - this.C38_f578[n4].x;
            final int n6 = a2.getOffsetY() - this.C38_f578[n4].y;
            final int e = a2.getWidth();
            final int f = a2.getHeight();
            GameUtils.a(a2, -n5, -n6, c23);
            a2.setWidth(this.C38_f578[n4].width, c23);
            a2.setHeight(this.C38_f578[n4].height, c23);
            if (b && this.C38_f574 > 0) {
               if (this.C38_f588 == 1) {
                  if (this.C38_f580 == (this.C38_f579 + c24[n4]) % this.C38_f574) {
                     a2.render(graphics, true, b2, c23, array);
                  }
                  else {
                     a2.render(graphics, false, b2, c23, array);
                  }
               }
               else if (this.C38_f580 == c24[n4]) {
                  a2.render(graphics, true, b2, c23, array);
               }
               else {
                  a2.render(graphics, false, b2, c23, array);
               }
            }
            else {
               a2.render(graphics, false, b2, c23, array);
            }
            GameUtils.a(a2, n5, n6, c23);
            a2.setWidth(e, c23);
            a2.setHeight(f, c23);
         }
      }
   }

   public final void update(final int n, final int[] array, final boolean b, final IComponent c23) {
      if (this.C38_f578 != null) {
         this.C38_f578 = new Rectangle[20];
         for (int i = 0; i < this.C38_f577; ++i) {
            final IComponent a = GameUtils.findChildById(c23, this.C38_f575[i]);
            this.C38_f578[i] = new Rectangle(a.getOffsetX(), a.getOffsetY(), a.getWidth(), a.getHeight());
         }
      }
      int n2 = -1;
      for (int n3 = 0; n3 < this.C38_f575.length && this.C38_f575[n3] != -1; ++n3) {
         if (this.C38_f575[n3] == n) {
            n2 = n3;
            break;
         }
      }
      final int[] c24 = GameUtils.initializeArrayWithMinusOne(50);
      if (this.C38_f588 == 1) {
         for (int j = 0; j < this.C38_f577; ++j) {
            c24[j] = j;
         }
      }
      else if (this.C38_f588 == -1) {
         for (int k = 0; k < this.C38_f577; ++k) {
            c24[k] = (this.C38_f579 + k) % this.C38_f575.length;
         }
      }
      for (int n4 = 0; n4 < c24.length && c24[n4] != -1; ++n4) {
         if (n2 == c24[n4]) {
            final IComponent a2;
            final int n5 = (a2 = GameUtils.findChildById(c23, n)).getOffsetX() - this.C38_f578[n4].x;
            final int n6 = a2.getOffsetY() - this.C38_f578[n4].y;
            final int e = a2.getWidth();
            final int f = a2.getHeight();
            GameUtils.a(a2, -n5, -n6, c23);
            a2.setWidth(this.C38_f578[n4].width, c23);
            a2.setHeight(this.C38_f578[n4].height, c23);
            a2.update(b, b, c23, array);
            GameUtils.a(a2, n5, n6, c23);
            a2.setWidth(e, c23);
            a2.setHeight(f, c23);
         }
      }
   }

   public final void a() {
      if (this.C38_f592 != null) {
         this.C38_f592 = null;
      }
      if (this.C38_f575 != null) {
         this.C38_f575 = null;
      }
      if (this.C38_f576 != null) {
         this.C38_f576 = null;
      }
      if (this.C38_f591 != null) {
         this.C38_f591 = null;
      }
      if (this.C38_f578 != null) {
         this.C38_f578 = null;
      }
   }

   public final void a(final int c38_f588) {
      if (c38_f588 != 1 && c38_f588 != -1) {
         this.C38_f588 = -1;
         return;
      }
      this.C38_f588 = c38_f588;
   }
}
