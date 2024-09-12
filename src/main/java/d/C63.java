package d;

import java.io.PrintStream;
import java.util.Random;

public final class C63 {
   public C72 C63_f890;
   public PrintStream C63_f891;

   public C63() {

   }

   private C63(PrintStream var1) throws Exception {
      new Random();
      this.C63_f891 = var1;
      this.C63_f890 = new C72(this, new C36((byte)0));
       try {
           this.C63_f890.C72_f964.a("_G", this.C63_f890.C72_f964);
       } catch (Exception e) {
           e.printStackTrace();
       }
       C47.a(this);
   }

   public final int a(C72 var1, int var2) throws Exception {
      return this.c(var1, var2);
   }

   private int c(C72 var1, int var2) throws Exception {
      int var3 = var1.C72_f969 - var2 - 1;
      Object var4;
      if ((var4 = var1.C72_f968[var3]) == null) {
         throw new Exception("call nil");
      } else if (var4 instanceof C32) {
         return a(var1, (C32)var4, var3 + 1, var3, var2);
      } else if (!(var4 instanceof C71)) {
         throw new Exception("call a non-func");
      } else {
         var1.a((C71)var4, var3 + 1, var3, var2, false, false).b();
         this.a(var1);
         var2 = var1.C72_f969 - var3;
         var1.C72_f966 = "";
         return var2;
      }
   }

   private static int a(C72 var0, C32 var1, int var2, int var3, int var4) throws Exception {
      int var5 = 0;
      C34 var9 = var0.a(null, var2, var3, var4, false, false);
      var0.a();

      try {
         var5 = var1.a(var9, var4);
      } catch (ClassCastException var6) {
         var6.printStackTrace();
         System.out.println("func");
      }

      int var10000 = var9.a();
      boolean var7 = false;
      int var8 = var10000 - var5;
      var9.a(var8, -1, var5);
      var9.c(var5 - 1);
      return var5;
   }

