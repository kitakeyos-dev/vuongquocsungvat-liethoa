package game;

import a.GameUtils;
import a.GameEngineBase;
import a.a.C20;
import a.a.C30;
import a.b.C6;
import a.b.C60;
import a.b.C67;
import a.b.C68;
import b.C57;
import b.C70;
import java.io.DataInputStream;
import java.io.InputStream;
import java.util.Calendar;
import java.util.TimeZone;
import java.util.Vector;
import javax.microedition.lcdui.Graphics;
import javax.microedition.lcdui.Image;

public final class C7 extends GameEngineBase {
   public static byte C7_f51;
   private static C7 C7_f52 = null;
   private C25 C7_f53;
   private C53 C7_f54;
   private GameEngineBase C7_f55;
   public C57[] C7_f56;
   private Vector C7_f57;
   private byte C7_f58 = -1;
   public byte C7_f59 = -1;
   public byte[][] C7_f60;
   private int C7_f61 = 0;
   public int C7_f62 = -1;
   private static Vector C7_f63;
   public static Vector C7_f64;
   private C39 C7_f65 = C39.a();
   public static boolean C7_f66 = false;
   public static boolean C7_f67 = true;
   public static boolean C7_f68 = false;
   public static boolean C7_f69 = false;
   public boolean C7_f70 = true;
   public static boolean C7_f71 = true;
   public static boolean C7_f72;
   public boolean C7_f73 = true;
   public byte C7_f74 = 0;
   public static byte C7_f75 = -1;
   private short[] C7_f76;
   private byte[] C7_f77;
   private short[] C7_f78;
   private short[] C7_f79;
   private short[][] C7_f80;
   private short[][] C7_f81;
   private short[] C7_f82;
   private short[] C7_f83;
   public byte C7_f84 = 0;
   public static Image C7_f85;
   private String[] C7_f86 = new String[]{"ikon_1", "ikon_2", "ikon_3", "ikon_4", "ikon_5"};
   private int C7_f87;
   private int C7_f88;
   private int C7_f89;
   private int C7_f90 = 0;
   private int C7_f91 = 0;
   private byte[] C7_f92;
   private String[] C7_f93;
   private int[] C7_f94;
   private int[] C7_f95;
   private String[] C7_f96 = null;
   private C20 C7_f97 = null;
   private int C7_f98 = -1;
   private Calendar C7_f99 = null;
   private int[] C7_f100;
   public byte C7_f101 = 0;
   public static String[] C7_f102;
   public static String[] C7_f103;
   private int C7_f104 = 0;
   public static short[][] C7_f105 = null;
   public static byte C7_f106 = 0;
   public static byte C7_f107 = 0;
   private static byte[][] C7_f108;
   private static byte[][] C7_f109;

   public C7() {
      if (this.C7_f53 == null) {
         this.C7_f53 = C25.B();
      }

      if (this.C7_f54 == null) {
         this.C7_f54 = C53.p();
      }

      if (this.C7_f60 == null) {
         this.C7_f60 = new byte[127][];
      }

      if (C7_f105 == null) {
         C7_f105 = new short[200][2];
      }

      if (this.C7_f99 == null) {
         this.C7_f99 = Calendar.getInstance(TimeZone.getDefault());
      }

      String[][] var1;
      C7_f102 = new String[(var1 = GameUtils.readStringMatrix(GameUtils.openInputStream("/data/script/bTask.mid"))).length];

      int var2;
      for(var2 = 0; var2 < var1.length; ++var2) {
         System.arraycopy(var1[var2], 0, C7_f102, var2, var1[var2].length);
      }

      C7_f103 = new String[(var1 = GameUtils.readStringMatrix(GameUtils.openInputStream("/data/script/mTask.mid"))).length];

      for(var2 = 0; var2 < var1.length; ++var2) {
         System.arraycopy(var1[var2], 0, C7_f103, var2, var1[var2].length);
      }

      InputStream var3;
      C7_f108 = GameUtils.readByteMatrix(var3 = GameUtils.openInputStream("/data/script/bqTask.mid"));
      C7_f109 = GameUtils.readByteMatrix(var3);
   }

   public static C7 B() {
      if (C7_f52 == null) {
         C7_f52 = new C7();
      }

      return C7_f52;
   }

   public final void a(GameEngineBase var1) {
      if (this.C7_f55 != null) {
         this.C7_f55 = null;
      }

      this.C7_f55 = var1;
   }

   public final void update() {
      if (this.C7_f56 != null) {
         C30.a().d();
         this.C7_f65.d();
         C7 var1 = this;

         for(int var2 = 0; var2 < var1.C7_f56.length; ++var2) {
            if (var1.C7_f56[var2].a() == 0 || var1.C7_f56[var2].a() == 4) {
               boolean var4;
               label218: {
                  C70 var3 = var1.C7_f56[var2].d();
                  var4 = false;
                  int var7;
                  int[] var12;
                  int var15;
                  switch(var3.a()) {
                  case 13:
                     if (!GameUtils.checkCollisionWithShortArray(var3.b()[0], var3.b()[1], var3.b()[2], var3.b()[3], var1.C7_f54.C60_f861, var1.C7_f54.C60_f862, var1.C7_f54.C20_f261.k())) {
                        break label218;
                     }

                     var1.C7_f54.a((byte)0, (byte)var1.C7_f54.C60_f866);
                     break;
                  case 15:
                     if (var1.C7_f60[C25.e(var3.b()[0], var3.b()[1])] != null && (var1.C7_f60[C25.e(var3.b()[0], var3.b()[1])][var3.b()[2]] == 3 || var1.C7_f60[C25.e(var3.b()[0], var3.b()[1])][var3.b()[2]] == 4)) {
                        var4 = true;
                     }
                     break label218;
                  case 16:
                     if (var3.b()[0] != C25.C25_f318) {
                        break label218;
                     }

                     C7_f68 = true;
                     if (!C7_f69) {
                        break label218;
                     }

                     var1.C7_f54.a((byte)0, (byte)var1.C7_f54.C60_f866);
                     C7_f69 = false;
                     break;
                  case 43:
                     if (C25.e(var3.b()[2], var3.b()[3]) == C25.e(var1.C7_f53.C25_f290, var1.C7_f53.C25_f291) && var3.b()[4] == C25.C25_f318 && var1.a(var3)) {
                        C7_f68 = true;
                        if (C7_f69 && var1.l(-1) == -1) {
                           var15 = var2;
                           C7 var14 = var1;
                           var7 = 0;

                           byte var10001;
                           while(true) {
                              if (var7 >= var14.C7_f56.length) {
                                 var10001 = -1;
                                 break;
                              }

                              C70 var8;
                              if (var14.C7_f56[var7].a() != 3 && var15 != var7 && (var8 = var14.C7_f56[var7].d()).a() == 43 && C25.e(var8.b()[2], var8.b()[3]) == C25.e(var14.C7_f53.C25_f290, var14.C7_f53.C25_f291) && var8.b()[4] == C25.C25_f318 && var14.a(var8)) {
                                 var10001 = (byte)var7;
                                 break;
                              }

                              ++var7;
                           }

                           if (var2 >= var10001) {
                              var4 = true;
                              C7_f68 = false;
                              var1.C7_f54.a((byte)0, (byte)var1.C7_f54.C60_f866);
                              if (var3.b()[1] == 1) {
                                 C7_f105[C7_f107][0] = var3.b()[0];
                              }
                           }
                        }
                     }
                     break label218;
                  case 44:
                     if (C25.e(var3.b()[2], var3.b()[3]) == C25.e(var1.C7_f53.C25_f290, var1.C7_f53.C25_f291) && var3.b()[4] == C25.C25_f318 && var1.b(var3)) {
                        C7_f68 = true;
                        if (C7_f69 && var2 >= var1.l(var2)) {
                           C7_f68 = false;
                           var4 = true;
                           var1.C7_f54.a((byte)0, (byte)var1.C7_f54.C60_f866);
                        }
                     }
                     break label218;
                  case 57:
                     if (var1.C7_f54.C60_f868 != null && ((C18)var1.C7_f54.C60_f868).C18_f223 == 0 && ((C18)var1.C7_f54.C60_f868).C18_f225 == 11 && ((C18)var1.C7_f54.C60_f868).C18_f248 == var3.b()[3] && C7_f69) {
                        if (var1.C7_f53.C25_f287[var3.b()[0]].C60_f861 == var3.b()[1] && var1.C7_f53.C25_f287[var3.b()[0]].C60_f862 == var3.b()[2]) {
                           ((C18)var1.C7_f54.C60_f868).a((byte)0);
                        } else {
                           var4 = true;
                        }

                        C7_f69 = false;
                     }
                     break label218;
                  case 59:
                     var12 = new int[GameUtils.splitString(var3.c()[0], ',').length];

                     for(var15 = 0; var15 < var12.length; ++var15) {
                        var12[var15] = GameUtils.parseInt(GameUtils.splitString(var3.c()[0], ',')[var15]);
                        if (var1.C7_f53.C25_f287[var12[var15]].i() == 0) {
                           break;
                        }
                     }

                     if (var15 >= var12.length) {
                        var4 = true;
                     }
                     break label218;
                  case 61:
                     var12 = new int[GameUtils.splitString(var3.c()[0], ',').length];

                     for(var15 = 0; var15 < var12.length; ++var15) {
                        var12[var15] = GameUtils.parseInt(GameUtils.splitString(var3.c()[0], ',')[var15]);
                        if (var1.C7_f53.C25_f287[var12[var15]].i() == 0) {
                           break;
                        }
                     }

                     if (var15 >= var12.length) {
                        var1.C7_f54.a((byte)0, (byte)var1.C7_f54.C60_f866);
                        var4 = true;
                     }
                     break label218;
                  case 69:
                     if (var3.b()[0] != C25.C25_f318) {
                        break label218;
                     }
                     break;
                  case 73:
                     String[] var11 = GameUtils.splitString(var3.c()[1], ',');
                     String[] var13 = GameUtils.splitString(var3.c()[0], ',');

                     int var10;
                     for(var10 = 0; var10 < var11.length && var1.C7_f54.a((byte) GameUtils.parseByte(var13[var10]), (int) GameUtils.parseByte(var11[var10])) >= 2; ++var10) {
                     }

                     if (var10 >= var11.length) {
                        var4 = true;
                     }
                     break label218;
                  case 75:
                     if (var1.C7_f54.C53_f789.size() <= 0) {
                        break label218;
                     }
                     break;
                  case 78:
                     byte[] var5 = GameUtils.parseStringToBytes(var3.c()[0]);
                     byte[] var6 = GameUtils.parseStringToBytes(var3.c()[1]);
                     byte[] var9 = GameUtils.parseStringToBytes(var3.c()[2]);

                     for(var7 = 0; var7 < var9.length && var1.C7_f60[C25.e(var5[var7], var6[var7])] != null && (var1.C7_f60[C25.e(var5[var7], var6[var7])][var9[var7]] == 3 || var1.C7_f60[C25.e(var5[var7], var6[var7])][var9[var7]] == 4); ++var7) {
                     }

                     if (var7 >= var9.length) {
                        var4 = true;
                     }
                     break label218;
                  case 79:
                     if (var1.C7_f60[C25.e(var3.b()[0], var3.b()[1])] == null || var1.C7_f60[C25.e(var3.b()[0], var3.b()[1])][var3.b()[2]] != 3 || var1.C7_f54.l(0) || C25.C25_f318 != var3.b()[3]) {
                        break label218;
                     }

                     C7_f68 = true;
                     if (!C7_f69) {
                        break label218;
                     }

                     C7_f69 = false;
                     break;
                  case 86:
                     if (var1.C7_f60[C25.e(var3.b()[0], var3.b()[1])] == null || var1.C7_f60[C25.e(var3.b()[0], var3.b()[1])][var3.b()[2]] != 3) {
                        break label218;
                     }
                  }

                  var4 = true;
               }

               if (var4) {
                  var1.C7_f58 = (byte)var2;
                  var1.C7_f56[var2].b((byte)0);
                  var1.C7_f57.addElement(var1.C7_f56[var2]);
                  C25 var10000 = var1.C7_f53;
                  byte var16;
                  if ((var16 = C25.a(var1.C7_f58, (byte)0)) != -1) {
                     var1.C7_f59 = var1.C7_f58;
                     var1.C7_f53.C25_f342.a(var16, 1);
                     C25.C25_f345 = true;
                  }

                  var1.C7_f56[var2].a((byte)1);
               }
            }
         }

         this.J();
      }
   }

