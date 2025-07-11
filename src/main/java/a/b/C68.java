package a.b;

import a.GameUtils;
import a.GameEngineBase;
import a.a.C69;
import game.C7;
import java.io.DataInputStream;
import java.io.InputStream;
import javax.microedition.lcdui.Graphics;
import javax.microedition.lcdui.Image;
import me.kitakeyos.ManagedInputStream;

public final class C68 {
   private static C68 C68_f931;
   private static final int[] C68_f932 = new int[]{0, 5, 3, 6, 2, 4, 1, 7};
   private int C68_f933 = -1;
   private int C68_f934 = -1;
   private short C68_f935 = -1;
   private short C68_f936 = -1;
   private short C68_f937 = -1;
   private short C68_f938 = -1;
   private int C68_f939 = -1;
   private byte C68_f940 = -1;
   private byte C68_f941 = -1;
   private Image[] C68_f942 = null;
   public int C68_f943;
   public int C68_f944;
   public int C68_f945;
   public int C68_f946;
   private int C68_f947;
   private int C68_f948;
   private int C68_f949;
   private int C68_f950;
   private byte C68_f951;
   private short[][] C68_f952;
   private short[][][] C68_f953;
   private byte[] C68_f954 = null;

   public static C68 a() {
      if (C68_f931 == null) {
         C68_f931 = new C68();
      }

      return C68_f931;
   }

   public C68() {
      this.C68_f934 = GameEngineBase.getScreenHeight();
      this.C68_f933 = GameEngineBase.getScreenWidth();
   }

   public final void b() {
      this.C68_f953 = null;
      this.C68_f954 = null;
      this.C68_f952 = null;
   }

   public final void a(int var1) {
      this.C68_f939 = var1;
      C68 var12 = this;

      try {
         var12.getClass();
         InputStream var2 = ManagedInputStream.openStream("/data/map/map_" + var12.C68_f939 + ".mid");
         DataInputStream var13;
         byte var3 = (var13 = new DataInputStream(var2)).readByte();
         var12.C68_f941 = var12.C68_f940;
         var12.C68_f940 = var13.readByte();
         var12.e();
         if (var3 == 1) {
            var12.C68_f935 = (short)var13.readByte();
         } else {
            var12.C68_f935 = var13.readShort();
         }

         if (var3 == 1) {
            var12.C68_f936 = (short)var13.readByte();
         } else {
            var12.C68_f936 = var13.readShort();
         }

         var12.C68_f937 = (short)var13.readByte();
         var12.C68_f938 = var12.C68_f937;
         var12.C68_f945 = var12.C68_f935 * var12.C68_f937;
         var12.C68_f946 = var12.C68_f936 * var12.C68_f938;
         var12.C68_f951 = var13.readByte();
         var12.C68_f954 = new byte[var12.C68_f951];
         var12.C68_f953 = new short[var12.C68_f951][][];

         for(int var4 = 0; var4 < var12.C68_f951; ++var4) {
            byte var5 = var13.readByte();
            var12.C68_f954[var4] = var13.readByte();
            short var6 = var13.readShort();
            int var7;
            if (var12.C68_f954[var5] != 0 && var12.C68_f954[var5] != 1) {
               var12.C68_f953[var5] = new short[var6][4];
            } else {
               var12.C68_f953[var5] = new short[var12.C68_f935][var12.C68_f936];

               for(var7 = 0; var7 < var12.C68_f935; ++var7) {
                  for(int var8 = 0; var8 < var12.C68_f936; ++var8) {
                     var12.C68_f953[var5][var7][var8] = -1;
                  }
               }
            }

            for(var7 = 0; var7 < var6; ++var7) {
               short var9;
               short var14;
               if (var3 == 1) {
                  var14 = (short)var13.readByte();
                  var9 = (short)var13.readByte();
               } else {
                  var14 = var13.readShort();
                  var9 = var13.readShort();
               }

               short var10 = var13.readShort();
               if (var12.C68_f954[var4] == 1) {
                  var12.C68_f953[var4][var14][var9] = var10;
               } else if (var12.C68_f954[var5] == 0) {
                  var12.C68_f953[var4][var14][var9] = (short)(var10 & 4095);
               } else {
                  var12.C68_f953[var4][var7][1] = var14;
                  var12.C68_f953[var4][var7][2] = var9;
                  var12.C68_f953[var4][var7][0] = (short)(var10 & 4095);
                  var12.C68_f953[var4][var7][3] = (short)((var10 & 28672) >> 12);
               }
            }
         }

         var13.close();
      } catch (Exception var11) {
         var11.printStackTrace();
      }
   }