   private final void a(C72 var1) throws Exception {
      C34 var2;
      C71 var3;
      C24 var4;
      int[] var5 = (var4 = (var3 = (var2 = var1.b()).C34_f549).C71_f961).C24_f274;
      int var10 = var2.C34_f552;

      while(true) {
         while(true) {
            C34 var14;
            try {
               label383:
               while(true) {
                  int var8;
                  int var9 = (var8 = var5[var2.C34_f550++]) & 63;
                  int var6 = var8 >>> 6 & 255;
                  int var7;
                  Object var16;
                  int var20;
                  Object var21;
                  int var23;
                  int var26;
                  int var27;
                  Object var28;
                  Integer var29;
                  Object var31;
                  String var32;
                  switch(var9) {
                  case 0:
                     var7 = var8 >>> 23 & 511;
                     var2.a(var6, var2.a(var7));
                     break;
                  case 1:
                     var7 = var8 >>> 14;
                     var2.a(var6, var4.C24_f275[var7]);
                     break;
                  case 2:
                     var7 = var8 >>> 23 & 511;
                     var8 = var8 >>> 14 & 511;
                     var2.a(var6, var7 == 0 ? Boolean.FALSE : Boolean.TRUE);
                     if (var8 != 0) {
                        ++var2.C34_f550;
                     }
                     break;
                  case 3:
                     var7 = var8 >>> 23 & 511;
                     var2.a(var6, var7);
                     break;
                  case 4:
                     var7 = var8 >>> 23 & 511;
                     var2.a(var6, var3.C71_f963[var7].a());
                     break;
                  case 5:
                     var7 = var8 >>> 14;
                     var2.a(var6, b(var3.C71_f962, var4.C24_f275[var7]));
                     break;
                  case 6:
                     var7 = var8 >>> 23 & 511;
                     var8 = var8 >>> 14 & 511;
                     var21 = var2.a(var7);
                     var31 = a(var2, var8, var4);
                     var2.a(var6, b(var21, var31));
                     break;
                  case 7:
                     var7 = var8 >>> 14;
                     var21 = var2.a(var6);
                     var31 = var4.C24_f275[var7];
                     a(var3.C71_f962, var31, var21);
                     break;
                  case 8:
                     var7 = var8 >>> 23 & 511;
                     var3.C71_f963[var7].a(var2.a(var6));
                     break;
                  case 9:
                     var7 = var8 >>> 23 & 511;
                     var8 = var8 >>> 14 & 511;
                     var21 = var2.a(var6);
                     var31 = a(var2, var7, var4);
                     var28 = a(var2, var8, var4);
                     a(var21, var31, var28);
                     break;
                  case 10:
                     C36 var34 = new C36((byte)0);
                     var2.a(var6, var34);
                     break;
                  case 11:
                     var7 = var8 >>> 23 & 511;
                     var8 = var8 >>> 14 & 511;
                     var21 = a(var2, var8, var4);
                     var28 = b(var31 = var2.a(var7), var21);
                     var2.a(var6, var28);
                     var2.a(var6 + 1, var31);
                     break;
                  case 12:
                  case 13:
                  case 14:
                  case 15:
                  case 16:
                  case 17:
                     var7 = var8 >>> 23 & 511;
                     var8 = var8 >>> 14 & 511;
                     var21 = a(var2, var7, var4);
                     var31 = a(var2, var8, var4);
                     var14 = null;
                     Integer var25 = null;
                     Integer var36;
                     if ((var36 = C47.b(var21)) != null && (var29 = C47.b(var31)) != null) {
                        var25 = a(var36, var29, var9);
                     }

                     var2.a(var6, var25);
                  case 18:
                  default:
                     break;
                  case 19:
                     var7 = var8 >>> 23 & 511;
                     var21 = var2.a(var7);
                     var2.a(var6, a(!b(var21)));
                     break;
                  case 20:
                     var7 = var8 >>> 23 & 511;
                     Integer var35;
                     if ((var21 = var2.a(var7)) instanceof C36) {
                        var35 = a(((C36)var21).a());
                     } else if (var21 instanceof String) {
                        var35 = a(((String)var21).length());
                     } else {
                        var35 = null;
                     }

                     var2.a(var6, var35);
                     break;
                  case 21:
                     var7 = var8 >>> 23 & 511;
                     var8 = var8 >>> 14 & 511;
                     var20 = var7;
                     var28 = var2.a(var8);
                     var26 = var8 - 1;

                     while(var20 <= var26) {
                        var32 = C47.a(var28);
                        if (var28 != null) {
                           var8 = 0;

                           for(var7 = var26; var20 <= var7; ++var8) {
                              Object var17 = var2.a(var7);
                              --var7;
                              if (C47.a(var17) == null) {
                                 break;
                              }
                           }

                           if (var8 > 0) {
                              StringBuffer var18 = new StringBuffer();

                              for(var7 = var26 - var8 + 1; var7 <= var26; ++var7) {
                                 var18.append(C47.a(var2.a(var7)));
                              }

                              var18.append(var32);
                              var28 = var18.toString();
                              var26 -= var8;
                           }
                        }

                        if (var20 <= var26) {
                           var2.a(var26);
                           --var26;
                        }
                     }

                     var2.a(var6, var28);
                     break;
                  case 22:
                     var2.C34_f550 += b(var8);
                     break;
                  case 23:
                  case 24:
                  case 25:
                     var7 = var8 >>> 23 & 511;
                     var8 = var8 >>> 14 & 511;
                     var21 = a(var2, var7, var4);
                     var31 = a(var2, var8, var4);
                     if (var21 instanceof Integer && var31 instanceof Integer) {
                        var23 = a(var21);
                        var27 = a(var31);
                        if (var9 == 23) {
                           if (var23 == var27 == (var6 == 0)) {
                              ++var2.C34_f550;
                           }
                        } else if (var9 == 24) {
                           if (var23 < var27 == (var6 == 0)) {
                              ++var2.C34_f550;
                           }
                        } else if (var23 <= var27 == (var6 == 0)) {
                           ++var2.C34_f550;
                        }
                     } else if (var21 instanceof String && var31 instanceof String) {
                        if (var9 == 23) {
                           if (var21.equals(var31) == (var6 == 0)) {
                              ++var2.C34_f550;
                           }
                        } else {
                           String var33 = (String)var21;
                           var32 = (String)var31;
                           var8 = var33.compareTo(var32);
                           if (var9 == 24) {
                              if (var8 < 0 == (var6 == 0)) {
                                 ++var2.C34_f550;
                              }
                           } else if (var8 <= 0 == (var6 == 0)) {
                              ++var2.C34_f550;
                           }
                        }
                     } else {
                        boolean var30 = false;
                        if (var21 == var31) {
                           var30 = true;
                        } else if (var9 == 23) {
                           var30 = c(var21, var31);
                        } else {
                           C47.a(String.valueOf(var9) + " not defined for operand");
                        }

                        if (var30 == (var6 == 0)) {
                           ++var2.C34_f550;
                        }
                     }
                     break;
                  case 26:
                     var8 = var8 >>> 14 & 511;
                     if (b(var2.a(var6)) == (var8 == 0)) {
                        ++var2.C34_f550;
                     }
                     break;
                  case 27:
                     var7 = var8 >>> 23 & 511;
                     var8 = var8 >>> 14 & 511;
                     if (b(var21 = var2.a(var7)) != (var8 == 0)) {
                        var2.a(var6, var21);
                     } else {
                        ++var2.C34_f550;
                     }
                     break;
                  case 28:
                     var7 = var8 >>> 23 & 511;
                     var8 = var8 >>> 14 & 511;
                     if ((var20 = var7 - 1) != -1) {
                        var2.c(var6 + var20 + 1);
                     } else {
                        var20 = var2.a() - var6 - 1;
                     }

                     var2.C34_f556 = var8 != 0;
                     var27 = (var23 = (var26 = var2.C34_f551) + var6) + 1;
                     if ((var16 = var2.a(var6)) == null) {
                        C47.a(var16 != null, "call nil");
                     }

                     if (var16 instanceof C71) {
                        C34 var19;
                        (var19 = var1.a((C71)var16, var27, var23, var20, true, var2.C34_f555)).b();
                        var2 = var19;
                        var5 = (var4 = (var3 = var19.C34_f549).C71_f961).C24_f274;
                        var10 = var19.C34_f552;
                     } else {
                        if (!(var16 instanceof C32)) {
                           throw new Exception("Call non-func: " + var16);
                        }

                        a(var1, (C32)var16, var26 + var6 + 1, var26 + var6, var20);
                        if ((var2 = var1.b()).d()) {
                           return;
                        }

                        if (var2.C34_f549 != null) {
                           var5 = (var4 = (var3 = var2.C34_f549).C71_f961).C24_f274;
                           var10 = var2.C34_f552;
                        }

                        if (var2.C34_f556) {
                           var2.c(var4.C24_f281);
                        }
                     }
                     break;
                  case 29:
                     var20 = var2.C34_f551;
                     var1.b(var20);
                     if ((var26 = (var8 >>> 23 & 511) - 1) == -1) {
                        var26 = var2.a() - var6 - 1;
                     }

                     var2.C34_f556 = false;
                     if ((var28 = var2.a(var6)) == null) {
                        C47.a(var28 != null, "Tried to call nil");
                     }

                     var27 = var10 + 1;
                     var1.a(var20 + var6, var10, var26 + 1);
                     var1.a(var10 + var26 + 1);
                     if (var28 instanceof C71) {
                        var2.C34_f551 = var27;
                        var2.C34_f553 = var26;
                        var2.C34_f549 = (C71)var28;
                        var2.b();
                     } else {
                        if (!(var28 instanceof C32)) {
                           C47.a("Tried to call a non-function: " + var28);
                        }

                        a(var1, (C32)var28, var27, var10, var26);
                        var2 = var1.b();
                        var1.a();
                        if (var1 != var1) {
                           if (var1.c() && var1.C72_f965 == var1) {
                              var1.C72_f965 = var1.C72_f965;
                              var1.C72_f965 = null;
                              var1.C72_f965.b().a(Boolean.TRUE);
                           }

                           if ((var2 = var1.b()).d()) {
                              return;
                           }
                        } else {
                           if (!var2.C34_f554) {
                              return;
                           }

                           if ((var2 = var1.b()).C34_f556) {
                              var2.c(var2.C34_f549.C71_f961.C24_f281);
                           }
                        }
                     }

                     var5 = (var4 = (var3 = var2.C34_f549).C71_f961).C24_f274;
                     var10 = var2.C34_f552;
                     break;
                  case 30:
                     var7 = (var8 >>> 23 & 511) - 1;
                     var20 = var2.C34_f551;
                     var1.b(var20);
                     if (var7 == -1) {
                        var7 = var2.a() - var6;
                     }

                     var1.a(var2.C34_f551 + var6, var10, var7);
                     var1.a(var10 + var7);
                     if (!var2.C34_f554) {
                        var1.a();
                        return;
                     }

                     var1.a();
                     if ((var2 = var1.b()).C34_f549 != null) {
                        var5 = (var4 = (var3 = var2.C34_f549).C71_f961).C24_f274;
                        var10 = var2.C34_f552;
                     }

                     if (var2.C34_f556) {
                        var2.c(var4.C24_f281);
                     }
                     break;
                  case 31:
                     label355: {
                        var20 = a(var2.a(var6));
                        var26 = a(var2.a(var6 + 1));
                        var23 = a(var2.a(var6 + 2));
                        var29 = a(var20 += var23);
                        var2.a(var6, var29);
                        if (var23 > 0) {
                           if (var20 <= var26) {
                              break label355;
                           }
                        } else if (var20 >= var26) {
                           break label355;
                        }

                        var2.b(var6);
                        break;
                     }

                     var7 = b(var8);
                     var2.C34_f550 += var7;
                     var2.a(var6 + 3, var29);
                     break;
                  case 32:
                     var7 = b(var8);
                     var20 = a(var2.a(var6));
                     var26 = a(var2.a(var6 + 2));
                     var2.a(var6, a(var20 - var26));
                     var2.C34_f550 += var7;
                     break;
                  case 33:
                     var8 = var8 >>> 14 & 511;
                     var2.c(var6 + 6);
                     var2.a(var6, var6 + 3, 3);
                     this.a(var1, 2);
                     var2.b(var6 + 3 + var8);
                     var2.c();
                     if ((var21 = var2.a(var6 + 3)) != null) {
                        var2.a(var6 + 2, var21);
                     } else {
                        ++var2.C34_f550;
                     }
                     break;
                  case 34:
                     var7 = var8 >>> 23 & 511;
                     var8 = var8 >>> 14 & 511;
                     if (var7 == 0) {
                        var7 = var2.a() - var6 - 1;
                     }

                     if (var8 == 0) {
                        var8 = var5[var2.C34_f550++];
                     }

                     var20 = (var8 - 1) * 50;
                     C36 var24 = (C36)var2.a(var6);
                     var23 = 1;

                     while(true) {
                        if (var23 > var7) {
                           continue label383;
                        }

                        var29 = a(var20 + var23);
                        var16 = var2.a(var6 + var23);
                        var24.a(var29, var16);
                        ++var23;
                     }
                  case 35:
                     var2.d(var6);
                     break;
                  case 36:
                     var7 = var8 >>> 14;
                     C24 var11 = var4.C24_f276[var7];
                     C71 var22 = new C71(var11, var3.C71_f962);
                     var2.a(var6, var22);
                     var23 = var11.C24_f280;
                     var27 = 0;

                     while(true) {
                        if (var27 >= var23) {
                           continue label383;
                        }

                        var9 = (var8 = var5[var2.C34_f550++]) & 63;
                        var7 = var8 >>> 23 & 511;
                        switch(var9) {
                        case 0:
                           var22.C71_f963[var27] = var2.e(var7);
                        case 1:
                        case 2:
                        case 3:
                        default:
                           break;
                        case 4:
                           var22.C71_f963[var27] = var3.C71_f963[var7];
                        }

                        ++var27;
                     }
                  case 37:
                     var7 = (var8 >>> 23 & 511) - 1;
                     var2.b(var6, var7);
                  }
               }
            } catch (Exception var15) {
               var15.printStackTrace();

               while(!var1.b().e()) {
                  var1.a();
               }

               boolean var12 = true;

               do {
                  if ((var2 = var1.b()) == null) {
                     C72 var13;
                     if ((var13 = var1.C72_f965) != null) {
                        var1.C72_f965 = null;
                        (var14 = var13.b()).a(Boolean.FALSE);
                        var14.a(var15.getMessage());
                        var14.a(var1.C72_f966);
                        var1.C72_f972.C63_f890 = var13;
                        var1 = var13;
                        var5 = (var4 = (var3 = (var2 = var13.b()).C34_f549).C71_f961).C24_f274;
                        var10 = var2.C34_f552;
                        var12 = false;
                     }
                     break;
                  }

                  var1.a();
               } while(var2.C34_f554);

               if (var2 != null) {
                  var2.d(0);
               }

               if (var12) {
                  throw var15;
               }
            }
         }
      }
   }