   public static void b(Graphics var0) {
      int var1;
      if (C7_f63 != null) {
         for(var1 = 0; var1 < C7_f63.size(); ++var1) {
            C20 var2;
            C20 var10000 = var2 = (C20)C7_f63.elementAt(var1);
            var10000.b(var10000.C60_f868.C60_f861, var2.C60_f868.C60_f862 - 40);
            var2.a(var0, C68.a().C68_f943, C68.a().C68_f944);
         }
      }

      if (C7_f64 != null) {
         for(var1 = 0; var1 < C7_f64.size(); ++var1) {
            ((C20)C7_f64.elementAt(var1)).a(var0, C68.a().C68_f943, C68.a().C68_f944);
         }
      }

   }

   public final void C() {
      int var1;
      if (C7_f63 != null) {
         for(var1 = 0; var1 < C7_f63.size(); ++var1) {
            C20 var2;
            (var2 = (C20)C7_f63.elementAt(var1)).a();
            if (var2.C20_f261.f()) {
               var2.d();
               C7_f63.removeElementAt(var1);
               --var1;
            }
         }
      }

      if (C7_f64 != null) {
         for(var1 = 0; var1 < C7_f64.size(); ++var1) {
            ((C20)C7_f64.elementAt(var1)).a();
         }
      }

      if (this.C7_f97 != null) {
         this.C7_f97.a();
      }

   }

   public final void renderPauseScreen(Graphics var1) {
      C30.a().a(var1);
      if (C7_f85 != null) {
         var1.setColor(0);
         var1.fillRect(0, 0, getScreenWidth(), getScreenHeight());
         var1.drawImage(C7_f85, this.C7_f87, this.C7_f88, 20);
      }

      if (this.C7_f97 != null && this.C7_f98 == C25.e(this.C7_f53.C25_f290, this.C7_f53.C25_f291)) {
         this.C7_f97.a(var1, C68.a().C68_f943, C68.a().C68_f944);
      }

      this.C7_f65.a(var1);
   }

   public final boolean initializeGame() {
      return true;
   }

   public final boolean a(DataInputStream var1, int var2, int var3, int var4, String[] var5) {
       this.C7_f56 = new C57[var4];
       this.C7_f57 = new Vector();
       C7_f63 = new Vector();
       int var6 = var2 << 8 | var3;
       if (this.C7_f60[C25.C25_f297[var2] + var3] == null) {
          this.C7_f60[C25.C25_f297[var2] + var3] = new byte[var4];
       }

       this.C7_f58 = -1;
       this.C7_f59 = -1;

       for(byte var7 = 0; var7 < var4; ++var7) {
          this.C7_f56[var7] = new C57();
          this.C7_f56[var7].a(var1, var7, var6, var5);
          this.C7_f56[var7].a(this.C7_f60[C25.C25_f297[var2] + var3][var7]);
       }

       this.G();
      return false;
   }

   public final void D() {
      this.C7_f54 = null;
      this.C7_f53 = null;
      this.C7_f60 = null;
      C7_f105 = null;
      this.C7_f99 = null;
      C7_f52 = null;
   }

   public final void cleanupCurrentScreen() {
      if (C7_f63 != null) {
         C7_f63.removeAllElements();
         C7_f63 = null;
      }

      if (C7_f64 != null) {
         C7_f64.removeAllElements();
         C7_f64 = null;
      }

      this.C7_f56 = null;
      this.C7_f76 = null;
      this.C7_f77 = null;
      this.C7_f78 = null;
      this.C7_f79 = null;
      this.C7_f80 = null;
      this.C7_f81 = null;
      this.C7_f82 = null;
      this.C7_f83 = null;
      C7_f85 = null;
      this.C7_f92 = null;
      this.C7_f93 = null;
   }

   public final void changeState(byte var1) {
   }

   private byte l(int var1) {
      for(int var2 = 0; var2 < this.C7_f56.length; ++var2) {
         C70 var3;
         if (this.C7_f56[var2].a() != 3 && var1 != var2 && (var3 = this.C7_f56[var2].d()).a() == 44 && C25.e(var3.b()[2], var3.b()[3]) == C25.e(this.C7_f53.C25_f290, this.C7_f53.C25_f291) && var3.b()[4] == C25.C25_f318 && this.b(var3)) {
            return (byte)var2;
         }
      }

      return -1;
   }

   private static void m(int var0) {
      for(int var1 = 0; var1 < C7_f107; ++var1) {
         if (C7_f105[var1][0] == var0) {
            C7_f105[var1][1] = 3;
            return;
         }
      }

   }

   private static int n(int var0) {
      for(int var1 = 0; var1 < C7_f107; ++var1) {
         if (C7_f105[var1][0] == var0) {
            return var1;
         }
      }

      return -1;
   }

