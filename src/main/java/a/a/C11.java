package a.a;

import java.io.InputStream;
import javax.microedition.media.Manager;
import javax.microedition.media.Player;
import javax.microedition.media.PlayerListener;
import javax.microedition.media.control.VolumeControl;
import me.kitakeyos.ManagedInputStream;

public final class C11 {
   private byte[] C11_f166 = new byte[]{0, 20, 40, 60, 80, 100};
   private String C11_f167 = "";
   private byte[][] C11_f168;
   private int C11_f169;
   private int C11_f170;
   private Player[] C11_f171;
   private short C11_f172;
   private Player[] C11_f173;
   private static final String[] C11_f174 = new String[]{"audio/x-tone-seq", "audio/midi", "audio/x-wav", "audio/amr"};
   private VolumeControl C11_f175;
   private byte C11_f176;
   private PlayerListener C11_f177;
   private PlayerListener C11_f178;

   public C11(int var1, int var2, byte var3, String var4) {
      this.C11_f168 = new byte[][]{{2, 2, 0}, {(byte)(this.C11_f166.length - 1), (byte)(this.C11_f166.length - 1), 1}, {-1, -1, -1}, {0, 0, 0}};
      this.C11_f169 = 0;
      this.C11_f170 = 0;
      this.C11_f175 = null;
      this.C11_f176 = 0;
      this.C11_f177 = new C10(this);
      this.C11_f178 = this.C11_f177;
      if (this.C11_f171 == null) {
         this.C11_f172 = 7;
         this.C11_f171 = new Player[7];
      }

      Player[] var10000 = this.C11_f173;
      this.C11_f176 = 0;
      this.C11_f167 = var4;
   }

   public final void a() {
      int var1;
      if (this.C11_f171 != null) {
         for(var1 = 0; var1 < this.C11_f171.length; ++var1) {
            this.C11_f171[var1] = null;
         }
      }

      if (this.C11_f173 != null) {
         for(var1 = 0; var1 < this.C11_f173.length; ++var1) {
            this.C11_f173[var1] = null;
         }
      }

      this.C11_f171 = null;
      this.C11_f173 = null;
   }

   private static Player a(String var0, int var1) {
      try {
         var0.getClass();
         InputStream var3;
         Player var4;
         (var4 = Manager.createPlayer(var3 = ManagedInputStream.openStream(var0), C11_f174[var1])).realize();
         var4.prefetch();
         var4.setLoopCount(-1);
         var3.close();
         return var4;
      } catch (Exception var2) {
         var2.printStackTrace();
         return null;
      }
   }

   public final void a(byte[] var1) {
      for(int var2 = 0; var2 < var1.length; ++var2) {
         if (var1[var2] != -1 && this.C11_f171[var1[var2]] == null) {
            this.C11_f171[var1[var2]] = a((String)(this.C11_f167 + var1[var2] + ".mid"), 1);
         }
      }

   }

   private void a(Player var1, int var2, int var3, boolean var4, boolean var5) {
      if (var1 != null) {
         try {
            if (this.C11_f168[0][var2] != 0) {
               if (this.C11_f176 == 1) {
                  if (var5) {
                     var1.addPlayerListener(this.C11_f177);
                  } else {
                     var1.addPlayerListener(this.C11_f178);
                  }
               }

               var1.prefetch();
               if (var1.getState() == 300) {
                  var1.setLoopCount(var3);
                  if (!var4) {
                     var1.setMediaTime(0L);
                  }

                  this.a((Player)var1, this.C11_f166[this.C11_f168[0][var2]]);
                  var1.start();
                  return;
               }
            } else {
               var1.stop();
            }

            return;
         } catch (Exception var6) {
         }
      }

   }

   private void a(Player var1, boolean var2) {
      if (var1 != null) {
         try {
            var1.stop();
            if (this.C11_f176 == 1) {
               if (var2) {
                  var1.removePlayerListener(this.C11_f177);
                  return;
               }

               var1.removePlayerListener(this.C11_f178);
            }

            return;
         } catch (Exception var3) {
            var3.printStackTrace();
         }
      }

   }