   private static final Object a(C34 var0, int var1, C24 var2) {
      int var3;
      return (var3 = var1 - 256) < 0 ? var0.a(var1) : var2.C24_f275[var3];
   }

   private static final int b(int var0) {
      return (var0 >>> 14) - 131071;
   }

   private static final Integer a(Integer var0, Integer var1, int var2) {
      int var4 = (Integer)var0;
      int var5 = (Integer)var1;
      int var3 = 0;
      switch(var2) {
      case 12:
         var3 = var4 + var5;
         break;
      case 13:
         var3 = var4 - var5;
         break;
      case 14:
         var3 = var4 * var5;
         break;
      case 15:
         var3 = var4 / var5;
         break;
      case 16:
         var3 = var4 % var5;
      }

      return a(var3);
   }

   public final Object a(Object var1, Object var2) throws Exception {
      int var3 = this.C63_f890.C72_f969;
      this.C63_f890.a(var3 + 1 + 3);
      Object[] var5;
      (var5 = this.C63_f890.C72_f968)[var3] = var1;
      var5[var3 + 1] = null;
      var5[var3 + 2] = null;
      var5[var3 + 3] = null;
      int var4 = this.c(this.C63_f890, 3);
      var2 = null;
      if (var4 > 0) {
         var2 = this.C63_f890.C72_f968[var3];
      }

      this.C63_f890.a(var3);
      return var2;
   }