   private void J() {
      ++this.C7_f104;
      int var1 = 0;

      while(true) {
         while(var1 < this.C7_f57.size()) {
            C57 var2;
            int var10;
            label1202: {
               C70 var3;
               byte var5;
               int var12;
               byte var14;
               int var15;
               int var18;
               short var19;
               int var22;
               boolean var24;
               short var27;
               switch((var3 = (var2 = (C57)this.C7_f57.elementAt(var1)).c()).a()) {
               case 0:
               case 15:
               case 26:
               case 27:
               case 28:
               case 43:
               case 44:
               case 57:
               case 59:
               case 61:
               case 68:
               case 69:
               case 73:
               case 75:
               case 78:
               case 79:
               case 86:
               default:
                  break label1202;
               case 1:
                  if (var2.a() != 5) {
                     C30.a().c(0, 9);
                     this.C7_f65.a(var3.b()[1], var3.b()[2]);
                     this.C7_f65.a((byte)(var3.b()[0] / 10 - 1), var3.c()[0], var3.b()[0] % 10);
                     this.C7_f65.a(true);
                     var2.a((byte)5);
                     break label1202;
                  }

                  if (!C39.C39_f608 || !this.C7_f55.g(65537) && !this.C7_f55.a(80, 294, 80, 18)) {
                     break label1202;
                  }

                  this.C7_f65.b();
                  if (C39.C39_f610) {
                     break label1202;
                  }

                  C30.a().C30_f472 = -1;
                  this.C7_f65.c();
                  break;
               case 2:
                  if (var3.b()[0] == -1) {
                     this.C7_f54.c();
                     this.C7_f54.a((byte)0, (byte) GameUtils.parseByte(GameUtils.splitString(var3.c()[1], ',')[0]));
                     break label1202;
                  }

                  var10 = 0;

                  while(true) {
                     if (var10 >= var3.b()[0]) {
                        break label1202;
                     }

                     this.C7_f53.C25_f287[GameUtils.parseShort(GameUtils.splitString(var3.c()[0], ',')[var10])].d(GameUtils.parseByte(GameUtils.splitString(var3.c()[1], ',')[var10]));
                     if (this.C7_f53.C25_f287[GameUtils.parseShort(GameUtils.splitString(var3.c()[0], ',')[var10])].C18_f225 == 1) {
                        this.C7_f53.C25_f287[GameUtils.parseShort(GameUtils.splitString(var3.c()[0], ',')[var10])].a((byte)0);
                     }

                     this.C7_f53.C25_f287[GameUtils.parseShort(GameUtils.splitString(var3.c()[0], ',')[var10])].c();
                     ++var10;
                  }
               case 3:
                  if (var3.b()[0] == -1) {
                     this.C7_f54.d();
                     break label1202;
                  }

                  var12 = 0;

                  while(true) {
                     if (var12 >= var3.b()[0]) {
                        break label1202;
                     }

                     var27 = GameUtils.parseShort(GameUtils.splitString(var3.c()[0], ',')[var12]);
                     this.C7_f53.C25_f287[var27].d();
                     ++var12;
                  }
               case 4:
                  if (var2.a() != 5) {
                     this.C7_f54.a((byte)0, (byte)this.C7_f54.C60_f866);
                     this.C7_f53.gameController.a((String)var3.c()[0], (String)var3.c()[1], var3.b()[1], var3.b()[0]);
                     var2.a((byte)5);
                  } else if (this.C7_f53.gameController.d(var3.b()[1], var3.b()[0]) && this.C7_f55.g(196640)) {
                     C25.B().D();
                     if (GameUtils.pageCount < GameUtils.b()) {
                        GameUtils.c();
                        this.C7_f53.gameController.b(GameUtils.pageCount);
                     } else {
                        if (C25.C25_f318 != -1 && this.C7_f53.C25_f287[C25.C25_f318].C20_f261.C62_f882 <= 85 && this.C7_f53.C25_f287[C25.C25_f318].v() == 0) {
                           C25.B().a((byte)13, C25.B().C25_f287[C25.C25_f318].C60_f861, C25.B().C25_f287[C25.C25_f318].C60_f862 - 40, C25.B().C25_f287[C25.C25_f318]);
                        }

                        C7_f68 = false;
                        C7_f69 = false;
                        this.C7_f53.gameController.aF();
                        var2.a((byte)1);
                     }
                  }
                  break label1202;
               case 5:
                  C20 var30;
                  (var30 = new C20()).a(259, false);
                  var30.g();
                  var30.a((byte)var3.b()[2], (byte)-1, true);
                  if (var3.b()[0] == 0) {
                     var30.b(this.C7_f54.m(), this.C7_f54.n() - this.C7_f54.C20_f261.b(this.C7_f54.i(), this.C7_f54.C60_f866)[3]);
                     var30.a(this.C7_f54);
                  } else if (var3.b()[0] == 1) {
                     if (var3.b()[3] == 0 && var3.b()[4] == 0) {
                        var30.b(this.C7_f53.C25_f287[var3.b()[1]].m(), this.C7_f53.C25_f287[var3.b()[1]].n());
                        var30.a(this.C7_f53.C25_f287[var3.b()[1]]);
                     } else {
                        var30.b(var3.b()[3], var3.b()[4]);
                     }
                  }

                  var30.c();
                  C7_f63.addElement(var30);
                  break label1202;
               case 6:
                  this.C7_f60[C25.C25_f297[this.C7_f53.C25_f290] + this.C7_f53.C25_f291][var2.b()] = 3;
                  C25.B().C25_f290 = var3.b()[0];
                  C25.B().C25_f291 = var3.b()[1];
                  if (var3.b()[3] == 1) {
                     this.C7_f53.C25_f295 = var3.b()[2];
                  } else {
                     this.C7_f53.C25_f295 = -1;
                  }

                  GameScreenManager.getInstance().changeState((byte)22);
                  break label1202;
               case 7:
                  if (var2.a() != 5) {
                     this.C7_f76 = new short[var3.b()[0]];

                     for(var10 = 0; var10 < this.C7_f76.length; ++var10) {
                        this.C7_f76[var10] = GameUtils.parseShort(GameUtils.splitString(var3.c()[0], ',')[var10]);
                        var5 = GameUtils.parseByte(GameUtils.splitString(var3.c()[2], ',')[var10]);
                        if (this.C7_f76[var10] == -1) {
                           this.C7_f54.a(GameUtils.parseByte(GameUtils.splitString(var3.c()[1], ',')[0]), var5);
                        } else {
                           this.C7_f53.C25_f287[this.C7_f76[var10]].d(var5);
                           this.C7_f53.C25_f287[this.C7_f76[var10]].a(GameUtils.parseByte(GameUtils.splitString(var3.c()[1], ',')[var10]));
                        }
                     }

                     this.C7_f61 = 0;
                     var2.a((byte)5);
                     break label1202;
                  }

                  for(var10 = 0; var10 < this.C7_f76.length; ++var10) {
                     if (this.C7_f76[var10] == -1) {
                        if (this.C7_f54.b()) {
                           this.C7_f54.a((byte)0, (byte)this.C7_f54.C60_f866);
                           ++this.C7_f61;
                        }
                     } else if (this.C7_f53.C25_f287[this.C7_f76[var10]].b()) {
                        this.C7_f53.C25_f287[this.C7_f76[var10]].a((byte)0);
                        ++this.C7_f61;
                     }
                  }

                  if (this.C7_f61 < this.C7_f76.length) {
                     break label1202;
                  }

                  this.C7_f61 = 0;
                  break;
               case 8:
                  this.C7_f54.c();
                  C25.C25_f318 = -1;
                  this.C7_f54.b(var3.b()[0], var3.b()[1]);
                  this.C7_f54.C20_f262.b(var3.b()[0], var3.b()[1]);
                  this.C7_f54.a((byte)0, (byte)this.C7_f54.C60_f866);
                  break label1202;
               case 9:
                  if (var2.a() != 5) {
                     var24 = false;
                     if (var3.b()[0] != 12 && var3.b()[0] != 13) {
                        if (var3.b()[0] == 10) {
                           C30.a().c(0, var3.b()[0]);
                           C30.a().d(var3.b()[1], var3.b()[2]);
                        } else if (var3.b()[0] != 15 && var3.b()[0] != 14) {
                           if (var3.b()[0] == 16) {
                              String[] var28;
                              if (var3.b()[1] == 0) {
                                 var28 = new String[]{"star0", "star1", "star2", "star3"};
                                 C30.a().a(16, 0, (byte)var3.b()[2], (byte)7, C25.C25_f298, var28);
                              } else if (var3.b()[1] == 1) {
                                 var28 = new String[]{"fire0", "fire1", "fire2"};
                                 C30.a().a(16, 0, (byte)var3.b()[2], (byte)0, (Image)null, var28);
                              } else if (var3.b()[1] == 2) {
                                 var28 = new String[]{"fire0", "fire1", "fire2"};
                                 C30.a().a(17, 0, (byte)var3.b()[2], (byte)0, (Image)null, var28);
                              } else {
                                 var24 = true;
                                 C30.a().a(-1, 0, (byte)var3.b()[2], (byte)0, (Image)null, (String[])null);
                                 var2.a((byte)1);
                              }
                           } else if (var3.b()[0] == 17) {
                              C30.a().c(var3.b()[1], var3.b()[0]);
                              C30.a().a(var3.b()[2], var3.b()[3], var3.b()[4], var3.b()[5]);
                           } else {
                              var12 = var3.b()[1] << 24 | var3.b()[2] << 16 | var3.b()[3] << 8 | var3.b()[4];
                              C30.a().c(var12, var3.b()[0]);
                           }
                        } else {
                           C30.a().c(0, var3.b()[0]);
                           C30.a().a(this.C7_f86[var3.b()[1]], var3.b()[2], var3.b()[3], var3.b()[4]);
                        }
                     } else {
                        C30.a().c(0, var3.b()[0]);
                        C30.a().a(var3.b()[1], var3.b()[2], var3.b()[3], var3.b()[4], var3.b()[5]);
                     }

                     if (!var24) {
                        var2.a((byte)5);
                     }
                     break label1202;
                  }

                  if (C30.a().C30_f477 && (var3.b()[0] == 12 || var3.b()[0] == 13)) {
                     var2.a((byte)1);
                     break label1202;
                  }

                  if (var3.b()[0] != 16 && !C30.a().C30_f476) {
                     break label1202;
                  }
                  break;
               case 10:
                  if (var2.a() != 5) {
                     if (var3.b()[0] == -1) {
                        this.C7_f77 = new byte[1];
                        this.C7_f54.d(GameUtils.parseByte(GameUtils.splitString(var3.c()[1], ',')[0]));
                        this.C7_f54.a((byte)0, (byte)this.C7_f54.C60_f866);
                        this.C7_f54.b((byte)0, (short) GameUtils.parseByte(GameUtils.splitString(var3.c()[2], ',')[0]));
                        this.C7_f77[0] = GameUtils.parseByte(GameUtils.splitString(var3.c()[3], ',')[0]);
                     } else {
                        this.C7_f76 = new short[var3.b()[0]];
                        this.C7_f77 = new byte[var3.b()[0]];

                        for(var10 = 0; var10 < this.C7_f76.length; ++var10) {
                           this.C7_f76[var10] = GameUtils.parseShort(GameUtils.splitString(var3.c()[0], ',')[var10]);
                           if (this.C7_f76[var10] != -1) {
                              this.C7_f53.C25_f287[this.C7_f76[var10]].d(GameUtils.parseByte(GameUtils.splitString(var3.c()[1], ',')[var10]));
                              this.C7_f53.C25_f287[this.C7_f76[var10]].b((byte)0, (short) GameUtils.parseByte(GameUtils.splitString(var3.c()[2], ',')[var10]));
                              this.C7_f53.C25_f287[this.C7_f76[var10]].a((byte)0);
                           } else {
                              this.C7_f54.d(GameUtils.parseByte(GameUtils.splitString(var3.c()[1], ',')[var10]));
                              this.C7_f54.b((byte)0, (short) GameUtils.parseByte(GameUtils.splitString(var3.c()[2], ',')[var10]));
                              this.C7_f54.a((byte)0, (byte)this.C7_f54.C60_f866);
                           }

                           this.C7_f77[var10] = GameUtils.parseByte(GameUtils.splitString(var3.c()[3], ',')[var10]);
                        }
                     }

                     this.C7_f61 = 0;
                     var2.a((byte)5);
                     break label1202;
                  }

                  if (var3.b()[0] == -1) {
                     if (this.C7_f54.i() == 0) {
                        this.C7_f54.a((byte)1, (byte)this.C7_f54.C60_f866);
                        break label1202;
                     }

                     --this.C7_f77[0];
                     if (this.C7_f77[0] > 0) {
                        break label1202;
                     }

                     this.C7_f54.a((byte)0, (byte)this.C7_f54.C60_f866);
                     if (this.C7_f54.C53_f793[0] != 2 && this.C7_f54.C53_f793[1] != 2) {
                        this.C7_f54.b((byte)0, (short)4);
                     } else {
                        this.C7_f54.b((byte)0, (short)8);
                     }

                     var2.a((byte)1);
                     break label1202;
                  }

                  for(var10 = 0; var10 < this.C7_f76.length; ++var10) {
                     if (this.C7_f76[var10] != -1 && this.C7_f53.C25_f287[this.C7_f76[var10]].i() == 0 || this.C7_f76[var10] == -1 && this.C7_f54.i() == 0) {
                        if (this.C7_f77[var10] > 0) {
                           if (this.C7_f76[var10] != -1) {
                              this.C7_f53.C25_f287[this.C7_f76[var10]].a((byte)3);
                           } else {
                              this.C7_f54.a((byte)1, (byte)this.C7_f54.C60_f866);
                           }
                        }
                     } else {
                        --this.C7_f77[var10];
                        if (this.C7_f77[var10] <= 0) {
                           ++this.C7_f61;
                           this.C7_f77[var10] = 0;
                           if (this.C7_f76[var10] != -1) {
                              this.C7_f53.C25_f287[this.C7_f76[var10]].a((byte)0);
                              this.C7_f53.C25_f287[this.C7_f76[var10]].b((byte)0, (short)4);
                           } else {
                              this.C7_f54.a((byte)0, (byte)this.C7_f54.C60_f866);
                              if (this.C7_f54.C53_f793[0] != 2 && this.C7_f54.C53_f793[1] != 2) {
                                 this.C7_f54.b((byte)0, (short)4);
                              } else {
                                 this.C7_f54.b((byte)0, (short)8);
                              }
                           }
                        }
                     }
                  }

                  if (this.C7_f61 < this.C7_f76.length) {
                     break label1202;
                  }
                  break;
               case 11:
                  if (var2.a() != 5) {
                     var24 = false;
                     if (var3.b()[6] == 0) {
                        var24 = true;
                     }

                     C6.a().a(var3.b()[7]);
                     if (var3.b()[2] == 1) {
                        C6.a().a(var3.b()[4], var3.b()[5], var3.b()[0], var3.b()[1], var24);
                     } else if (var3.b()[2] == 0) {
                        if (var3.b()[3] == -1) {
                           C6.a().a(this.C7_f54, var3.b()[0], var3.b()[1], var24);
                        } else {
                           C6.a().a(this.C7_f53.C25_f287[var3.b()[3]], var3.b()[0], var3.b()[1], var24);
                        }
                     }

                     this.C7_f54.a((byte)0, (byte)this.C7_f54.C60_f866);
                     var2.a((byte)5);
                     break label1202;
                  }

                  if (!C6.a().b()) {
                     break label1202;
                  }
                  break;
               case 12:
                  ++this.C7_f61;
                  if (var2.a() != 5) {
                     var2.a((byte)5);
                     break label1202;
                  }

                  if (this.C7_f61 < var3.b()[0]) {
                     break label1202;
                  }

                  this.C7_f61 = 0;
                  break;
               case 13:
                  if (GameUtils.checkCollisionWithShortArray(var3.b()[0], var3.b()[1], var3.b()[2], var3.b()[3], this.C7_f54.C60_f861, this.C7_f54.C60_f862, this.C7_f54.C20_f261.k())) {
                     var2.a((byte)1);
                     this.C7_f54.a((byte)0, (byte)this.C7_f54.C60_f866);
                  } else {
                     var2.a((byte)6);
                  }
                  break label1202;
               case 14:
                  var2.a((byte)3);
                  break label1202;
               case 16:
                  if (var3.b()[0] == C25.C25_f318) {
                     C7_f68 = true;
                     if (C7_f69) {
                        C7_f69 = false;
                        var2.a((byte)2);
                     }
                  } else {
                     var2.a((byte)6);
                  }
                  break label1202;
               case 17:
                  if (var2.a() != 5) {
                     if (var3.b()[0] == 0) {
                        if (this.C7_f54.a((int)var3.b()[1], var3.b()[2], (byte)0)) {
                           var27 = C67.C67_f923[4][var3.b()[1]][0];
                           this.C7_f55.gameController.a((String)("Đạt được: " + GameEngineBase.getLocalizedText((int)var27)), var3.b()[2]);
                           this.C7_f54.c(var3.b()[1], var3.b()[2], (byte)0);
                        } else {
                           this.C7_f55.gameController.b("Ba lô đã đủ đạo cụ này");
                        }
                     } else if (this.C7_f54.b((int)var3.b()[1], (int)var3.b()[2], (byte)0)) {
                        var27 = C67.C67_f923[4][var3.b()[1]][0];
                        this.C7_f55.gameController.a((String)("Mất: " + GameEngineBase.getLocalizedText((int)var27)), var3.b()[2]);
                        this.C7_f54.d(var3.b()[1], var3.b()[2], (byte)0);
                     }

                     var2.a((byte)5);
                     break label1202;
                  }

                  if (!this.C7_f55.gameController.aA()) {
                     break label1202;
                  }
                  break;
               case 18:
                  if (var2.a() != 5) {
                     if (var3.b()[0] == 0) {
                        if (this.C7_f54.a((int)var3.b()[1], var3.b()[2], (byte)2)) {
                           var27 = C67.C67_f923[3][var3.b()[1]][0];
                           this.C7_f55.gameController.a((String)("Đạt được: " + GameEngineBase.getLocalizedText((int)var27)), var3.b()[2]);
                           this.C7_f54.c(var3.b()[1], var3.b()[2], (byte)2);
                        } else {
                           this.C7_f55.gameController.b("Ba lô đã đủ đạo cụ này");
                        }
                     } else if (var3.b()[0] == 1) {
                        var27 = C67.C67_f923[3][var3.b()[1]][0];
                        this.C7_f55.gameController.a((String)("Mất: " + GameEngineBase.getLocalizedText((int)var27)), var3.b()[2]);
                        this.C7_f54.d(var3.b()[1], var3.b()[2], (byte)2);
                     }

                     var2.a((byte)5);
                     break label1202;
                  }

                  if (!this.C7_f55.gameController.aA()) {
                     break label1202;
                  }
                  break;
               case 19:
                  if (var2.a() != 5) {
                     var27 = C67.C67_f923[5][var3.b()[0]][0];
                     this.C7_f55.gameController.a((String)("Đạt được: " + GameEngineBase.getLocalizedText((int)var27)), var3.b()[1]);
                     if ((var12 = this.C7_f54.d(var3.b()[0], var3.b()[1])) != -1) {
                        if (var12 == 1) {
                           this.C7_f55.gameController.b("Ba lô đã đủ loại đạo cụ này");
                        } else {
                           this.C7_f54.c((int)var3.b()[0], (int)var3.b()[1]);
                        }
                     } else if (var3.b()[0] == 0) {
                        this.C7_f54.e(var3.b()[0], -1);
                     } else {
                        this.C7_f54.j(var3.b()[0]);
                     }

                     var2.a((byte)5);
                     break label1202;
                  }

                  if (!this.C7_f55.gameController.aA()) {
                     break label1202;
                  }
                  break;
               case 20:
                  if (var2.a() != 5) {
                     if (var3.b()[0] == 1) {
                        this.C7_f55.gameController.b("Mất: " + var3.c()[0]);
                        this.C7_f54.C53_f797[var3.b()[1]] = false;
                     } else {
                        this.C7_f55.gameController.b("Đạt được: " + var3.c()[0]);
                        this.C7_f54.C53_f797[var3.b()[1]] = true;
                     }

                     var2.a((byte)5);
                     break label1202;
                  }

                  if (!this.C7_f55.gameController.aA()) {
                     break label1202;
                  }
                  break;
               case 21:
                  C25.C25_f321 = false;
                  C25.C25_f322 = var3.b()[2];
                  if (var3.b()[1] == 1) {
                     C25.C25_f323 = var3.b()[3];
                     C25.C25_f324 = var3.b()[4];
                     C25.C25_f325 = var3.b()[5];
                     C25.C25_f326 = var3.b()[6];
                  }
                  break label1202;
               case 22:
                  C25.C25_f321 = true;
                  C25.C25_f320 = (byte)var3.b()[1];
                  C25.B().C25_f293 = var3.b()[2];
                  C25.B().C25_f294 = var3.b()[3];
                  C25.C25_f325 = var3.b()[4];
                  C25.C25_f326 = var3.b()[5];
                  C25.B().C25_f295 = -1;
                  break label1202;
               case 23:
                  this.C7_f60[C25.e(var3.b()[0], var3.b()[1])][var3.b()[2]] = 3;
                  if (var3.b()[0] == this.C7_f53.C25_f290 && var3.b()[1] == this.C7_f53.C25_f291) {
                     this.C7_f56[var3.b()[2]].a((byte)3);
                     if (this.C7_f57.size() > 0) {
                        this.C7_f57.removeElement(this.C7_f56[var3.b()[2]]);
                        --var1;
                     }
                  }
                  break label1202;
               case 24:
                  if (var2.a() != 5) {
                     C30.a().c(0, 11);
                     C30.a().a(var3.b()[0], var3.b()[1], var3.b()[2]);
                     var2.a((byte)5);
                     break label1202;
                  }

                  if (!C30.a().C30_f476) {
                     break label1202;
                  }
                  break;
               case 25:
                  C7_f67 = var3.b()[0] == 0;
                  break label1202;
               case 29:
                  if (var2.a() != 5) {
                     var27 = var3.b()[0];
                     if (var3.b()[0] == -1) {
                        var27 = 1;
                     }

                     this.C7_f76 = new short[var27];
                     this.C7_f82 = new short[var27];
                     this.C7_f83 = new short[var27];
                     this.C7_f78 = new short[var27];
                     this.C7_f79 = new short[var27];

                     for(var12 = 0; var12 < this.C7_f76.length; ++var12) {
                        this.C7_f76[var12] = GameUtils.parseShort(GameUtils.splitString(var3.c()[0], ',')[var12]);
                        this.C7_f82[var12] = GameUtils.parseShort(GameUtils.splitString(var3.c()[1], ',')[var12]);
                        this.C7_f83[var12] = GameUtils.parseShort(GameUtils.splitString(var3.c()[2], ',')[var12]);
                        this.C7_f78[var12] = GameUtils.parseShort(GameUtils.splitString(var3.c()[3], ',')[var12]);
                        this.C7_f79[var12] = GameUtils.parseShort(GameUtils.splitString(var3.c()[4], ',')[var12]);
                     }

                     var2.a((byte)5);
                     break label1202;
                  }

                  var24 = true;

                  for(var18 = 0; var18 < this.C7_f76.length; ++var18) {
                     if (this.C7_f78[var18] > 0 || this.C7_f79[var18] > 0) {
                        var24 = false;
                        --this.C7_f78[var18];
                        --this.C7_f79[var18];
                        if (var3.b()[0] == -1) {
                           var12 = this.C7_f54.m() + this.C7_f82[var18];
                           var15 = this.C7_f54.n() + this.C7_f83[var18];
                           this.C7_f54.b(var12, var15);
                           if (this.C7_f54.C20_f262 != null) {
                              this.C7_f54.C20_f262.b(var12, var15);
                           }
                        } else {
                           var12 = this.C7_f53.C25_f287[this.C7_f76[var18]].m() + this.C7_f82[var18];
                           var15 = this.C7_f53.C25_f287[this.C7_f76[var18]].n() + this.C7_f83[var18];
                           this.C7_f53.C25_f287[this.C7_f76[var18]].b(var12, var15);
                           if (this.C7_f53.C25_f287[this.C7_f76[var18]].C20_f262 != null) {
                              this.C7_f53.C25_f287[this.C7_f76[var18]].C20_f262.b(var12, var15);
                           }
                        }
                     }
                  }

                  if (var24) {
                     var2.a((byte)1);
                  }
                  break label1202;
               case 30:
                  if (var2.a() != 5) {
                     this.C7_f76 = new short[var3.b()[0]];
                     String[] var23 = GameUtils.splitString(var3.c()[2], ',');

                     for(var12 = 0; var12 < this.C7_f76.length; ++var12) {
                        this.C7_f76[var12] = GameUtils.parseShort(var23[var12]);
                     }

                     String[][] var21 = new String[this.C7_f76.length][];
                     String[][] var29 = new String[this.C7_f76.length][];

                     for(var18 = 0; var18 < var29.length; ++var18) {
                        var21[var18] = GameUtils.splitString(GameUtils.splitString(var3.c()[0], '#')[var18], ',');
                        var29[var18] = GameUtils.splitString(GameUtils.splitString(var3.c()[1], '#')[var18], ',');
                     }

                     this.C7_f80 = new short[this.C7_f76.length][];
                     this.C7_f81 = new short[this.C7_f76.length][];

                     for(var18 = 0; var18 < this.C7_f76.length; ++var18) {
                        this.C7_f80[var18] = new short[var21[var18].length];
                        this.C7_f81[var18] = new short[var29[var18].length];

                        for(var22 = 0; var22 < this.C7_f80[var18].length; ++var22) {
                           this.C7_f80[var18][var22] = GameUtils.parseShort(var21[var18][var22]);
                           this.C7_f81[var18][var22] = GameUtils.parseShort(var29[var18][var22]);
                        }
                     }

                     this.C7_f61 = 0;
                     var2.a((byte)5);
                     break label1202;
                  }

                  for(var10 = 0; var10 < this.C7_f76.length; ++var10) {
                     this.C7_f53.C25_f287[this.C7_f76[var10]].b(this.C7_f80[var10][this.C7_f61], this.C7_f81[var10][this.C7_f61]);
                  }

                  ++this.C7_f61;
                  if (this.C7_f61 < this.C7_f80[0].length) {
                     break label1202;
                  }
                  break;
               case 31:
                  if (var2.a() != 5) {
                     if (var3.b()[0] == 0) {
                        if (var3.b()[1] == 0) {
                           this.C7_f54.s(var3.b()[2]);
                           this.C7_f55.gameController.b("Đạt được: " + var3.b()[2] + " kim tiền");
                        } else if (var3.b()[1] == 1) {
                           this.C7_f54.v(var3.b()[2]);
                           this.C7_f55.gameController.b("Đạt được: " + var3.b()[2] + "Huy hiệu");
                        }
                     } else if (var3.b()[0] == 1) {
                        if (var3.b()[1] == 0) {
                           this.C7_f54.s(-var3.b()[2]);
                           this.C7_f55.gameController.b("Mất: " + var3.b()[2] + " kim tiền");
                        } else if (var3.b()[1] == 1) {
                           this.C7_f54.v(-var3.b()[2]);
                           this.C7_f55.gameController.b("Mất: " + var3.b()[2] + " huy hiệu");
                        }
                     }

                     var2.a((byte)5);
                     break label1202;
                  }

                  if (!this.C7_f53.gameController.aA()) {
                     break label1202;
                  }
                  break;
               case 32:
                  this.C7_f53.D();
                  C29.B().C29_f397 = var3.b()[0];
                  C29.B().C29_f398 = (byte)var3.b()[1];
                  this.C7_f53.N();
                  this.C7_f54.a((byte)0, (byte)this.C7_f54.C60_f866);
                  var2.a((byte)1);
                  C25 var10000 = this.C7_f53;
                  if ((var14 = C25.a(var2.b(), (byte)1)) != -1) {
                     this.C7_f53.C25_f342.a(var14, 1);
                  } else {
                     this.C7_f53.C25_f342.a(4, 1);
                  }

                  GameScreenManager.getInstance().changeState((byte)12);
                  break label1202;
               case 33:
                  if ((C7_f51 = (byte)var3.b()[0]) == 2) {
                     C7_f51 = 1;
                     C68.a().c();
                     var12 = 0;

                     while(true) {
                        if (var12 >= C25.B().C25_f287.length) {
                           break label1202;
                        }

                        if (this.C7_f53.C25_f287[var12].k()) {
                           this.C7_f53.C25_f287[var12].p();
                        }

                        ++var12;
                     }
                  }

                  if (C7_f51 != 3) {
                     break label1202;
                  }

                  C7_f51 = 0;
                  C68.a().c();
                  var12 = 0;

                  while(true) {
                     if (var12 >= C25.B().C25_f287.length) {
                        break label1202;
                     }

                     if (this.C7_f53.C25_f287[var12].k()) {
                        C25.B().C25_f287[var12].p();
                     }

                     ++var12;
                  }
               case 34:
                  if (var2.a() != 5) {
                     C7_f85 = GameUtils.loadImage("/data/tex/", this.C7_f86[var3.b()[0]]);
                     this.C7_f87 = var3.b()[1];
                     this.C7_f88 = var3.b()[2];
                     this.C7_f89 = var3.b()[3];
                     this.C7_f61 = var3.b()[4];
                     var2.a((byte)5);
                     break label1202;
                  }

                  --this.C7_f61;
                  this.C7_f88 -= this.C7_f89;
                  if (this.C7_f61 > 0) {
                     break label1202;
                  }

                  this.C7_f61 = 0;
                  break;
               case 35:
                  if (var2.a() == 5) {
                     int var10001 = this.C7_f90;
                     if ((var12 = this.C7_f55.gameController.c(this.C7_f91)) != -1) {
                        var2.b((byte)(this.C7_f92[var12] - 2));
                        var2.a((byte)1);
                     }
                     break label1202;
                  }

                  this.C7_f90 = var3.b()[0];
                  this.C7_f91 = var3.b()[1];
                  this.C7_f93 = GameUtils.splitString(var3.c()[0], ',');
                  this.C7_f92 = new byte[GameUtils.splitString(var3.c()[1], ',').length];
                  String var11 = var3.c()[2];

                  for(var15 = 0; var15 < this.C7_f92.length; ++var15) {
                     this.C7_f92[var15] = GameUtils.parseByte(GameUtils.splitString(var3.c()[1], ',')[var15]);
                  }

                  this.C7_f55.gameController.a(this.C7_f91, this.C7_f90, this.C7_f93, var11);
                  var2.a((byte)5);
                  break label1202;
               case 36:
                  if (var2.a() != 5) {
                     var5 = this.C7_f54.z();
                     if (var3.b()[0] == 0) {
                        if (var5 == 0) {
                           this.C7_f54.a(var3.b()[1], var3.b()[2], (short)-1, (byte)var3.b()[4], (byte)var3.b()[3], (byte)-1, new int[]{1, var3.b()[5], var3.b()[6]});
                        } else if (var5 == 1) {
                           this.C7_f55.gameController.b("Ba lô đã đủ, đã để vào ngân hàng");
                           var19 = C41.b(var3.b()[1], var3.b()[2], var3.b()[3]);
                           this.C7_f54.a(var3.b()[1], var3.b()[2], (short)-1, (byte)var3.b()[4], (byte)var3.b()[3], (byte)-1, var19, 0, 0, new int[]{1, var3.b()[5], var3.b()[6]});
                        } else {
                           this.C7_f55.gameController.b("Không có không gian, đã phóng sinh");
                        }
                     } else if (var3.b()[0] == 1) {
                        this.C7_f54.o(var3.b()[1]);
                     }

                     var2.a((byte)5);
                     break label1202;
                  }

                  if (!this.C7_f55.gameController.aA()) {
                     break label1202;
                  }
                  break;
               case 37:
                  C29.B().a(new int[][]{{var3.b()[0], var3.b()[1], var3.b()[2]}});
                  break label1202;
               case 38:
                  C7_f68 = false;

                  for(var15 = 0; var15 < GameUtils.splitString(var3.c()[0], ',').length; ++var15) {
                     if (GameUtils.parseByte(GameUtils.splitString(var3.c()[0], ',')[var15]) == C25.C25_f318) {
                        C7_f68 = true;
                        if (C7_f69) {
                           C25.C25_f318 = -1;
                           var5 = GameUtils.parseByte(GameUtils.splitString(var3.c()[1], ',')[var15]);
                           var2.b((byte)(var5 - 1));
                           C25.B().D();
                           C7_f68 = false;
                           C7_f69 = false;
                        }
                        break;
                     }
                  }

                  var2.a((byte)6);
                  break label1202;
               case 39:
                  var15 = 0;

                  while(true) {
                     if (var15 >= this.C7_f54.C53_f778) {
                        break label1202;
                     }

                     this.C7_f54.C53_f777[var15].J();
                     ++var15;
                  }
               case 40:
                  if (var2.a() != 5) {
                     this.C7_f55.gameController.c(var3.c()[0]);
                     var2.a((byte)5);
                     break label1202;
                  }

                  if (!this.C7_f55.gameController.aB()) {
                     break label1202;
                  }
                  break;
               case 41:
                  var2.b((byte)(var3.b()[0] - 2));
                  break label1202;
               case 42:
                  var2.a((byte)4);
                  break label1202;
               case 45:
                  if (var2.a() != 5) {
                     this.C7_f55.gameController.c(var3.c()[0]);
                     C7_f106 = (byte)var3.b()[0];
                     var2.a((byte)5);
                     break label1202;
                  }

                  if (!this.C7_f55.gameController.aB()) {
                     break label1202;
                  }
                  break;
               case 46:
                  if (var2.a() != 5) {
                     this.C7_f55.gameController.K();
                     this.C7_f55.gameController.a(var3.c()[0]);
                     var2.a((byte)5);
                     break label1202;
                  }

                  if (this.C7_f55.gameController.C9_f131 == 0) {
                     if (this.C7_f55.g(196640)) {
                        this.C7_f55.gameController.C9_f131 = 1;
                        this.C7_f55.gameController.a("Đang lưu...");
                        this.C7_f55.gameController.M();
                     } else if (this.C7_f55.g(262144)) {
                        var2.a((byte)1);
                        this.C7_f55.gameController.L();
                        this.C7_f55.gameController.C9_f131 = 0;
                     }
                     break label1202;
                  }

                  if (this.C7_f55.gameController.C9_f131 == 1) {
                     this.C7_f60[C25.e(this.C7_f53.C25_f290, this.C7_f53.C25_f291)][var2.b()] = 3;
                     if (((C25)this.C7_f55).I()) {
                        this.C7_f55.gameController.a("Lưu thành công");
                        this.C7_f55.gameController.C9_f131 = 2;
                     }
                     break label1202;
                  }

                  if (this.C7_f55.gameController.C9_f131 != 2) {
                     break label1202;
                  }

                  this.C7_f55.gameController.L();
                  this.C7_f55.gameController.C9_f131 = 0;
                  break;
               case 47:
                  if (this.C7_f74 != -1) {
                     var2.b((byte)(var3.b()[this.C7_f74] - 2));
                  }
                  break label1202;
               case 48:
                  if (var2.a() != 5) {
                     this.C7_f65.a(var3.b()[1], var3.b()[2]);
                     this.C7_f65.a((byte)(var3.b()[0] / 10 - 1), var3.c()[0], var3.b()[0] % 10);
                     if (var3.b()[5] == 1) {
                        this.C7_f65.a(true);
                     }

                     this.C7_f65.b(var3.b()[3], var3.b()[4]);
                     var2.a((byte)5);
                     break label1202;
                  }

                  if (!this.C7_f65.e()) {
                     var2.a((byte)1);
                     break label1202;
                  }

                  if (!C39.C39_f608 || !this.C7_f55.g(1) && !this.C7_f55.a(80, 294, 80, 18)) {
                     break label1202;
                  }

                  this.C7_f65.b();
                  if (C39.C39_f610) {
                     break label1202;
                  }

                  C30.a().C30_f472 = -1;
                  this.C7_f65.c();
                  break;
               case 49:
                  if (var2.a() == 5) {
                     if ((var15 = this.C7_f55.gameController.aG()) != -1) {
                        if (var15 == 0 && var2.d().b()[1] == 1) {
                           C7_f105[C7_f107][1] = 1;
                           ++C7_f107;
                        }

                        var2.b((byte)(this.C7_f92[var15] - 2));
                        var2.a((byte)1);
                     }
                     break label1202;
                  }

                  this.C7_f94 = new int[2];
                  this.C7_f95 = new int[2];
                  this.C7_f96 = new String[2];
                  this.C7_f93 = new String[2];

                  for(var15 = 0; var15 < 2; ++var15) {
                     this.C7_f94[var15] = var3.b()[var15 << 1];
                     this.C7_f95[var15] = var3.b()[(var15 << 1) + 1];
                     this.C7_f96[var15] = var3.c()[var15];
                  }

                  this.C7_f92 = new byte[GameUtils.splitString(var3.c()[2], ',').length];

                  for(var15 = 0; var15 < this.C7_f92.length; ++var15) {
                     this.C7_f92[var15] = GameUtils.parseByte(GameUtils.splitString(var3.c()[2], ',')[var15]);
                     this.C7_f93[var15] = GameUtils.splitString(var3.c()[3], ',')[var15];
                  }

                  this.C7_f55.gameController.a(this.C7_f94, this.C7_f95, this.C7_f96, this.C7_f93);
                  var2.a((byte)5);
                  break label1202;
               case 50:
                  if (var3.b()[0] == 0) {
                     this.C7_f54.v();
                  } else {
                     this.C7_f54.u();
                  }
                  break label1202;
               case 51:
                  this.C7_f53.gameController.aE();
                  this.C7_f65.a(var3.b()[1], var3.b()[2]);
                  this.C7_f65.a((byte)(var3.b()[0] / 10 - 1), var3.c()[0], var3.b()[0] % 10);
                  this.C7_f65.b(var3.b()[3], var3.b()[4]);
                  break label1202;
               case 52:
                  if (var3.b()[0] == 0) {
                     this.C7_f70 = true;
                  } else {
                     this.C7_f70 = false;
                  }

                  if (var3.b()[1] == 0) {
                     C7_f71 = true;
                  } else {
                     C7_f71 = false;
                  }
                  break label1202;
               case 53:
                  if (var2.a() != 5) {
                     if (var3.b()[1] == 0) {
                        this.C7_f54.b((byte)((byte)var3.b()[0]), (byte)((byte)var3.b()[1]), (byte)2);

                        for(var15 = 0; var15 < C25.B().C25_f287.length; ++var15) {
                           if (C25.B().C25_f287[var15].C18_f223 == 0 && C25.B().C25_f287[var15].C18_f225 == 1) {
                              C25.B().C25_f287[var15].w();
                           }
                        }
                     } else if (var3.b()[1] == 1) {
                        this.C7_f54.b((byte)((byte)var3.b()[0]), (byte)((byte)var3.b()[1]), (byte)1);
                     }

                     this.C7_f55.gameController.a(var3.b()[0]);
                     var2.a((byte)5);
                     break label1202;
                  }

                  if (!this.C7_f53.g(65537) && !this.C7_f55.a(94, 200, 60, 18)) {
                     break label1202;
                  }

                  this.C7_f55.gameController.Y();
                  break;
               case 54:
                  int[][] var26 = new int[var19 = var3.b()[0]][3];

                  for(var22 = 0; var22 < var19; ++var22) {
                     var26[var22][0] = GameUtils.parseInt(GameUtils.splitString(var3.c()[0], ',')[var22]);
                     var26[var22][1] = GameUtils.parseInt(GameUtils.splitString(var3.c()[1], ',')[var22]);
                     var26[var22][2] = GameUtils.parseInt(GameUtils.splitString(var3.c()[2], ',')[var22]);
                  }

                  C29.B().a(var26);
                  break label1202;
               case 55:
                  if (var3.b()[0] == 0) {
                     if (this.C7_f97 == null) {
                        this.C7_f97 = new C20();
                        this.C7_f97.a(340, false);
                        this.C7_f97.c();
                        this.C7_f98 = C25.e(var3.b()[3], var3.b()[4]);
                     }

                     this.C7_f97.b(var3.b()[1], var3.b()[2]);
                  } else if (var3.b()[0] == 1 && this.C7_f97 != null) {
                     this.C7_f97.d();
                     this.C7_f97 = null;
                     this.C7_f98 = -1;
                  }
                  break label1202;
               case 56:
                  var19 = var3.b()[1];
                  short var25;
                  if (var3.b()[0] == 0) {
                     var18 = 0;

                     while(true) {
                        if (var18 >= var19) {
                           break label1202;
                        }

                        var25 = GameUtils.parseShort(GameUtils.splitString(var3.c()[0], ',')[var18]);
                        var14 = GameUtils.parseByte(GameUtils.splitString(var3.c()[1], ',')[var18]);
                        this.C7_f53.C25_f287[var25].d(var14);
                        if (this.C7_f53.C25_f287[var25].C18_f225 == 1) {
                           this.C7_f53.C25_f287[var25].a((byte)0);
                        }

                        this.C7_f53.C25_f287[var25].c();
                        this.C7_f53.a(var25, 1, (byte)1, true);
                        this.C7_f53.a(var25, 2, var14, true);
                        this.C7_f53.C25_f287[var25].t();
                        ++var18;
                     }
                  }

                  if (var3.b()[0] != 1) {
                     break label1202;
                  }

                  var18 = 0;

                  while(true) {
                     if (var18 >= var19) {
                        break label1202;
                     }

                     var25 = GameUtils.parseShort(GameUtils.splitString(var3.c()[0], ',')[var18]);
                     if (this.C7_f53.C25_f287[var25].C18_f225 == 1) {
                        this.C7_f53.C25_f287[var25].a((byte)0);
                     }

                     this.C7_f53.C25_f287[var25].d();
                     this.C7_f53.a(var25, 1, (byte)0, true);
                     this.C7_f53.C25_f287[var25].t();
                     ++var18;
                  }
               case 58:
                  if (((C18)this.C7_f54.C60_f868).i() == 1 && ((C18)this.C7_f54.C60_f868).C20_f261.f()) {
                     ((C18)this.C7_f54.C60_f868).a((byte)0);
                     this.C7_f53.C25_f287[var3.b()[0]].b(var3.b()[1], var3.b()[2]);
                     if ((C18)this.C7_f53.C25_f287[var3.b()[0]].C60_f868 != null) {
                        ((C18)this.C7_f53.C25_f287[var3.b()[0]].C60_f868).s();
                        this.C7_f53.C25_f287[var3.b()[0]].a((C60)null);
                     }
                  }
                  break label1202;
               case 60:
                  if (var2.a() != 5) {
                     this.C7_f76 = new short[var3.b()[0]];

                     for(var15 = 0; var15 < this.C7_f76.length; ++var15) {
                        this.C7_f76[var15] = GameUtils.parseShort(GameUtils.splitString(var3.c()[0], ',')[var15]);
                        this.C7_f53.C25_f287[this.C7_f76[var15]].a(GameUtils.parseByte(GameUtils.splitString(var3.c()[1], ',')[var15]));
                        if (this.C7_f53.C25_f287[this.C7_f76[var15]].C18_f223 == 0 && this.C7_f53.C25_f287[this.C7_f76[var15]].C18_f225 == 6 && this.C7_f53.C25_f287[this.C7_f76[var15]].i() == 2) {
                           short var10002 = this.C7_f76[var15];
                           C25.B().C25_f285.a(C25.B().C25_f287[var10002], 2);
                        }
                     }

                     this.C7_f61 = 0;
                     var2.a((byte)5);
                     break label1202;
                  }

                  for(var15 = 0; var15 < this.C7_f76.length; ++var15) {
                     if (this.C7_f53.C25_f287[this.C7_f76[var15]].b()) {
                        ++this.C7_f61;
                     }
                  }

                  if (this.C7_f61 < this.C7_f76.length) {
                     break label1202;
                  }

                  this.C7_f61 = 0;
                  break;
               case 62:
                  int[] var13 = new int[GameUtils.splitString(var3.c()[0], ',').length];
                  var22 = -1;
                  var2.a((byte)6);

                  for(var18 = 0; var18 < var13.length; ++var18) {
                     var13[var18] = GameUtils.parseInt(GameUtils.splitString(var3.c()[0], ',')[var18]);
                     if (this.C7_f53.C25_f287[var13[var18]].i() == 2) {
                        var22 = var13[var18];
                        break;
                     }
                  }

                  if (var22 < 0) {
                     break label1202;
                  }

                  if (var22 == var3.b()[0]) {
                     var2.b((byte)(var3.b()[1] - 2));
                     var2.a((byte)1);
                     break label1202;
                  }

                  var2.b((byte)(var3.b()[2] - 2));
                  break;
               case 63:
                  if (var3.b()[0] == 0) {
                     this.C7_f54.h(var3.b()[1]);
                  } else {
                     this.C7_f54.t();
                  }

                  this.C7_f73 = var3.b()[2] != 0;
                  break label1202;
               case 64:
                  if (var3.b()[0] == 0) {
                     this.C7_f53.l(var3.b()[1]);
                     if (var3.b()[2] == -1) {
                        this.C7_f53.a((C20)this.C7_f54);
                     } else {
                        this.C7_f53.a((C20)this.C7_f53.C25_f287[var3.b()[2]]);
                     }
                  } else {
                     this.C7_f53.E();
                  }
                  break label1202;
               case 65:
                  if (var2.a() != 5 && !paymentActive) {
                     this.C7_f55.changeState((byte)100);
                     var2.a((byte)5);
                     break label1202;
                  }

                  if (paymentActive) {
                     var2.b((byte)(var3.b()[0] - 2));
                  } else {
                     var2.b((byte)(var3.b()[1] - 2));
                  }
                  break;
               case 66:
                  GameEngineBase.actionType = (byte)var3.b()[0];
                  GameEngineBase.b(0, 3);
                  break label1202;
               case 67:
                  C25.C25_f319 = var3.b()[0];
                  break label1202;
               case 70:
                  if (var2.a() != 5) {
                     C7_f66 = false;
                     this.C7_f62 = var3.b()[0];
                     switch(var3.b()[0]) {
                     case 0:
                     case 1:
                        this.C7_f53.changeState((byte)1);
                        break;
                     case 2:
                        this.C7_f53.changeState((byte)16);
                     }

                     var2.a((byte)5);
                     break label1202;
                  }

                  if (!C7_f66) {
                     break label1202;
                  }

                  this.C7_f62 = -1;
                  break;
               case 71:
                  if (this.C7_f54.C53_f783 >= var3.b()[0]) {
                     var2.b((byte)(var3.b()[1] - 2));
                  } else {
                     var2.b((byte)(var3.b()[2] - 2));
                  }
                  break label1202;
               case 72:
                  String[] var6 = GameUtils.splitString(var3.c()[0], ',');
                  String[] var17 = GameUtils.splitString(var3.c()[1], ',');
                  C20[] var20 = new C20[var6.length];
                  var10 = 0;

                  while(true) {
                     if (var10 >= var6.length) {
                        break label1202;
                     }

                     var20[var10] = new C20();
                     var20[var10].a(259, false);
                     var20[var10].g();
                     if (GameUtils.parseInt(var6[var10]) == -1) {
                        var20[var10].a(GameUtils.parseByte(var17[var10]), (byte)-1, true);
                        var20[var10].c();
                        var20[var10].b(this.C7_f54.m(), this.C7_f54.n() - 40);
                        var20[var10].a(this.C7_f54);
                     } else {
                        var20[var10].a(GameUtils.parseByte(var17[var10]), (byte)-1, true);
                        var20[var10].c();
                        var20[var10].b(this.C7_f53.C25_f287[GameUtils.parseInt(var6[var10])].m(), this.C7_f53.C25_f287[GameUtils.parseInt(var6[var10])].n() - 40);
                        var20[var10].a(this.C7_f53.C25_f287[GameUtils.parseInt(var6[var10])]);
                     }

                     C7_f63.addElement(var20[var10]);
                     ++var10;
                  }
               case 74:
                  if (((int[])this.C7_f54.C53_f788.elementAt(0))[1] > 0) {
                     var2.b((byte)(var3.b()[0] - 2));
                  } else {
                     var2.b((byte)(var3.b()[1] - 2));
                  }
                  break label1202;
               case 76:
                  this.C7_f60[C25.C25_f297[this.C7_f53.C25_f290] + this.C7_f53.C25_f291][var2.b()] = 3;
                  C25.B().C25_f290 = var3.b()[0];
                  C25.B().C25_f291 = var3.b()[1];
                  C25.B().C25_f295 = -1;
                  this.C7_f53.changeState((byte)29);
                  break label1202;
               case 77:
                  this.C7_f60[C25.e(var3.b()[0], var3.b()[1])][var3.b()[2]] = 4;
                  if (var3.b()[0] == this.C7_f53.C25_f290 && var3.b()[1] == this.C7_f53.C25_f291) {
                     this.C7_f56[var3.b()[2]].a((byte)4);
                  }
                  break label1202;
               case 80:
                  if (var2.a() != 5) {
                     if (var3.b()[0] == 0) {
                        this.C7_f61 = 0;
                        this.C7_f84 = 4;
                     } else if (var3.b()[0] == 1) {
                        GameScreenManager.getInstance().battleStartTime = GameScreenManager.getInstance().pauseStartTime;
                        long var16;
                        if (GameUtils.convertTime(var16 = GameScreenManager.getInstance().battleStartTime - GameScreenManager.getInstance().gameStartTime)[2] <= 60L) {
                           if ((var14 = this.C7_f54.z()) == 0) {
                              this.C7_f55.gameController.b("Đạt được #2Lục hành điểu");
                              this.C7_f54.a(54, 5, (short)-1, (byte)2, (short)-1, (byte)-1, new int[]{1, 30, 45});
                           } else if (var14 == 1) {
                              this.C7_f55.gameController.b("Đạt được #2Lục hành điểu#0 ba lô đã đủ, đã để vào ngân hàng");
                              var10 = GameUtils.getRandomInRange(C67.C67_f923[0][54][3], C67.C67_f923[0][54][3]);
                              this.C7_f54.a(54, 5, (short)-1, (byte)2, (byte)var10, (byte)-1, 0, 0, 0, new int[]{1, 30, 45});
                           } else {
                              this.C7_f55.gameController.b("Không có không gian, đã phóng sinh");
                           }
                        } else if (GameUtils.convertTime(var16)[2] <= 65L) {
                           this.C7_f54.s(1000);
                           this.C7_f55.gameController.b("Thưởng 1000 kim");
                        } else if (GameUtils.convertTime(var16)[2] <= 130L) {
                           this.C7_f54.s(750);
                           this.C7_f55.gameController.b("Thưởng 750 kim");
                        } else if (GameUtils.convertTime(var16)[2] <= 200L) {
                           this.C7_f54.s(600);
                           this.C7_f55.gameController.b("Thưởng 600 kim");
                        }

                        GameScreenManager.getInstance().pauseStartTime = 0L;
                        GameScreenManager.getInstance().gameStartTime = 0L;
                     }

                     var2.a((byte)5);
                     break label1202;
                  }

                  if (var3.b()[0] == 0) {
                     ++this.C7_f61;
                     if (this.C7_f84 > 0) {
                        if (this.C7_f61 / 10 != 0 && this.C7_f61 % 10 == 0) {
                           --this.C7_f84;
                        }
                        break label1202;
                     }

                     this.C7_f84 = 0;
                     GameScreenManager.getInstance().gameStartTime = System.currentTimeMillis();
                     GameScreenManager.getInstance().pauseStartTime = GameScreenManager.getInstance().gameStartTime;
                     GameScreenManager.getInstance().battleStartTime = 0L;
                  } else {
                     if (var3.b()[0] != 1 || !this.C7_f55.gameController.aA()) {
                        break label1202;
                     }

                     if (this.C7_f101 == 0) {
                        this.C7_f100 = this.H();
                     }

                     ++this.C7_f101;
                  }
                  break;
               case 81:
                  if (var3.b()[0] == 0) {
                     if (this.C7_f54.u(var3.b()[1])) {
                        var2.b((byte)(var3.b()[2] - 2));
                     } else {
                        var2.b((byte)(var3.b()[3] - 2));
                     }
                  } else if (var3.b()[0] == 1) {
                     if (this.C7_f54.x(var3.b()[1])) {
                        var2.b((byte)(var3.b()[2] - 2));
                     } else {
                        var2.b((byte)(var3.b()[3] - 2));
                     }
                  }
                  break label1202;
               case 82:
                  short var7 = var3.b()[0];
                  byte[] var8 = GameUtils.parseStringToBytes(var3.c()[0]);
                  var10 = 0;

                  while(true) {
                     if (var10 >= var7) {
                        break label1202;
                     }

                     this.C7_f53.C25_f287[var8[var10]].t();
                     this.C7_f53.C25_f287[var8[var10]].u();
                     ++var10;
                  }
               case 83:
                  if (var2.a() != 5) {
                     this.C7_f55.changeState((byte)30);
                     var2.a((byte)5);
                     break label1202;
                  }

                  var2.b((byte)(var3.b()[C7_f75] - 2));
                  break;
               case 84:
                  if (var2.a() != 5) {
                     int[] var4 = null;
                     if (var3.b()[2] == 1) {
                        var4 = new int[]{this.C7_f101, 5 - this.C7_f101};
                     } else if (var3.b()[2] == 0) {
                        var4 = new int[]{this.C7_f54.C53_f786, this.C7_f54.C53_f795.length - this.C7_f54.C53_f786};
                     }

                     String var9 = a(var3.c()[1], var4);
                     this.C7_f53.gameController.a((String)var3.c()[0], (String)var9, var3.b()[1], var3.b()[0]);
                     var2.a((byte)5);
                  } else if (this.C7_f53.gameController.d(var3.b()[1], var3.b()[0]) && this.C7_f55.g(196640)) {
                     C25.B().D();
                     if (GameUtils.pageCount < GameUtils.b()) {
                        GameUtils.c();
                        this.C7_f53.gameController.b(GameUtils.pageCount);
                     } else {
                        if (C25.C25_f318 != -1 && this.C7_f53.C25_f287[C25.C25_f318].C20_f261.C62_f882 <= 85 && this.C7_f53.C25_f287[C25.C25_f318].v() == 0) {
                           C25.B().a((byte)13, C25.B().C25_f287[C25.C25_f318].C60_f861, C25.B().C25_f287[C25.C25_f318].C60_f862 - 40, C25.B().C25_f287[C25.C25_f318]);
                        }

                        C7_f68 = false;
                        C7_f69 = false;
                        this.C7_f53.gameController.aF();
                        var2.a((byte)1);
                     }
                  }
                  break label1202;
               case 85:
                  if (this.C7_f101 >= 0 && this.C7_f101 < 5) {
                     var2.b((byte)(var3.b()[0] - 2));
                     break label1202;
                  }

                  var2.b((byte)(var3.b()[1] - 2));
                  break label1202;
               case 87:
                  if (var2.a() != 5) {
                     if (var3.b()[0] == 0) {
                        this.C7_f54.a(var3.b()[7], var3.b()[1], var3.b()[2], (short)-1, (byte)var3.b()[4], (byte)var3.b()[3], (byte)-1, new int[]{1, var3.b()[5], var3.b()[6]});
                     } else if (var3.b()[0] == 1) {
                        this.C7_f54.o(var3.b()[1]);
                     }

                     var2.a((byte)5);
                     break label1202;
                  }

                  if (!this.C7_f55.gameController.aA()) {
                     break label1202;
                  }
                  break;
               case 88:
                  if (this.C7_f54.z() == 2) {
                     var2.b((byte)(var3.b()[0] - 2));
                  } else {
                     var2.b((byte)(var3.b()[1] - 2));
                  }
               }

               var2.a((byte)1);
            }

            if (var2.a() != 5 && var2.a() != 6) {
               var2.e();
            }

            if (var2.a() != 3 && var2.a() != 4) {
               ++var1;
            } else {
               C7_f69 = false;
               this.C7_f57.removeElement(var2);
               var10 = C25.e(var2.f()[0], var2.f()[1]);
               if (this.C7_f60[var10] != null) {
                  this.C7_f60[var10][var2.b()] = var2.a();
               }

               if (var2.a() == 3 && var2.d().a() == 44 && var2.d().b()[1] == 1) {
                  m(var2.d().b()[0]);
               }

               this.G();
               if (C25.C25_f345) {
                  this.C7_f53.C25_f342.a(C25.C25_f344, 1);
               }

               C25.C25_f345 = false;
            }
         }

         return;
      }
   }