   private void e() {
      int var1;
      if (this.C68_f942 != null) {
         if (C7.C7_f51 != 0) {
            for(var1 = 0; var1 < this.C68_f942.length; ++var1) {
               if (this.C68_f942[var1] != null) {
                  this.C68_f942[var1] = null;
               }
            }

            this.C68_f942 = null;
         } else {
            for(var1 = 0; var1 < this.C68_f942.length; ++var1) {
               for(int var2 = 0; var2 < ResourceManager.moduleInfoData[this.C68_f940].length; ++var2) {
                  if (ResourceManager.moduleInfoData[this.C68_f941][var1] == ResourceManager.moduleInfoData[this.C68_f940][var2]) {
                     ResourceManager.releaseImageReference(ResourceManager.moduleInfoData[this.C68_f941][var1]);
                     this.C68_f942[var1] = null;
                     break;
                  }
               }

               if (this.C68_f942[var1] != null) {
                  ResourceManager.forceReleaseImage(ResourceManager.moduleInfoData[this.C68_f941][var1]);
                  this.C68_f942[var1] = null;
               }
            }

            this.C68_f942 = null;
         }
      }

      this.C68_f942 = new Image[ResourceManager.moduleInfoData[this.C68_f940].length];

      for(var1 = 0; var1 < this.C68_f942.length; ++var1) {
         if (C7.C7_f51 == 1) {
            this.C68_f942[var1] = C69.a(ResourceManager.getCachedImage(ResourceManager.moduleInfoData[this.C68_f940][var1]));
         } else {
            this.C68_f942[var1] = ResourceManager.getCachedImage(ResourceManager.moduleInfoData[this.C68_f940][var1]);
         }
      }

      try {
         InputStream var7 = ManagedInputStream.openStream("/data/mod/mod_" + this.C68_f940 + ".mid");
         DataInputStream var6;
         short var3 = (var6 = new DataInputStream(var7)).readShort();
         this.C68_f952 = new short[var3][5];

         for(int var4 = 0; var4 < var3; ++var4) {
            this.C68_f952[var4][0] = (short)var6.readByte();
            this.C68_f952[var4][1] = var6.readShort();
            this.C68_f952[var4][2] = var6.readShort();
            this.C68_f952[var4][3] = var6.readShort();
            this.C68_f952[var4][4] = var6.readShort();
         }

         var6.close();
         var7.close();
      } catch (Exception var5) {
         var5.printStackTrace();
      }
   }

   public final void c() {
      int var1;
      for(var1 = 0; var1 < this.C68_f942.length; ++var1) {
         if (C7.C7_f51 == 1) {
            this.C68_f942[var1] = C69.a(ResourceManager.getCachedImage(ResourceManager.moduleInfoData[this.C68_f940][var1]));
         } else {
            ResourceManager.forceReleaseImage(ResourceManager.moduleInfoData[this.C68_f940][var1]);
         }
      }

      for(var1 = 0; var1 < this.C68_f942.length; ++var1) {
         if (C7.C7_f51 == 0) {
            this.C68_f942[var1] = ResourceManager.getCachedImage(ResourceManager.moduleInfoData[this.C68_f940][var1]);
         }
      }

   }

   public final void d() {
      if (this.C68_f937 != 0) {
         this.C68_f948 = this.C68_f944 / this.C68_f938;
         this.C68_f947 = this.C68_f943 / this.C68_f937;
         this.C68_f950 = this.C68_f934 / this.C68_f938 + 1;
         this.C68_f949 = this.C68_f933 / this.C68_f937 + 1;
         if (this.C68_f948 + this.C68_f950 >= this.C68_f936) {
            this.C68_f950 = this.C68_f936 - 1 - this.C68_f948;
         }

         if (this.C68_f949 + this.C68_f947 >= this.C68_f935) {
            this.C68_f949 = this.C68_f935 - 1 - this.C68_f947;
         }

      }
   }