   public static Object b(Object var0, Object var1) throws Exception {
      int var2;
      if (!(var0 instanceof C36)) {
         if (var0 instanceof String && var1 instanceof Integer) {
            var2 = (Integer)var1;
            return ((String)var0).substring(var2 - 1, var2);
         } else {
            return null;
         }
      } else {
         if (var1 instanceof Integer) {
            var2 = (Integer)var1;
            Object var3;
            if ((var3 = ((C36)var0).a(var2)) != null) {
               return var3;
            }
         }

         return ((C36)var0).a(var1);
      }
   }

   private static void a(Object var0, Object var1, Object var2) {
      try {
         ((C36)var0).a(var1, var2);
      } catch (Exception var3) {
         var3.printStackTrace();
         System.out.println(var1);
         System.out.println(var2);
         System.out.println(var0);
      }
   }

   public final int b(C72 var1, int var2) throws Exception {
      C72 var3 = var1;
      C34 var4 = var1.b();
      var1.C72_f966 = "";
      int var5 = var1.C72_f969 - var2 - 1;

      String var10;
      Object var11;
      try {
         int var14 = this.c(var1, var2);
         int var12 = var5 + var14 + 1;
         var3.a(var12);
         var3.a(var5, var5 + 1, var14);
         var3.C72_f968[var5] = Boolean.TRUE;
         return var14 + 1;
      } catch (Exception var8) {
         var11 = var8;
         var10 = null;
         var8.printStackTrace();
      } catch (Throwable var9) {
         var11 = var9;
         var10 = var9.getMessage();
         var9.printStackTrace();
      }

      C47.a(true, "Thread changed in pcall");
      if (var4 != null) {
         var4.d(0);
      }

      C34 var6 = var4;
      C72 var13 = var3;

      C34 var7;
      while((var7 = var13.b()) != null && var7 != var6) {
         var13.a();
      }

       var3.a(var5 + 4);
      var3.C72_f968[var5] = Boolean.FALSE;
      var3.C72_f968[var5 + 1] = var10;
      var3.C72_f968[var5 + 2] = var3.C72_f966;
      var3.C72_f968[var5 + 3] = var11;
      var3.C72_f966 = "";
      return 4;
   }

   public static boolean c(Object var0, Object var1) {
      if (var0 != null && var1 != null) {
         if (var0 instanceof Integer && var1 instanceof Integer) {
            Integer var2 = (Integer)var0;
            Integer var3 = (Integer)var1;
            return var2 == var3;
         } else {
            return var0 == var1;
         }
      } else {
         return var0 == var1;
      }
   }

   private static int a(Object var0) {
      return (Integer)var0;
   }

   public static Integer a(int var0) {
      return new Integer(var0);
   }

   private static boolean b(Object var0) {
      return var0 != null && var0 != Boolean.FALSE;
   }

   private static Boolean a(boolean var0) {
      return var0 ? Boolean.TRUE : Boolean.FALSE;
   }
}