   public final byte E() {
      return this.C7_f58;
   }

   public final boolean F() {
      if (this.C7_f56 == null) {
         return false;
      } else {
         for(int var1 = 0; var1 < this.C7_f57.size(); ++var1) {
            C57 var2;
            if ((var2 = (C57)this.C7_f57.elementAt(var1)).a() != 2 && var2.a() != 6) {
               return true;
            }
         }

         return false;
      }
   }

   private boolean a(C70 var1) {
      boolean var2 = false;
      if (var1.b()[7] == -1 || var1.b()[7] != -1 && this.C7_f60[C25.e(var1.b()[5], var1.b()[6])] != null && this.C7_f60[C25.e(var1.b()[5], var1.b()[6])][var1.b()[7]] == 3) {
         label58:
         switch(var1.b()[8]) {
         case 0:
            if (!this.C7_f54.C53_f797[var1.b()[9]]) {
               return var2;
            }
            break;
         case 1:
            var2 = true;
            return var2;
         case 2:
            if (this.C7_f54.C53_f792.size() + this.C7_f54.C53_f778 < var1.b()[9]) {
               return var2;
            }

            int var3;
            for(var3 = 0; var3 < this.C7_f54.C53_f778; ++var3) {
               if (this.C7_f54.C53_f777[var3].t() == var1.b()[10]) {
                  var2 = true;
                  break;
               }
            }

            if (var2) {
               return var2;
            }

            for(var3 = 0; var3 < this.C7_f54.C53_f792.size(); ++var3) {
               if (((int[])this.C7_f54.C53_f792.elementAt(var3))[1] == var1.b()[10]) {
                  break label58;
               }
            }

            return var2;
         case 3:
            if (this.C7_f54.C53_f783 < var1.b()[9]) {
               return var2;
            }
            break;
         case 4:
            if (this.C7_f54.a((byte)((byte)var1.b()[9]), (int)var1.b()[10]) != 2) {
               return var2;
            }
            break;
         case 5:
            if (C7_f106 <= var1.b()[9]) {
               return var2;
            }
            break;
         case 6:
            if (C7_f106 != var1.b()[9]) {
               return var2;
            }
         }

         var2 = true;
      }

      return var2;
   }