   private void a(Player var1, int var2) {
      try {
         this.C11_f175 = (VolumeControl)var1.getControl("VolumeControl");
         if (var2 == 0) {
            this.a((int)1);
         } else {
            this.C11_f175.setLevel(var2);
            if (a(var1)) {
               var1.start();
            }

         }
      } catch (Exception var3) {
         var3.printStackTrace();
      }
   }

   public final void a(byte var1) {
      if (this.C11_f171 != null) {
         for(int var3 = 0; var3 < this.C11_f171.length; ++var3) {
            Player var2;
            if ((var2 = this.C11_f171[var3]) != null) {
               var2.deallocate();
               var2.close();
            }
         }

      }
   }

   private static boolean a(Player var0) {
      try {
         var0.prefetch();
         if (var0.getState() == 300) {
            return true;
         }
      } catch (Exception var1) {
      }

      return false;
   }

   public final void b(byte var1) {
      if (var1 >= this.C11_f166.length) {
         var1 = (byte)(this.C11_f166.length - 1);
      }

      this.C11_f168[2][1] = (byte)this.C11_f169;
      this.C11_f168[0][1] = var1;
      this.C11_f168[0][0] = var1;
      if (this.C11_f171 != null && this.C11_f171[this.C11_f169] != null) {
         this.a((Player)this.C11_f171[this.C11_f169], this.C11_f166[this.C11_f168[0][1]]);
      }

      if (this.C11_f173 != null) {
         for(int var2 = 0; var2 < this.C11_f173.length; ++var2) {
            if (this.C11_f173[var2] != null) {
               this.a((Player)this.C11_f173[var2], this.C11_f166[this.C11_f168[0][0]]);
            }
         }
      }

   }

   public final void a(int var1, int var2) {
      this.C11_f170 = this.C11_f169;
      this.C11_f169 = var1;
      if (var1 >= 0 && var1 < this.C11_f172) {
         if (this.C11_f168[2][1] == -1 || var1 != this.C11_f168[2][1]) {
            byte var3 = 1;
            if (this.C11_f168[0][var3] == 0) {
               return;
            }

            this.a((int)1);
            if (this.C11_f170 != var1 && this.C11_f171[this.C11_f170] != null) {
               this.C11_f171[this.C11_f170] = null;
            }

            if (this.C11_f171[var1] == null) {
               this.C11_f171[var1] = a((String)(this.C11_f167 + var1 + ".mid"), 1);
            }

            byte var5 = 1;
            byte var4 = -1;
            this.a((int)var5);
            this.C11_f168[2][var5] = (byte)var1;
            this.C11_f168[3][var5] = (byte)var4;
            this.a(this.C11_f171[var1], 1, -1, false, true);
            return;
         }
      } else if (var1 == -2 || var1 == -1) {
         this.a((int)1);
         return;
      }

   }

   private void a(int var1) {
      switch(var1) {
      case 0:
         if (this.C11_f168[2][0] >= 0) {
            this.a(this.C11_f173[this.C11_f168[2][0]], false);
            return;
         }
         break;
      case 1:
         if (this.C11_f168[2][1] >= 0) {
            this.a(this.C11_f171[this.C11_f168[2][1]], true);
         }
      case 2:
      }

   }

   public final void b() {
      this.a((int)0);
      this.a((int)1);
   }

   public final void c() {
      if (this.C11_f168[2][1] != -1) {
         this.a(this.C11_f171[this.C11_f168[2][1]], 1, this.C11_f168[3][1], this.C11_f176 == 0, true);
      }

      if (this.C11_f168[2][0] != -1) {
         this.a(this.C11_f173[this.C11_f168[2][0]], 0, this.C11_f168[3][0], this.C11_f176 == 0, false);
      }

   }

   static byte[][] a(C11 var0) {
      return var0.C11_f168;
   }
}
