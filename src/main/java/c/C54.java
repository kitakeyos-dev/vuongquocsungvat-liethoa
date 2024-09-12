package c;

import a.C44;
import java.util.Enumeration;
import java.util.Hashtable;
import java.util.Vector;
import javax.microedition.lcdui.Graphics;

public final class C54 {
   private static C54 C54_f807;
   private Hashtable C54_f808 = new Hashtable();
   private Vector C54_f809 = new Vector();
   private Vector C54_f810 = new Vector();
   public C58 C54_f811;
   public C31 C54_f812 = new C31();

   private C54() {
      C31 var10000 = this.C54_f812;
   }

   public static C54 a() {
      if (C54_f807 == null) {
         C54_f807 = new C54();
      }

      return C54_f807;
   }

   public final void b() {
      if (C54_f807 != null) {
         C54 var1;
         Enumeration var2 = (var1 = C54_f807).C54_f808.elements();

         while(var2.hasMoreElements()) {
            ((C58)var2.nextElement()).c();
         }

         var1.C54_f808.clear();
         var1.C54_f809.removeAllElements();
      }

      this.C54_f811 = null;
      System.gc();
   }

   public final void a(Graphics var1) {
      if (this.C54_f809 != null) {
         for(int var2 = 0; var2 < this.C54_f809.size(); ++var2) {
            ((C58)this.C54_f809.elementAt(var2)).a(var1);
         }
      }

      var1.setClip(0, 0, C44.g(), C44.h());
   }

   public final void c() {
      if (this.C54_f811 != null) {
         this.C54_f811.b();
      }

   }

   public final void a(String var1, int var2, C61 var3) {
      C58 var4;
      if ((var4 = (C58)this.C54_f808.get(var1)) != null) {
         if (!var1.equals("/data/ui/dialog.ui")) {
            this.C54_f808.remove(var1);
         }

         this.C54_f809.removeElement(var4);
         if (!var1.equals("/data/ui/dialog.ui")) {
            var4.c();
            var4 = null;
         }

         if (var1.equals("/data/ui/dialog.ui")) {
            this.C54_f809.addElement(var4);
            this.C54_f811 = var4;
            this.C54_f810.addElement(var1);
         }
      }

      if (var4 == null) {
         (var4 = new C58(var3)).a(this.C54_f812);
         var4.a(var1, var2, false);
         this.C54_f808.put(var1, var4);
         this.C54_f809.addElement(var4);
         this.C54_f811 = var4;
         this.C54_f810.addElement(var1);
      }

   }

   public final void a(String var1) {
      C58 var2 = (C58)this.C54_f808.get(var1);
      if (var2 != null) {
         if (this.C54_f811.equals(var2)) {
            this.C54_f811 = null;
         }

         if (!var1.equals("/data/ui/dialog.ui")) {
            this.C54_f808.remove(var1);
         }

         this.C54_f809.removeElement(var2);
         this.C54_f810.removeElement(var1);
         if (!var1.equals("/data/ui/dialog.ui")) {
            var2.c();
         }
      }

      if (!this.C54_f808.isEmpty() && !this.C54_f809.isEmpty()) {
         this.C54_f811 = (C58)this.C54_f809.lastElement();
      }

   }

   public boolean b(String var1) {
      return !this.C54_f810.isEmpty() && this.C54_f810.lastElement().equals(var1);
   }

   public boolean c(String var1) {
      return !this.C54_f810.isEmpty() && this.C54_f810.contains(var1);
   }

   public C58 d(String var1) {
      return (C58)this.C54_f808.get(var1);
   }
}