   private boolean b(C70 var1) {
      boolean var2 = false;
      if (var1.b()[7] == -1 || var1.b()[7] != -1 && this.C7_f60[C25.e(var1.b()[5], var1.b()[6])] != null && this.C7_f60[C25.e(var1.b()[5], var1.b()[6])][var1.b()[7]] == 3) {
         switch(var1.b()[8]) {
         case 0:
            if (this.C7_f54.a((byte)((byte)var1.b()[9]), (int)var1.b()[10]) == 2) {
               var2 = true;
            }
            break;
         case 1:
            if (this.C7_f54.C53_f797[var1.b()[9]]) {
               var2 = true;
            }
            break;
         case 2:
         case 4:
            if (this.C7_f60[C25.e(var1.b()[5], var1.b()[6])] != null && this.C7_f60[C25.e(var1.b()[5], var1.b()[6])][var1.b()[7]] == 3) {
               var2 = true;
            }
            break;
         case 3:
            if (this.C7_f54.b((int)var1.b()[9], (int)var1.b()[10], (byte)0)) {
               var2 = true;
            }
            break;
         case 5:
            if (this.C7_f54.C53_f783 >= var1.b()[9]) {
               var2 = true;
            }
            break;
         case 6:
            byte[] var5 = new byte[]{0, 1, 2, 3};

            int var3;
            for(var3 = 0; var3 < this.C7_f54.C53_f778; ++var3) {
               for(int var4 = 0; var4 < var5.length; ++var4) {
                  if (var5[var4] != -1 && var5[var4] == C67.a((byte)0, (short)this.C7_f54.C53_f777[var3].r(), (byte)1)) {
                     var5[var4] = -1;
                     break;
                  }
               }
            }

            for(var3 = 0; var3 < var5.length && var5[var3] == -1; ++var3) {
            }

            if (var3 >= var5.length) {
               var2 = true;
            }
         }
      }

      return var2;
   }