   public final void a(Graphics var1, int var2) {
      int var3;
      int var4;
      int var5;
      short var6;
      C68 var8;
      Graphics var9;
      switch(this.C68_f954[var2]) {
      case 0:
         var3 = var2;
         var9 = var1;
         var8 = this;

         for(var4 = 0; var4 <= var8.C68_f949; ++var4) {
            for(var5 = 0; var5 <= var8.C68_f950; ++var5) {
               if ((var6 = var8.C68_f953[var3][var8.C68_f947 + var4][var8.C68_f948 + var5]) != -1) {
                  var9.drawRegion(var8.C68_f942[0], var8.C68_f952[var6][1], var8.C68_f952[var6][2], var8.C68_f952[var6][3], var8.C68_f952[var6][4], 0, var4 * var8.C68_f937 - var8.C68_f943 % var8.C68_f937, var5 * var8.C68_f938 - var8.C68_f944 % var8.C68_f938, 20);
               }
            }
         }

         return;
      case 1:
         var3 = var2;
         var9 = var1;
         var8 = this;

         for(var4 = 0; var4 <= var8.C68_f949; ++var4) {
            for(var5 = 0; var5 <= var8.C68_f950; ++var5) {
               if ((var6 = var8.C68_f953[var3][var8.C68_f947 + var4][var8.C68_f948 + var5]) != -1) {
                  short var7 = (short)(var6 & 4095);
                  var6 = (short)C68_f932[(var6 & 28672) >> 12];
                  var9.drawRegion(var8.C68_f942[var8.C68_f952[var7][0]], var8.C68_f952[var7][1], var8.C68_f952[var7][2], var8.C68_f952[var7][3], var8.C68_f952[var7][4], var6, var4 * var8.C68_f937 - var8.C68_f943 % var8.C68_f937, var5 * var8.C68_f938 - var8.C68_f944 % var8.C68_f938, 20);
               }
            }
         }

         return;
      case 2:
      case 3:
      case 4:
         var3 = var2;
         var9 = var1;
         var8 = this;

         for(var4 = 0; var4 < var8.C68_f953[var3].length; ++var4) {
            if (var8.C68_f953[var3][var4][2] >= 0 && GameUtils.checkCollision(var8.C68_f953[var3][var4][1], var8.C68_f953[var3][var4][2], var8.C68_f952[var8.C68_f953[var3][var4][0]][3], var8.C68_f952[var8.C68_f953[var3][var4][0]][4], var8.C68_f947, var8.C68_f948, var8.C68_f949 + 1, var8.C68_f950 + 1, var8.C68_f953[var3][var4][3])) {
               var9.drawRegion(var8.C68_f942[var8.C68_f952[var8.C68_f953[var3][var4][0]][0]], var8.C68_f952[var8.C68_f953[var3][var4][0]][1], var8.C68_f952[var8.C68_f953[var3][var4][0]][2], var8.C68_f952[var8.C68_f953[var3][var4][0]][3], var8.C68_f952[var8.C68_f953[var3][var4][0]][4], C68_f932[var8.C68_f953[var3][var4][3]], (var8.C68_f953[var3][var4][1] - var8.C68_f947) * var8.C68_f937 - var8.C68_f943 % var8.C68_f937, (var8.C68_f953[var3][var4][2] - var8.C68_f948) * var8.C68_f938 - var8.C68_f944 % var8.C68_f938, 20);
            }
         }
      default:
      }
   }

   public final void a(int var1, int var2) {
      this.C68_f943 = var1 - this.C68_f933 / 2;
      this.C68_f944 = var2 - this.C68_f934 / 2;
      if (this.C68_f943 + this.C68_f933 >= this.C68_f935 * this.C68_f937) {
         this.C68_f943 = this.C68_f935 * this.C68_f937 - this.C68_f933;
      }

      if (this.C68_f943 <= 0) {
         this.C68_f943 = 0;
      }

      if (this.C68_f944 + this.C68_f934 >= this.C68_f936 * this.C68_f938) {
         this.C68_f944 = this.C68_f936 * this.C68_f938 - this.C68_f934;
      }

      if (this.C68_f944 <= 0) {
         this.C68_f944 = 0;
      }

   }

   public final byte a(int var1, int var2, int var3) {
      if (this.C68_f953 != null && this.C68_f953[0] != null) {
         var1 = var2 / this.C68_f937;
         int var4 = var3 / this.C68_f938;
         if (this.b(var2, var3)) {
            return 1;
         } else {
            if (var4 < 0) {
               var4 = 0;
            }

            if (var1 < 0) {
               var1 = 0;
            }

            if (var1 > this.C68_f935) {
               var1 = this.C68_f935;
            }

            if (var4 > this.C68_f936) {
               var4 = this.C68_f936;
            }

            return (byte)this.C68_f953[0][var1][var4];
         }
      } else {
         return -1;
      }
   }

   public final boolean b(int var1, int var2) {
      return var1 <= 0 || var1 >= this.C68_f945 || var2 <= 0 || var2 >= this.C68_f946;
   }
}