   public final void G() {
      if (C7_f64 == null) {
         C7_f64 = new Vector();
      }

      C7_f64.removeAllElements();
      Vector var2 = new Vector();

      C70 var1;
      int var3;
      C18 var6;
      C20 var7;
      for(var3 = 0; var3 < C7_f109.length; ++var3) {
         if (C25.e(C7_f109[var3][0], C7_f109[var3][1]) == C25.e(C25.B().C25_f290, C25.B().C25_f291) && (this.C7_f56[C7_f109[var3][2]].a() == 0 || this.C7_f56[C7_f109[var3][2]].a() == 4)) {
            var1 = this.C7_f56[C7_f109[var3][2]].d();
            if (!var2.contains("" + var1.b()[4])) {
               if (this.b(var1)) {
                  (var7 = new C20()).a(259, false);
                  var7.a((byte)1, (byte)-1, true);
                  var7.b(this.C7_f53.C25_f287[var1.b()[4]].C60_f861, this.C7_f53.C25_f287[var1.b()[4]].C60_f862 - 40);
                  this.C7_f53.C25_f287[var1.b()[4]].f((byte)1);
                  var6 = this.C7_f53.C25_f287[var1.b()[4]];
                  var7.C60_f868 = var6;
                  var7.c();
                  C7_f64.addElement(var7);
                  var2.addElement("" + var1.b()[4]);
               } else {
                  int var4 = n(var1.b()[0]);
                  if (var1.b()[1] == 0 && this.C7_f60[C25.e(C7_f108[var3][0], C7_f108[var3][1])][C7_f108[var3][2]] == 3 && this.C7_f60[C25.e(C7_f109[var3][0], C7_f109[var3][1])][C7_f109[var3][2]] != 3 || var1.b()[1] == 1 && var4 != -1 && C7_f105[var4][1] == 1) {
                     (var7 = new C20()).a(259, false);
                     var7.a((byte)15, (byte)-1, true);
                     var7.b(this.C7_f53.C25_f287[var1.b()[4]].C60_f861, this.C7_f53.C25_f287[var1.b()[4]].C60_f862 - 40);
                     this.C7_f53.C25_f287[var1.b()[4]].f((byte)1);
                     var6 = this.C7_f53.C25_f287[var1.b()[4]];
                     var7.C60_f868 = var6;
                     var7.c();
                     C7_f64.addElement(var7);
                     var2.addElement("" + var1.b()[4]);
                  }
               }
            }
         }
      }

      for(var3 = 0; var3 < C7_f108.length; ++var3) {
         if (C25.e(C7_f108[var3][0], C7_f108[var3][1]) == C25.e(C25.B().C25_f290, C25.B().C25_f291) && (this.C7_f56[C7_f108[var3][2]].a() == 0 || this.C7_f56[C7_f108[var3][2]].a() == 4)) {
            var1 = this.C7_f56[C7_f108[var3][2]].d();
            if (!var2.contains("" + var1.b()[4]) && this.a(var1)) {
               (var7 = new C20()).a(259, false);
               var7.a((byte)7, (byte)-1, true);
               var7.b(this.C7_f53.C25_f287[var1.b()[4]].C60_f861, this.C7_f53.C25_f287[var1.b()[4]].C60_f862 - 40);
               this.C7_f53.C25_f287[var1.b()[4]].f((byte)1);
               var6 = this.C7_f53.C25_f287[var1.b()[4]];
               var7.C60_f868 = var6;
               var7.c();
               C7_f64.addElement(var7);
            }
         }
      }

   }

   public final int[] H() {
      int[] var1;
      (var1 = new int[4])[0] = this.C7_f99.get(1);
      var1[1] = this.C7_f99.get(Calendar.MONTH);
      var1[2] = this.C7_f99.get(Calendar.DATE);
      var1[3] = this.C7_f99.get(Calendar.HOUR_OF_DAY);
      return this.C7_f100;
   }

   public final int[] I() {
      return this.C7_f100;
   }

   public final void a(int[] var1) {
      this.C7_f100 = var1;
   }
}
