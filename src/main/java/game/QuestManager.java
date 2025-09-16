package game;

import a.GameUtils;
import a.GameEngineBase;
import a.a.GameObject;
import a.a.C30;
import a.b.CameraController;
import a.b.GameEntity;
import a.b.ResourceManager;
import a.b.TileMapRenderer;
import event.EventScript;
import event.ScriptCommand;
import java.io.DataInputStream;
import java.io.InputStream;
import java.util.Calendar;
import java.util.TimeZone;
import java.util.Vector;
import javax.microedition.lcdui.Graphics;
import javax.microedition.lcdui.Image;

public final class QuestManager extends GameEngineBase {
   public static byte questState;
   private static QuestManager instance = null;
   private C25 mapManager;
   private C53 player;
   private GameEngineBase gameEngine;
   public EventScript[] eventScripts;
   private Vector activeEvents;
   private byte currentEventIndex = -1;
   public byte lastEventIndex = -1;
   public byte[][] questStates;
   private int animationCounter = 0;
   public int questChangeState = -1;
   private static Vector effectObjects;
   public static Vector questEffectObjects;
   private C39 dialogManager = C39.a();
   public static boolean isChangingState = false;
   public static boolean isQuestActive = true;
   public static boolean isQuestTriggered = false;
   public static boolean isQuestReady = false;
   public boolean isPlayerControl = true;
   public static boolean isMapControl = true;
   public static boolean isSpecialState;
   public boolean isEventVisible = true;
   public byte selectOption = 0;
   public static byte questOptionIndex = -1;
   private short[] eventEntityIds;
   private byte[] eventEntityStates;
   private short[] eventEntityMoveX;
   private short[] eventEntityMoveY;
   private short[][] eventEntityPathX;
   private short[][] eventEntityPathY;
   private short[] eventEntityOffsetX;
   private short[] eventEntityOffsetY;
   public byte questEffectCounter = 0;
   public static Image questEffectImage;
   private String[] questIconNames = new String[]{"ikon_1", "ikon_2", "ikon_3", "ikon_4", "ikon_5"};
   private int effectImageX;
   private int effectImageY;
   private int effectImageDeltaY;
   private int dialogId = 0;
   private int dialogType = 0;
   private byte[] dialogOptions;
   private String[] dialogTexts;
   private int[] dialogOptionIds;
   private int[] dialogOptionTypes;
   private String[] dialogOptionTexts = null;
   private GameObject questSpecialObject = null;
   private int questSpecialObjectId = -1;
   private Calendar calendar = null;
   private int[] currentTimeArray;
   public byte questProgress = 0;
   public static String[] questNames;
   public static String[] questDescriptions;
   private int updateCounter = 0;
   public static short[][] questFlags = null;
   public static byte questDialogState = 0;
   public static byte questFlagCount = 0;
   private static byte[][] questStartMatrix;
   private static byte[][] questEndMatrix;

   public QuestManager() {
      if (this.mapManager == null) {
         this.mapManager = C25.B();
      }

      if (this.player == null) {
         this.player = C53.p();
      }

      if (this.questStates == null) {
         this.questStates = new byte[127][];
      }

      if (questFlags == null) {
         questFlags = new short[200][2];
      }

      if (this.calendar == null) {
         this.calendar = Calendar.getInstance(TimeZone.getDefault());
      }

      String[][] var1 = GameUtils.readStringMatrix(GameUtils.openInputStream("/data/script/bTask.mid"));
      questNames = new String[var1.length];

      int var2;
      for(var2 = 0; var2 < var1.length; ++var2) {
         System.arraycopy(var1[var2], 0, questNames, var2, var1[var2].length);
      }

      questDescriptions = new String[(var1 = GameUtils.readStringMatrix(GameUtils.openInputStream("/data/script/mTask.mid"))).length];

      for(var2 = 0; var2 < var1.length; ++var2) {
         System.arraycopy(var1[var2], 0, questDescriptions, var2, var1[var2].length);
      }

      InputStream var3;
      questStartMatrix = GameUtils.readByteMatrix(var3 = GameUtils.openInputStream("/data/script/bqTask.mid"));
      questEndMatrix = GameUtils.readByteMatrix(var3);
   }

   public static QuestManager getInstance() {
      if (instance == null) {
         instance = new QuestManager();
      }

      return instance;
   }

   public final void setGameEngine(GameEngineBase var1) {
      if (this.gameEngine != null) {
         this.gameEngine = null;
      }

      this.gameEngine = var1;
   }

   public final void update() {
      if (this.eventScripts != null) {
         C30.a().d();
         this.dialogManager.d();
         QuestManager var1 = this;

         for(int var2 = 0; var2 < var1.eventScripts.length; ++var2) {
            if (var1.eventScripts[var2].getExecutionState() == 0 || var1.eventScripts[var2].getExecutionState() == 4) {
               boolean var4;
               label218: {
                  ScriptCommand var3 = var1.eventScripts[var2].getFirstCommand();
                  var4 = false;
                  int var7;
                  int[] var12;
                  int var15;
                  switch(var3.getCommandId()) {
                  case 13:
                     if (!GameUtils.checkCollisionWithShortArray(var3.getNumericParameters()[0], var3.getNumericParameters()[1], var3.getNumericParameters()[2], var3.getNumericParameters()[3], var1.player.worldX, var1.player.worldY, var1.player.sprite.getCurrentFrameEvents())) {
                        break label218;
                     }

                     var1.player.a((byte)0, (byte)var1.player.currentDirection);
                     break;
                  case 15:
                     if (var1.questStates[C25.e(var3.getNumericParameters()[0], var3.getNumericParameters()[1])] != null && (var1.questStates[C25.e(var3.getNumericParameters()[0], var3.getNumericParameters()[1])][var3.getNumericParameters()[2]] == 3 || var1.questStates[C25.e(var3.getNumericParameters()[0], var3.getNumericParameters()[1])][var3.getNumericParameters()[2]] == 4)) {
                        var4 = true;
                     }
                     break label218;
                  case 16:
                     if (var3.getNumericParameters()[0] != C25.C25_f318) {
                        break label218;
                     }

                     isQuestTriggered = true;
                     if (!isQuestReady) {
                        break label218;
                     }

                     var1.player.a((byte)0, (byte)var1.player.currentDirection);
                     isQuestReady = false;
                     break;
                  case 43:
                     if (C25.e(var3.getNumericParameters()[2], var3.getNumericParameters()[3]) == C25.e(var1.mapManager.C25_f290, var1.mapManager.C25_f291) && var3.getNumericParameters()[4] == C25.C25_f318 && var1.checkEventCondition(var3)) {
                        isQuestTriggered = true;
                        if (isQuestReady && var1.findEventIndex(-1) == -1) {
                           var15 = var2;
                           QuestManager var14 = var1;
                           var7 = 0;

                           byte var10001;
                           while(true) {
                              if (var7 >= var14.eventScripts.length) {
                                 var10001 = -1;
                                 break;
                              }

                              ScriptCommand var8;
                              if (var14.eventScripts[var7].getExecutionState() != 3 && var15 != var7 && (var8 = var14.eventScripts[var7].getFirstCommand()).getCommandId() == 43 && C25.e(var8.getNumericParameters()[2], var8.getNumericParameters()[3]) == C25.e(var14.mapManager.C25_f290, var14.mapManager.C25_f291) && var8.getNumericParameters()[4] == C25.C25_f318 && var14.checkEventCondition(var8)) {
                                 var10001 = (byte)var7;
                                 break;
                              }

                              ++var7;
                           }

                           if (var2 >= var10001) {
                              var4 = true;
                              isQuestTriggered = false;
                              var1.player.a((byte)0, (byte)var1.player.currentDirection);
                              if (var3.getNumericParameters()[1] == 1) {
                                 questFlags[questFlagCount][0] = var3.getNumericParameters()[0];
                              }
                           }
                        }
                     }
                     break label218;
                  case 44:
                     if (C25.e(var3.getNumericParameters()[2], var3.getNumericParameters()[3]) == C25.e(var1.mapManager.C25_f290, var1.mapManager.C25_f291) && var3.getNumericParameters()[4] == C25.C25_f318 && var1.checkEventCompletion(var3)) {
                        isQuestTriggered = true;
                        if (isQuestReady && var2 >= var1.findEventIndex(var2)) {
                           isQuestTriggered = false;
                           var4 = true;
                           var1.player.a((byte)0, (byte)var1.player.currentDirection);
                        }
                     }
                     break label218;
                  case 57:
                     if (var1.player.followTarget != null && ((C18)var1.player.followTarget).C18_f223 == 0 && ((C18)var1.player.followTarget).C18_f225 == 11 && ((C18)var1.player.followTarget).C18_f248 == var3.getNumericParameters()[3] && isQuestReady) {
                        if (var1.mapManager.C25_f287[var3.getNumericParameters()[0]].worldX == var3.getNumericParameters()[1] && var1.mapManager.C25_f287[var3.getNumericParameters()[0]].worldY == var3.getNumericParameters()[2]) {
                           ((C18)var1.player.followTarget).a((byte)0);
                        } else {
                           var4 = true;
                        }

                        isQuestReady = false;
                     }
                     break label218;
                  case 59:
                     var12 = new int[GameUtils.splitString(var3.getStringParameters()[0], ',').length];

                     for(var15 = 0; var15 < var12.length; ++var15) {
                        var12[var15] = GameUtils.parseInt(GameUtils.splitString(var3.getStringParameters()[0], ',')[var15]);
                        if (var1.mapManager.C25_f287[var12[var15]].getFacingDirection() == 0) {
                           break;
                        }
                     }

                     if (var15 >= var12.length) {
                        var4 = true;
                     }
                     break label218;
                  case 61:
                     var12 = new int[GameUtils.splitString(var3.getStringParameters()[0], ',').length];

                     for(var15 = 0; var15 < var12.length; ++var15) {
                        var12[var15] = GameUtils.parseInt(GameUtils.splitString(var3.getStringParameters()[0], ',')[var15]);
                        if (var1.mapManager.C25_f287[var12[var15]].getFacingDirection() == 0) {
                           break;
                        }
                     }

                     if (var15 >= var12.length) {
                        var1.player.a((byte)0, (byte)var1.player.currentDirection);
                        var4 = true;
                     }
                     break label218;
                  case 69:
                     if (var3.getNumericParameters()[0] != C25.C25_f318) {
                        break label218;
                     }
                     break;
                  case 73:
                     String[] var11 = GameUtils.splitString(var3.getStringParameters()[1], ',');
                     String[] var13 = GameUtils.splitString(var3.getStringParameters()[0], ',');

                     int var10;
                     for(var10 = 0; var10 < var11.length && var1.player.a((byte) GameUtils.parseByte(var13[var10]), (int) GameUtils.parseByte(var11[var10])) >= 2; ++var10) {
                     }

                     if (var10 >= var11.length) {
                        var4 = true;
                     }
                     break label218;
                  case 75:
                     if (var1.player.C53_f789.size() <= 0) {
                        break label218;
                     }
                     break;
                  case 78:
                     byte[] var5 = GameUtils.parseStringToBytes(var3.getStringParameters()[0]);
                     byte[] var6 = GameUtils.parseStringToBytes(var3.getStringParameters()[1]);
                     byte[] var9 = GameUtils.parseStringToBytes(var3.getStringParameters()[2]);

                     for(var7 = 0; var7 < var9.length && var1.questStates[C25.e(var5[var7], var6[var7])] != null && (var1.questStates[C25.e(var5[var7], var6[var7])][var9[var7]] == 3 || var1.questStates[C25.e(var5[var7], var6[var7])][var9[var7]] == 4); ++var7) {
                     }

                     if (var7 >= var9.length) {
                        var4 = true;
                     }
                     break label218;
                  case 79:
                     if (var1.questStates[C25.e(var3.getNumericParameters()[0], var3.getNumericParameters()[1])] == null || var1.questStates[C25.e(var3.getNumericParameters()[0], var3.getNumericParameters()[1])][var3.getNumericParameters()[2]] != 3 || var1.player.l(0) || C25.C25_f318 != var3.getNumericParameters()[3]) {
                        break label218;
                     }

                     isQuestTriggered = true;
                     if (!isQuestReady) {
                        break label218;
                     }

                     isQuestReady = false;
                     break;
                  case 86:
                     if (var1.questStates[C25.e(var3.getNumericParameters()[0], var3.getNumericParameters()[1])] == null || var1.questStates[C25.e(var3.getNumericParameters()[0], var3.getNumericParameters()[1])][var3.getNumericParameters()[2]] != 3) {
                        break label218;
                     }
                  }

                  var4 = true;
               }

               if (var4) {
                  var1.currentEventIndex = (byte)var2;
                  var1.eventScripts[var2].setCurrentCommandIndex((byte)0);
                  var1.activeEvents.addElement(var1.eventScripts[var2]);
                  C25 var10000 = var1.mapManager;
                  byte var16;
                  if ((var16 = C25.a(var1.currentEventIndex, (byte)0)) != -1) {
                     var1.lastEventIndex = var1.currentEventIndex;
                     var1.mapManager.C25_f342.playBackgroundMusic(var16, 1);
                     C25.C25_f345 = true;
                  }

                  var1.eventScripts[var2].setExecutionState((byte)1);
               }
            }
         }

         this.processActiveEvents();
      }
   }

   public static void renderEffects(Graphics var0) {
      int var1;
      if (effectObjects != null) {
         for(var1 = 0; var1 < effectObjects.size(); ++var1) {
            GameObject var2;
            GameObject var10000 = var2 = (GameObject)effectObjects.elementAt(var1);
            var10000.setWorldPosition(var10000.followTarget.worldX, var2.followTarget.worldY - 40);
            var2.render(var0, TileMapRenderer.getInstance().cameraX, TileMapRenderer.getInstance().cameraY);
         }
      }

      if (questEffectObjects != null) {
         for(var1 = 0; var1 < questEffectObjects.size(); ++var1) {
            ((GameObject)questEffectObjects.elementAt(var1)).render(var0, TileMapRenderer.getInstance().cameraX, TileMapRenderer.getInstance().cameraY);
         }
      }

   }

   public final void updateEffects() {
      int var1;
      if (effectObjects != null) {
         for(var1 = 0; var1 < effectObjects.size(); ++var1) {
            GameObject var2;
            (var2 = (GameObject)effectObjects.elementAt(var1)).updateAnimation();
            if (var2.sprite.isAtLastFrame()) {
               var2.deactivate();
               effectObjects.removeElementAt(var1);
               --var1;
            }
         }
      }

      if (questEffectObjects != null) {
         for(var1 = 0; var1 < questEffectObjects.size(); ++var1) {
            ((GameObject)questEffectObjects.elementAt(var1)).updateAnimation();
         }
      }

      if (this.questSpecialObject != null) {
         this.questSpecialObject.updateAnimation();
      }

   }

   public final void renderPauseScreen(Graphics var1) {
      C30.a().a(var1);
      if (questEffectImage != null) {
         var1.setColor(0);
         var1.fillRect(0, 0, getScreenWidth(), getScreenHeight());
         var1.drawImage(questEffectImage, this.effectImageX, this.effectImageY, 20);
      }

      if (this.questSpecialObject != null && this.questSpecialObjectId == C25.e(this.mapManager.C25_f290, this.mapManager.C25_f291)) {
         this.questSpecialObject.render(var1, TileMapRenderer.getInstance().cameraX, TileMapRenderer.getInstance().cameraY);
      }

      this.dialogManager.a(var1);
   }

   public final boolean initializeGame() {
      return true;
   }

   public final boolean loadQuests(DataInputStream var1, int var2, int var3, int var4, String[] var5) {
       this.eventScripts = new EventScript[var4];
       this.activeEvents = new Vector();
       effectObjects = new Vector();
       int var6 = var2 << 8 | var3;
       if (this.questStates[C25.C25_f297[var2] + var3] == null) {
          this.questStates[C25.C25_f297[var2] + var3] = new byte[var4];
       }

       this.currentEventIndex = -1;
       this.lastEventIndex = -1;

       for(byte var7 = 0; var7 < var4; ++var7) {
          this.eventScripts[var7] = new EventScript();
          this.eventScripts[var7].loadFromStream(var1, var7, var6, var5);
          this.eventScripts[var7].setExecutionState(this.questStates[C25.C25_f297[var2] + var3][var7]);
       }

       this.updateQuestEffects();
      return false;
   }

   public final void resetManager() {
      this.player = null;
      this.mapManager = null;
      this.questStates = null;
      questFlags = null;
      this.calendar = null;
      instance = null;
   }

   public final void cleanupCurrentScreen() {
      if (effectObjects != null) {
         effectObjects.removeAllElements();
         effectObjects = null;
      }

      if (questEffectObjects != null) {
         questEffectObjects.removeAllElements();
         questEffectObjects = null;
      }

      this.eventScripts = null;
      this.eventEntityIds = null;
      this.eventEntityStates = null;
      this.eventEntityMoveX = null;
      this.eventEntityMoveY = null;
      this.eventEntityPathX = null;
      this.eventEntityPathY = null;
      this.eventEntityOffsetX = null;
      this.eventEntityOffsetY = null;
      questEffectImage = null;
      this.dialogOptions = null;
      this.dialogTexts = null;
   }

   public final void changeState(byte var1) {
   }

   private byte findEventIndex(int var1) {
      for(int var2 = 0; var2 < this.eventScripts.length; ++var2) {
         ScriptCommand var3;
         if (this.eventScripts[var2].getExecutionState() != 3 && var1 != var2 && (var3 = this.eventScripts[var2].getFirstCommand()).getCommandId() == 44 && C25.e(var3.getNumericParameters()[2], var3.getNumericParameters()[3]) == C25.e(this.mapManager.C25_f290, this.mapManager.C25_f291) && var3.getNumericParameters()[4] == C25.C25_f318 && this.checkEventCompletion(var3)) {
            return (byte)var2;
         }
      }

      return -1;
   }

   private static void setQuestFlag(int var0) {
      for(int var1 = 0; var1 < questFlagCount; ++var1) {
         if (questFlags[var1][0] == var0) {
            questFlags[var1][1] = 3;
            return;
         }
      }

   }

   private static int getQuestFlagIndex(int var0) {
      for(int var1 = 0; var1 < questFlagCount; ++var1) {
         if (questFlags[var1][0] == var0) {
            return var1;
         }
      }

      return -1;
   }

   private void processActiveEvents() {
      ++this.updateCounter;
      int var1 = 0;

      while(true) {
         while(var1 < this.activeEvents.size()) {
            EventScript var2;
            int var10;
            label1202: {
               ScriptCommand var3;
               byte var5;
               int var12;
               byte var14;
               int var15;
               int var18;
               short var19;
               int var22;
               boolean var24;
               short var27;
               switch((var3 = (var2 = (EventScript)this.activeEvents.elementAt(var1)).getCurrentCommand()).getCommandId()) {
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
                  if (var2.getExecutionState() != 5) {
                     C30.a().c(0, 9);
                     this.dialogManager.a(var3.getNumericParameters()[1], var3.getNumericParameters()[2]);
                     this.dialogManager.a((byte)(var3.getNumericParameters()[0] / 10 - 1), var3.getStringParameters()[0], var3.getNumericParameters()[0] % 10);
                     this.dialogManager.a(true);
                     var2.setExecutionState((byte)5);
                     break label1202;
                  }

                  if (!C39.C39_f608 || !this.gameEngine.isKeyPressed(65537) && !this.gameEngine.isPointerClickInBounds(80, 294, 80, 18)) {
                     break label1202;
                  }

                  this.dialogManager.b();
                  if (C39.C39_f610) {
                     break label1202;
                  }

                  C30.a().C30_f472 = -1;
                  this.dialogManager.c();
                  break;
               case 2:
                  if (var3.getNumericParameters()[0] == -1) {
                     this.player.activate();
                     this.player.a((byte)0, (byte) GameUtils.parseByte(GameUtils.splitString(var3.getStringParameters()[1], ',')[0]));
                     break label1202;
                  }

                  var10 = 0;

                  while(true) {
                     if (var10 >= var3.getNumericParameters()[0]) {
                        break label1202;
                     }

                     this.mapManager.C25_f287[GameUtils.parseShort(GameUtils.splitString(var3.getStringParameters()[0], ',')[var10])].setCurrentDirection(GameUtils.parseByte(GameUtils.splitString(var3.getStringParameters()[1], ',')[var10]));
                     if (this.mapManager.C25_f287[GameUtils.parseShort(GameUtils.splitString(var3.getStringParameters()[0], ',')[var10])].C18_f225 == 1) {
                        this.mapManager.C25_f287[GameUtils.parseShort(GameUtils.splitString(var3.getStringParameters()[0], ',')[var10])].a((byte)0);
                     }

                     this.mapManager.C25_f287[GameUtils.parseShort(GameUtils.splitString(var3.getStringParameters()[0], ',')[var10])].activate();
                     ++var10;
                  }
               case 3:
                  if (var3.getNumericParameters()[0] == -1) {
                     this.player.deactivate();
                     break label1202;
                  }

                  var12 = 0;

                  while(true) {
                     if (var12 >= var3.getNumericParameters()[0]) {
                        break label1202;
                     }

                     var27 = GameUtils.parseShort(GameUtils.splitString(var3.getStringParameters()[0], ',')[var12]);
                     this.mapManager.C25_f287[var27].deactivate();
                     ++var12;
                  }
               case 4:
                  if (var2.getExecutionState() != 5) {
                     this.player.a((byte)0, (byte)this.player.currentDirection);
                     this.mapManager.gameController.a((String)var3.getStringParameters()[0], (String)var3.getStringParameters()[1], var3.getNumericParameters()[1], var3.getNumericParameters()[0]);
                     var2.setExecutionState((byte)5);
                  } else if (this.mapManager.gameController.d(var3.getNumericParameters()[1], var3.getNumericParameters()[0]) && this.gameEngine.isKeyPressed(196640)) {
                     C25.B().D();
                     if (GameUtils.pageCount < GameUtils.b()) {
                        GameUtils.c();
                        this.mapManager.gameController.b(GameUtils.pageCount);
                     } else {
                        if (C25.C25_f318 != -1 && this.mapManager.C25_f287[C25.C25_f318].sprite.spriteSetId <= 85 && this.mapManager.C25_f287[C25.C25_f318].v() == 0) {
                           C25.B().a((byte)13, C25.B().C25_f287[C25.C25_f318].worldX, C25.B().C25_f287[C25.C25_f318].worldY - 40, C25.B().C25_f287[C25.C25_f318]);
                        }

                        isQuestTriggered = false;
                        isQuestReady = false;
                        this.mapManager.gameController.aF();
                        var2.setExecutionState((byte)1);
                     }
                  }
                  break label1202;
               case 5:
                  GameObject var30;
                  (var30 = new GameObject()).loadSpriteSet(259, false);
                  var30.applyColorEffects();
                  var30.setAnimation((byte)var3.getNumericParameters()[2], (byte)-1, true);
                  if (var3.getNumericParameters()[0] == 0) {
                     var30.setWorldPosition(this.player.getWorldX(), this.player.getWorldY() - this.player.sprite.getSpritePartBounds(this.player.getFacingDirection(), this.player.currentDirection)[3]);
                     var30.setFollowTarget(this.player);
                  } else if (var3.getNumericParameters()[0] == 1) {
                     if (var3.getNumericParameters()[3] == 0 && var3.getNumericParameters()[4] == 0) {
                        var30.setWorldPosition(this.mapManager.C25_f287[var3.getNumericParameters()[1]].getWorldX(), this.mapManager.C25_f287[var3.getNumericParameters()[1]].getWorldY());
                        var30.setFollowTarget(this.mapManager.C25_f287[var3.getNumericParameters()[1]]);
                     } else {
                        var30.setWorldPosition(var3.getNumericParameters()[3], var3.getNumericParameters()[4]);
                     }
                  }

                  var30.activate();
                  effectObjects.addElement(var30);
                  break label1202;
               case 6:
                  this.questStates[C25.C25_f297[this.mapManager.C25_f290] + this.mapManager.C25_f291][var2.getEventId()] = 3;
                  C25.B().C25_f290 = var3.getNumericParameters()[0];
                  C25.B().C25_f291 = var3.getNumericParameters()[1];
                  if (var3.getNumericParameters()[3] == 1) {
                     this.mapManager.C25_f295 = var3.getNumericParameters()[2];
                  } else {
                     this.mapManager.C25_f295 = -1;
                  }

                  GameScreenManager.getInstance().changeState((byte)22);
                  break label1202;
               case 7:
                  if (var2.getExecutionState() != 5) {
                     this.eventEntityIds = new short[var3.getNumericParameters()[0]];

                     for(var10 = 0; var10 < this.eventEntityIds.length; ++var10) {
                        this.eventEntityIds[var10] = GameUtils.parseShort(GameUtils.splitString(var3.getStringParameters()[0], ',')[var10]);
                        var5 = GameUtils.parseByte(GameUtils.splitString(var3.getStringParameters()[2], ',')[var10]);
                        if (this.eventEntityIds[var10] == -1) {
                           this.player.a(GameUtils.parseByte(GameUtils.splitString(var3.getStringParameters()[1], ',')[0]), var5);
                        } else {
                           this.mapManager.C25_f287[this.eventEntityIds[var10]].setCurrentDirection(var5);
                           this.mapManager.C25_f287[this.eventEntityIds[var10]].a(GameUtils.parseByte(GameUtils.splitString(var3.getStringParameters()[1], ',')[var10]));
                        }
                     }

                     this.animationCounter = 0;
                     var2.setExecutionState((byte)5);
                     break label1202;
                  }

                  for(var10 = 0; var10 < this.eventEntityIds.length; ++var10) {
                     if (this.eventEntityIds[var10] == -1) {
                        if (this.player.isAnimationComplete()) {
                           this.player.a((byte)0, (byte)this.player.currentDirection);
                           ++this.animationCounter;
                        }
                     } else if (this.mapManager.C25_f287[this.eventEntityIds[var10]].isAnimationComplete()) {
                        this.mapManager.C25_f287[this.eventEntityIds[var10]].a((byte)0);
                        ++this.animationCounter;
                     }
                  }

                  if (this.animationCounter < this.eventEntityIds.length) {
                     break label1202;
                  }

                  this.animationCounter = 0;
                  break;
               case 8:
                  this.player.activate();
                  C25.C25_f318 = -1;
                  this.player.setWorldPosition(var3.getNumericParameters()[0], var3.getNumericParameters()[1]);
                  this.player.attachedObject.setWorldPosition(var3.getNumericParameters()[0], var3.getNumericParameters()[1]);
                  this.player.a((byte)0, (byte)this.player.currentDirection);
                  break label1202;
               case 9:
                  if (var2.getExecutionState() != 5) {
                     var24 = false;
                     if (var3.getNumericParameters()[0] != 12 && var3.getNumericParameters()[0] != 13) {
                        if (var3.getNumericParameters()[0] == 10) {
                           C30.a().c(0, var3.getNumericParameters()[0]);
                           C30.a().d(var3.getNumericParameters()[1], var3.getNumericParameters()[2]);
                        } else if (var3.getNumericParameters()[0] != 15 && var3.getNumericParameters()[0] != 14) {
                           if (var3.getNumericParameters()[0] == 16) {
                              String[] var28;
                              if (var3.getNumericParameters()[1] == 0) {
                                 var28 = new String[]{"star0", "star1", "star2", "star3"};
                                 C30.a().a(16, 0, (byte)var3.getNumericParameters()[2], (byte)7, C25.C25_f298, var28);
                              } else if (var3.getNumericParameters()[1] == 1) {
                                 var28 = new String[]{"fire0", "fire1", "fire2"};
                                 C30.a().a(16, 0, (byte)var3.getNumericParameters()[2], (byte)0, (Image)null, var28);
                              } else if (var3.getNumericParameters()[1] == 2) {
                                 var28 = new String[]{"fire0", "fire1", "fire2"};
                                 C30.a().a(17, 0, (byte)var3.getNumericParameters()[2], (byte)0, (Image)null, var28);
                              } else {
                                 var24 = true;
                                 C30.a().a(-1, 0, (byte)var3.getNumericParameters()[2], (byte)0, (Image)null, (String[])null);
                                 var2.setExecutionState((byte)1);
                              }
                           } else if (var3.getNumericParameters()[0] == 17) {
                              C30.a().c(var3.getNumericParameters()[1], var3.getNumericParameters()[0]);
                              C30.a().a(var3.getNumericParameters()[2], var3.getNumericParameters()[3], var3.getNumericParameters()[4], var3.getNumericParameters()[5]);
                           } else {
                              var12 = var3.getNumericParameters()[1] << 24 | var3.getNumericParameters()[2] << 16 | var3.getNumericParameters()[3] << 8 | var3.getNumericParameters()[4];
                              C30.a().c(var12, var3.getNumericParameters()[0]);
                           }
                        } else {
                           C30.a().c(0, var3.getNumericParameters()[0]);
                           C30.a().a(this.questIconNames[var3.getNumericParameters()[1]], var3.getNumericParameters()[2], var3.getNumericParameters()[3], var3.getNumericParameters()[4]);
                        }
                     } else {
                        C30.a().c(0, var3.getNumericParameters()[0]);
                        C30.a().a(var3.getNumericParameters()[1], var3.getNumericParameters()[2], var3.getNumericParameters()[3], var3.getNumericParameters()[4], var3.getNumericParameters()[5]);
                     }

                     if (!var24) {
                        var2.setExecutionState((byte)5);
                     }
                     break label1202;
                  }

                  if (C30.a().C30_f477 && (var3.getNumericParameters()[0] == 12 || var3.getNumericParameters()[0] == 13)) {
                     var2.setExecutionState((byte)1);
                     break label1202;
                  }

                  if (var3.getNumericParameters()[0] != 16 && !C30.a().C30_f476) {
                     break label1202;
                  }
                  break;
               case 10:
                  if (var2.getExecutionState() != 5) {
                     if (var3.getNumericParameters()[0] == -1) {
                        this.eventEntityStates = new byte[1];
                        this.player.setCurrentDirection(GameUtils.parseByte(GameUtils.splitString(var3.getStringParameters()[1], ',')[0]));
                        this.player.a((byte)0, (byte)this.player.currentDirection);
                        this.player.setSecondaryState((byte)0, (short) GameUtils.parseByte(GameUtils.splitString(var3.getStringParameters()[2], ',')[0]));
                        this.eventEntityStates[0] = GameUtils.parseByte(GameUtils.splitString(var3.getStringParameters()[3], ',')[0]);
                     } else {
                        this.eventEntityIds = new short[var3.getNumericParameters()[0]];
                        this.eventEntityStates = new byte[var3.getNumericParameters()[0]];

                        for(var10 = 0; var10 < this.eventEntityIds.length; ++var10) {
                           this.eventEntityIds[var10] = GameUtils.parseShort(GameUtils.splitString(var3.getStringParameters()[0], ',')[var10]);
                           if (this.eventEntityIds[var10] != -1) {
                              this.mapManager.C25_f287[this.eventEntityIds[var10]].setCurrentDirection(GameUtils.parseByte(GameUtils.splitString(var3.getStringParameters()[1], ',')[var10]));
                              this.mapManager.C25_f287[this.eventEntityIds[var10]].setSecondaryState((byte)0, (short) GameUtils.parseByte(GameUtils.splitString(var3.getStringParameters()[2], ',')[var10]));
                              this.mapManager.C25_f287[this.eventEntityIds[var10]].a((byte)0);
                           } else {
                              this.player.setCurrentDirection(GameUtils.parseByte(GameUtils.splitString(var3.getStringParameters()[1], ',')[var10]));
                              this.player.setSecondaryState((byte)0, (short) GameUtils.parseByte(GameUtils.splitString(var3.getStringParameters()[2], ',')[var10]));
                              this.player.a((byte)0, (byte)this.player.currentDirection);
                           }

                           this.eventEntityStates[var10] = GameUtils.parseByte(GameUtils.splitString(var3.getStringParameters()[3], ',')[var10]);
                        }
                     }

                     this.animationCounter = 0;
                     var2.setExecutionState((byte)5);
                     break label1202;
                  }

                  if (var3.getNumericParameters()[0] == -1) {
                     if (this.player.getFacingDirection() == 0) {
                        this.player.a((byte)1, (byte)this.player.currentDirection);
                        break label1202;
                     }

                     --this.eventEntityStates[0];
                     if (this.eventEntityStates[0] > 0) {
                        break label1202;
                     }

                     this.player.a((byte)0, (byte)this.player.currentDirection);
                     if (this.player.C53_f793[0] != 2 && this.player.C53_f793[1] != 2) {
                        this.player.setSecondaryState((byte)0, (short)4);
                     } else {
                        this.player.setSecondaryState((byte)0, (short)8);
                     }

                     var2.setExecutionState((byte)1);
                     break label1202;
                  }

                  for(var10 = 0; var10 < this.eventEntityIds.length; ++var10) {
                     if (this.eventEntityIds[var10] != -1 && this.mapManager.C25_f287[this.eventEntityIds[var10]].getFacingDirection() == 0 || this.eventEntityIds[var10] == -1 && this.player.getFacingDirection() == 0) {
                        if (this.eventEntityStates[var10] > 0) {
                           if (this.eventEntityIds[var10] != -1) {
                              this.mapManager.C25_f287[this.eventEntityIds[var10]].a((byte)3);
                           } else {
                              this.player.a((byte)1, (byte)this.player.currentDirection);
                           }
                        }
                     } else {
                        --this.eventEntityStates[var10];
                        if (this.eventEntityStates[var10] <= 0) {
                           ++this.animationCounter;
                           this.eventEntityStates[var10] = 0;
                           if (this.eventEntityIds[var10] != -1) {
                              this.mapManager.C25_f287[this.eventEntityIds[var10]].a((byte)0);
                              this.mapManager.C25_f287[this.eventEntityIds[var10]].setSecondaryState((byte)0, (short)4);
                           } else {
                              this.player.a((byte)0, (byte)this.player.currentDirection);
                              if (this.player.C53_f793[0] != 2 && this.player.C53_f793[1] != 2) {
                                 this.player.setSecondaryState((byte)0, (short)4);
                              } else {
                                 this.player.setSecondaryState((byte)0, (short)8);
                              }
                           }
                        }
                     }
                  }

                  if (this.animationCounter < this.eventEntityIds.length) {
                     break label1202;
                  }
                  break;
               case 11:
                  if (var2.getExecutionState() != 5) {
                     var24 = false;
                     if (var3.getNumericParameters()[6] == 0) {
                        var24 = true;
                     }

                     CameraController.getInstance().setCameraLag(var3.getNumericParameters()[7]);
                     if (var3.getNumericParameters()[2] == 1) {
                        CameraController.getInstance().setFixedPosition(var3.getNumericParameters()[4], var3.getNumericParameters()[5], var24);
                     } else if (var3.getNumericParameters()[2] == 0) {
                        if (var3.getNumericParameters()[3] == -1) {
                           CameraController.getInstance().setFollowEntity(this.player, var24);
                        } else {
                           CameraController.getInstance().setFollowEntity(this.mapManager.C25_f287[var3.getNumericParameters()[3]], var24);
                        }
                     }

                     this.player.a((byte)0, (byte)this.player.currentDirection);
                     var2.setExecutionState((byte)5);
                     break label1202;
                  }

                  if (!CameraController.getInstance().isLocked()) {
                     break label1202;
                  }
                  break;
               case 12:
                  ++this.animationCounter;
                  if (var2.getExecutionState() != 5) {
                     var2.setExecutionState((byte)5);
                     break label1202;
                  }

                  if (this.animationCounter < var3.getNumericParameters()[0]) {
                     break label1202;
                  }

                  this.animationCounter = 0;
                  break;
               case 13:
                  if (GameUtils.checkCollisionWithShortArray(var3.getNumericParameters()[0], var3.getNumericParameters()[1], var3.getNumericParameters()[2], var3.getNumericParameters()[3], this.player.worldX, this.player.worldY, this.player.sprite.getCurrentFrameEvents())) {
                     var2.setExecutionState((byte)1);
                     this.player.a((byte)0, (byte)this.player.currentDirection);
                  } else {
                     var2.setExecutionState((byte)6);
                  }
                  break label1202;
               case 14:
                  var2.setExecutionState((byte)3);
                  break label1202;
               case 16:
                  if (var3.getNumericParameters()[0] == C25.C25_f318) {
                     isQuestTriggered = true;
                     if (isQuestReady) {
                        isQuestReady = false;
                        var2.setExecutionState((byte)2);
                     }
                  } else {
                     var2.setExecutionState((byte)6);
                  }
                  break label1202;
               case 17:
                  if (var2.getExecutionState() != 5) {
                     if (var3.getNumericParameters()[0] == 0) {
                        if (this.player.a((int)var3.getNumericParameters()[1], var3.getNumericParameters()[2], (byte)0)) {
                           var27 = ResourceManager.gameDatabase[4][var3.getNumericParameters()[1]][0];
                           this.gameEngine.gameController.a((String)("Đạt được: " + GameEngineBase.getLocalizedText((int)var27)), var3.getNumericParameters()[2]);
                           this.player.c(var3.getNumericParameters()[1], var3.getNumericParameters()[2], (byte)0);
                        } else {
                           this.gameEngine.gameController.b("Ba lô đã đủ đạo cụ này");
                        }
                     } else if (this.player.b((int)var3.getNumericParameters()[1], (int)var3.getNumericParameters()[2], (byte)0)) {
                        var27 = ResourceManager.gameDatabase[4][var3.getNumericParameters()[1]][0];
                        this.gameEngine.gameController.a((String)("Mất: " + GameEngineBase.getLocalizedText((int)var27)), var3.getNumericParameters()[2]);
                        this.player.d(var3.getNumericParameters()[1], var3.getNumericParameters()[2], (byte)0);
                     }

                     var2.setExecutionState((byte)5);
                     break label1202;
                  }

                  if (!this.gameEngine.gameController.aA()) {
                     break label1202;
                  }
                  break;
               case 18:
                  if (var2.getExecutionState() != 5) {
                     if (var3.getNumericParameters()[0] == 0) {
                        if (this.player.a((int)var3.getNumericParameters()[1], var3.getNumericParameters()[2], (byte)2)) {
                           var27 = ResourceManager.gameDatabase[3][var3.getNumericParameters()[1]][0];
                           this.gameEngine.gameController.a((String)("Đạt được: " + GameEngineBase.getLocalizedText((int)var27)), var3.getNumericParameters()[2]);
                           this.player.c(var3.getNumericParameters()[1], var3.getNumericParameters()[2], (byte)2);
                        } else {
                           this.gameEngine.gameController.b("Ba lô đã đủ đạo cụ này");
                        }
                     } else if (var3.getNumericParameters()[0] == 1) {
                        var27 = ResourceManager.gameDatabase[3][var3.getNumericParameters()[1]][0];
                        this.gameEngine.gameController.a((String)("Mất: " + GameEngineBase.getLocalizedText((int)var27)), var3.getNumericParameters()[2]);
                        this.player.d(var3.getNumericParameters()[1], var3.getNumericParameters()[2], (byte)2);
                     }

                     var2.setExecutionState((byte)5);
                     break label1202;
                  }

                  if (!this.gameEngine.gameController.aA()) {
                     break label1202;
                  }
                  break;
               case 19:
                  if (var2.getExecutionState() != 5) {
                     var27 = ResourceManager.gameDatabase[5][var3.getNumericParameters()[0]][0];
                     this.gameEngine.gameController.a((String)("Đạt được: " + GameEngineBase.getLocalizedText((int)var27)), var3.getNumericParameters()[1]);
                     if ((var12 = this.player.d(var3.getNumericParameters()[0], var3.getNumericParameters()[1])) != -1) {
                        if (var12 == 1) {
                           this.gameEngine.gameController.b("Ba lô đã đủ loại đạo cụ này");
                        } else {
                           this.player.c((int)var3.getNumericParameters()[0], (int)var3.getNumericParameters()[1]);
                        }
                     } else if (var3.getNumericParameters()[0] == 0) {
                        this.player.e(var3.getNumericParameters()[0], -1);
                     } else {
                        this.player.j(var3.getNumericParameters()[0]);
                     }

                     var2.setExecutionState((byte)5);
                     break label1202;
                  }

                  if (!this.gameEngine.gameController.aA()) {
                     break label1202;
                  }
                  break;
               case 20:
                  if (var2.getExecutionState() != 5) {
                     if (var3.getNumericParameters()[0] == 1) {
                        this.gameEngine.gameController.b("Mất: " + var3.getStringParameters()[0]);
                        this.player.C53_f797[var3.getNumericParameters()[1]] = false;
                     } else {
                        this.gameEngine.gameController.b("Đạt được: " + var3.getStringParameters()[0]);
                        this.player.C53_f797[var3.getNumericParameters()[1]] = true;
                     }

                     var2.setExecutionState((byte)5);
                     break label1202;
                  }

                  if (!this.gameEngine.gameController.aA()) {
                     break label1202;
                  }
                  break;
               case 21:
                  C25.C25_f321 = false;
                  C25.C25_f322 = var3.getNumericParameters()[2];
                  if (var3.getNumericParameters()[1] == 1) {
                     C25.C25_f323 = var3.getNumericParameters()[3];
                     C25.C25_f324 = var3.getNumericParameters()[4];
                     C25.C25_f325 = var3.getNumericParameters()[5];
                     C25.C25_f326 = var3.getNumericParameters()[6];
                  }
                  break label1202;
               case 22:
                  C25.C25_f321 = true;
                  C25.C25_f320 = (byte)var3.getNumericParameters()[1];
                  C25.B().C25_f293 = var3.getNumericParameters()[2];
                  C25.B().C25_f294 = var3.getNumericParameters()[3];
                  C25.C25_f325 = var3.getNumericParameters()[4];
                  C25.C25_f326 = var3.getNumericParameters()[5];
                  C25.B().C25_f295 = -1;
                  break label1202;
               case 23:
                  this.questStates[C25.e(var3.getNumericParameters()[0], var3.getNumericParameters()[1])][var3.getNumericParameters()[2]] = 3;
                  if (var3.getNumericParameters()[0] == this.mapManager.C25_f290 && var3.getNumericParameters()[1] == this.mapManager.C25_f291) {
                     this.eventScripts[var3.getNumericParameters()[2]].setExecutionState((byte)3);
                     if (this.activeEvents.size() > 0) {
                        this.activeEvents.removeElement(this.eventScripts[var3.getNumericParameters()[2]]);
                        --var1;
                     }
                  }
                  break label1202;
               case 24:
                  if (var2.getExecutionState() != 5) {
                     C30.a().c(0, 11);
                     C30.a().a(var3.getNumericParameters()[0], var3.getNumericParameters()[1], var3.getNumericParameters()[2]);
                     var2.setExecutionState((byte)5);
                     break label1202;
                  }

                  if (!C30.a().C30_f476) {
                     break label1202;
                  }
                  break;
               case 25:
                  isQuestActive = var3.getNumericParameters()[0] == 0;
                  break label1202;
               case 29:
                  if (var2.getExecutionState() != 5) {
                     var27 = var3.getNumericParameters()[0];
                     if (var3.getNumericParameters()[0] == -1) {
                        var27 = 1;
                     }

                     this.eventEntityIds = new short[var27];
                     this.eventEntityOffsetX = new short[var27];
                     this.eventEntityOffsetY = new short[var27];
                     this.eventEntityMoveX = new short[var27];
                     this.eventEntityMoveY = new short[var27];

                     for(var12 = 0; var12 < this.eventEntityIds.length; ++var12) {
                        this.eventEntityIds[var12] = GameUtils.parseShort(GameUtils.splitString(var3.getStringParameters()[0], ',')[var12]);
                        this.eventEntityOffsetX[var12] = GameUtils.parseShort(GameUtils.splitString(var3.getStringParameters()[1], ',')[var12]);
                        this.eventEntityOffsetY[var12] = GameUtils.parseShort(GameUtils.splitString(var3.getStringParameters()[2], ',')[var12]);
                        this.eventEntityMoveX[var12] = GameUtils.parseShort(GameUtils.splitString(var3.getStringParameters()[3], ',')[var12]);
                        this.eventEntityMoveY[var12] = GameUtils.parseShort(GameUtils.splitString(var3.getStringParameters()[4], ',')[var12]);
                     }

                     var2.setExecutionState((byte)5);
                     break label1202;
                  }

                  var24 = true;

                  for(var18 = 0; var18 < this.eventEntityIds.length; ++var18) {
                     if (this.eventEntityMoveX[var18] > 0 || this.eventEntityMoveY[var18] > 0) {
                        var24 = false;
                        --this.eventEntityMoveX[var18];
                        --this.eventEntityMoveY[var18];
                        if (var3.getNumericParameters()[0] == -1) {
                           var12 = this.player.getWorldX() + this.eventEntityOffsetX[var18];
                           var15 = this.player.getWorldY() + this.eventEntityOffsetY[var18];
                           this.player.setWorldPosition(var12, var15);
                           if (this.player.attachedObject != null) {
                              this.player.attachedObject.setWorldPosition(var12, var15);
                           }
                        } else {
                           var12 = this.mapManager.C25_f287[this.eventEntityIds[var18]].getWorldX() + this.eventEntityOffsetX[var18];
                           var15 = this.mapManager.C25_f287[this.eventEntityIds[var18]].getWorldY() + this.eventEntityOffsetY[var18];
                           this.mapManager.C25_f287[this.eventEntityIds[var18]].setWorldPosition(var12, var15);
                           if (this.mapManager.C25_f287[this.eventEntityIds[var18]].attachedObject != null) {
                              this.mapManager.C25_f287[this.eventEntityIds[var18]].attachedObject.setWorldPosition(var12, var15);
                           }
                        }
                     }
                  }

                  if (var24) {
                     var2.setExecutionState((byte)1);
                  }
                  break label1202;
               case 30:
                  if (var2.getExecutionState() != 5) {
                     this.eventEntityIds = new short[var3.getNumericParameters()[0]];
                     String[] var23 = GameUtils.splitString(var3.getStringParameters()[2], ',');

                     for(var12 = 0; var12 < this.eventEntityIds.length; ++var12) {
                        this.eventEntityIds[var12] = GameUtils.parseShort(var23[var12]);
                     }

                     String[][] var21 = new String[this.eventEntityIds.length][];
                     String[][] var29 = new String[this.eventEntityIds.length][];

                     for(var18 = 0; var18 < var29.length; ++var18) {
                        var21[var18] = GameUtils.splitString(GameUtils.splitString(var3.getStringParameters()[0], '#')[var18], ',');
                        var29[var18] = GameUtils.splitString(GameUtils.splitString(var3.getStringParameters()[1], '#')[var18], ',');
                     }

                     this.eventEntityPathX = new short[this.eventEntityIds.length][];
                     this.eventEntityPathY = new short[this.eventEntityIds.length][];

                     for(var18 = 0; var18 < this.eventEntityIds.length; ++var18) {
                        this.eventEntityPathX[var18] = new short[var21[var18].length];
                        this.eventEntityPathY[var18] = new short[var29[var18].length];

                        for(var22 = 0; var22 < this.eventEntityPathX[var18].length; ++var22) {
                           this.eventEntityPathX[var18][var22] = GameUtils.parseShort(var21[var18][var22]);
                           this.eventEntityPathY[var18][var22] = GameUtils.parseShort(var29[var18][var22]);
                        }
                     }

                     this.animationCounter = 0;
                     var2.setExecutionState((byte)5);
                     break label1202;
                  }

                  for(var10 = 0; var10 < this.eventEntityIds.length; ++var10) {
                     this.mapManager.C25_f287[this.eventEntityIds[var10]].setWorldPosition(this.eventEntityPathX[var10][this.animationCounter], this.eventEntityPathY[var10][this.animationCounter]);
                  }

                  ++this.animationCounter;
                  if (this.animationCounter < this.eventEntityPathX[0].length) {
                     break label1202;
                  }
                  break;
               case 31:
                  if (var2.getExecutionState() != 5) {
                     if (var3.getNumericParameters()[0] == 0) {
                        if (var3.getNumericParameters()[1] == 0) {
                           this.player.s(var3.getNumericParameters()[2]);
                           this.gameEngine.gameController.b("Đạt được: " + var3.getNumericParameters()[2] + " kim tiền");
                        } else if (var3.getNumericParameters()[1] == 1) {
                           this.player.v(var3.getNumericParameters()[2]);
                           this.gameEngine.gameController.b("Đạt được: " + var3.getNumericParameters()[2] + "Huy hiệu");
                        }
                     } else if (var3.getNumericParameters()[0] == 1) {
                        if (var3.getNumericParameters()[1] == 0) {
                           this.player.s(-var3.getNumericParameters()[2]);
                           this.gameEngine.gameController.b("Mất: " + var3.getNumericParameters()[2] + " kim tiền");
                        } else if (var3.getNumericParameters()[1] == 1) {
                           this.player.v(-var3.getNumericParameters()[2]);
                           this.gameEngine.gameController.b("Mất: " + var3.getNumericParameters()[2] + " huy hiệu");
                        }
                     }

                     var2.setExecutionState((byte)5);
                     break label1202;
                  }

                  if (!this.mapManager.gameController.aA()) {
                     break label1202;
                  }
                  break;
               case 32:
                  this.mapManager.D();
                  C29.B().C29_f397 = var3.getNumericParameters()[0];
                  C29.B().C29_f398 = (byte)var3.getNumericParameters()[1];
                  this.mapManager.N();
                  this.player.a((byte)0, (byte)this.player.currentDirection);
                  var2.setExecutionState((byte)1);
                  C25 var10000 = this.mapManager;
                  if ((var14 = C25.a(var2.getEventId(), (byte)1)) != -1) {
                     this.mapManager.C25_f342.playBackgroundMusic(var14, 1);
                  } else {
                     this.mapManager.C25_f342.playBackgroundMusic(4, 1);
                  }

                  GameScreenManager.getInstance().changeState((byte)12);
                  break label1202;
               case 33:
                  if ((questState = (byte)var3.getNumericParameters()[0]) == 2) {
                     questState = 1;
                     TileMapRenderer.getInstance().refreshTileset();
                     var12 = 0;

                     while(true) {
                        if (var12 >= C25.B().C25_f287.length) {
                           break label1202;
                        }

                        if (this.mapManager.C25_f287[var12].isVisible()) {
                           this.mapManager.C25_f287[var12].p();
                        }

                        ++var12;
                     }
                  }

                  if (questState != 3) {
                     break label1202;
                  }

                  questState = 0;
                  TileMapRenderer.getInstance().refreshTileset();
                  var12 = 0;

                  while(true) {
                     if (var12 >= C25.B().C25_f287.length) {
                        break label1202;
                     }

                     if (this.mapManager.C25_f287[var12].isVisible()) {
                        C25.B().C25_f287[var12].p();
                     }

                     ++var12;
                  }
               case 34:
                  if (var2.getExecutionState() != 5) {
                     questEffectImage = GameUtils.loadImage("/data/tex/", this.questIconNames[var3.getNumericParameters()[0]]);
                     this.effectImageX = var3.getNumericParameters()[1];
                     this.effectImageY = var3.getNumericParameters()[2];
                     this.effectImageDeltaY = var3.getNumericParameters()[3];
                     this.animationCounter = var3.getNumericParameters()[4];
                     var2.setExecutionState((byte)5);
                     break label1202;
                  }

                  --this.animationCounter;
                  this.effectImageY -= this.effectImageDeltaY;
                  if (this.animationCounter > 0) {
                     break label1202;
                  }

                  this.animationCounter = 0;
                  break;
               case 35:
                  if (var2.getExecutionState() == 5) {
                     int var10001 = this.dialogId;
                     if ((var12 = this.gameEngine.gameController.c(this.dialogType)) != -1) {
                        var2.setCurrentCommandIndex((byte)(this.dialogOptions[var12] - 2));
                        var2.setExecutionState((byte)1);
                     }
                     break label1202;
                  }

                  this.dialogId = var3.getNumericParameters()[0];
                  this.dialogType = var3.getNumericParameters()[1];
                  this.dialogTexts = GameUtils.splitString(var3.getStringParameters()[0], ',');
                  this.dialogOptions = new byte[GameUtils.splitString(var3.getStringParameters()[1], ',').length];
                  String var11 = var3.getStringParameters()[2];

                  for(var15 = 0; var15 < this.dialogOptions.length; ++var15) {
                     this.dialogOptions[var15] = GameUtils.parseByte(GameUtils.splitString(var3.getStringParameters()[1], ',')[var15]);
                  }

                  this.gameEngine.gameController.a(this.dialogType, this.dialogId, this.dialogTexts, var11);
                  var2.setExecutionState((byte)5);
                  break label1202;
               case 36:
                  if (var2.getExecutionState() != 5) {
                     var5 = this.player.z();
                     if (var3.getNumericParameters()[0] == 0) {
                        if (var5 == 0) {
                           this.player.a(var3.getNumericParameters()[1], var3.getNumericParameters()[2], (short)-1, (byte)var3.getNumericParameters()[4], (byte)var3.getNumericParameters()[3], (byte)-1, new int[]{1, var3.getNumericParameters()[5], var3.getNumericParameters()[6]});
                        } else if (var5 == 1) {
                           this.gameEngine.gameController.b("Ba lô đã đủ, đã để vào ngân hàng");
                           var19 = C41.b(var3.getNumericParameters()[1], var3.getNumericParameters()[2], var3.getNumericParameters()[3]);
                           this.player.a(var3.getNumericParameters()[1], var3.getNumericParameters()[2], (short)-1, (byte)var3.getNumericParameters()[4], (byte)var3.getNumericParameters()[3], (byte)-1, var19, 0, 0, new int[]{1, var3.getNumericParameters()[5], var3.getNumericParameters()[6]});
                        } else {
                           this.gameEngine.gameController.b("Không có không gian, đã phóng sinh");
                        }
                     } else if (var3.getNumericParameters()[0] == 1) {
                        this.player.o(var3.getNumericParameters()[1]);
                     }

                     var2.setExecutionState((byte)5);
                     break label1202;
                  }

                  if (!this.gameEngine.gameController.aA()) {
                     break label1202;
                  }
                  break;
               case 37:
                  C29.B().a(new int[][]{{var3.getNumericParameters()[0], var3.getNumericParameters()[1], var3.getNumericParameters()[2]}});
                  break label1202;
               case 38:
                  isQuestTriggered = false;

                  for(var15 = 0; var15 < GameUtils.splitString(var3.getStringParameters()[0], ',').length; ++var15) {
                     if (GameUtils.parseByte(GameUtils.splitString(var3.getStringParameters()[0], ',')[var15]) == C25.C25_f318) {
                        isQuestTriggered = true;
                        if (isQuestReady) {
                           C25.C25_f318 = -1;
                           var5 = GameUtils.parseByte(GameUtils.splitString(var3.getStringParameters()[1], ',')[var15]);
                           var2.setCurrentCommandIndex((byte)(var5 - 1));
                           C25.B().D();
                           isQuestTriggered = false;
                           isQuestReady = false;
                        }
                        break;
                     }
                  }

                  var2.setExecutionState((byte)6);
                  break label1202;
               case 39:
                  var15 = 0;

                  while(true) {
                     if (var15 >= this.player.C53_f778) {
                        break label1202;
                     }

                     this.player.C53_f777[var15].J();
                     ++var15;
                  }
               case 40:
                  if (var2.getExecutionState() != 5) {
                     this.gameEngine.gameController.c(var3.getStringParameters()[0]);
                     var2.setExecutionState((byte)5);
                     break label1202;
                  }

                  if (!this.gameEngine.gameController.aB()) {
                     break label1202;
                  }
                  break;
               case 41:
                  var2.setCurrentCommandIndex((byte)(var3.getNumericParameters()[0] - 2));
                  break label1202;
               case 42:
                  var2.setExecutionState((byte)4);
                  break label1202;
               case 45:
                  if (var2.getExecutionState() != 5) {
                     this.gameEngine.gameController.c(var3.getStringParameters()[0]);
                     questDialogState = (byte)var3.getNumericParameters()[0];
                     var2.setExecutionState((byte)5);
                     break label1202;
                  }

                  if (!this.gameEngine.gameController.aB()) {
                     break label1202;
                  }
                  break;
               case 46:
                  if (var2.getExecutionState() != 5) {
                     this.gameEngine.gameController.K();
                     this.gameEngine.gameController.a(var3.getStringParameters()[0]);
                     var2.setExecutionState((byte)5);
                     break label1202;
                  }

                  if (this.gameEngine.gameController.C9_f131 == 0) {
                     if (this.gameEngine.isKeyPressed(196640)) {
                        this.gameEngine.gameController.C9_f131 = 1;
                        this.gameEngine.gameController.a("Đang lưu...");
                        this.gameEngine.gameController.M();
                     } else if (this.gameEngine.isKeyPressed(262144)) {
                        var2.setExecutionState((byte)1);
                        this.gameEngine.gameController.L();
                        this.gameEngine.gameController.C9_f131 = 0;
                     }
                     break label1202;
                  }

                  if (this.gameEngine.gameController.C9_f131 == 1) {
                     this.questStates[C25.e(this.mapManager.C25_f290, this.mapManager.C25_f291)][var2.getEventId()] = 3;
                     if (((C25)this.gameEngine).I()) {
                        this.gameEngine.gameController.a("Lưu thành công");
                        this.gameEngine.gameController.C9_f131 = 2;
                     }
                     break label1202;
                  }

                  if (this.gameEngine.gameController.C9_f131 != 2) {
                     break label1202;
                  }

                  this.gameEngine.gameController.L();
                  this.gameEngine.gameController.C9_f131 = 0;
                  break;
               case 47:
                  if (this.selectOption != -1) {
                     var2.setCurrentCommandIndex((byte)(var3.getNumericParameters()[this.selectOption] - 2));
                  }
                  break label1202;
               case 48:
                  if (var2.getExecutionState() != 5) {
                     this.dialogManager.a(var3.getNumericParameters()[1], var3.getNumericParameters()[2]);
                     this.dialogManager.a((byte)(var3.getNumericParameters()[0] / 10 - 1), var3.getStringParameters()[0], var3.getNumericParameters()[0] % 10);
                     if (var3.getNumericParameters()[5] == 1) {
                        this.dialogManager.a(true);
                     }

                     this.dialogManager.b(var3.getNumericParameters()[3], var3.getNumericParameters()[4]);
                     var2.setExecutionState((byte)5);
                     break label1202;
                  }

                  if (!this.dialogManager.e()) {
                     var2.setExecutionState((byte)1);
                     break label1202;
                  }

                  if (!C39.C39_f608 || !this.gameEngine.isKeyPressed(1) && !this.gameEngine.isPointerClickInBounds(80, 294, 80, 18)) {
                     break label1202;
                  }

                  this.dialogManager.b();
                  if (C39.C39_f610) {
                     break label1202;
                  }

                  C30.a().C30_f472 = -1;
                  this.dialogManager.c();
                  break;
               case 49:
                  if (var2.getExecutionState() == 5) {
                     if ((var15 = this.gameEngine.gameController.aG()) != -1) {
                        if (var15 == 0 && var2.getFirstCommand().getNumericParameters()[1] == 1) {
                           questFlags[questFlagCount][1] = 1;
                           ++questFlagCount;
                        }

                        var2.setCurrentCommandIndex((byte)(this.dialogOptions[var15] - 2));
                        var2.setExecutionState((byte)1);
                     }
                     break label1202;
                  }

                  this.dialogOptionIds = new int[2];
                  this.dialogOptionTypes = new int[2];
                  this.dialogOptionTexts = new String[2];
                  this.dialogTexts = new String[2];

                  for(var15 = 0; var15 < 2; ++var15) {
                     this.dialogOptionIds[var15] = var3.getNumericParameters()[var15 << 1];
                     this.dialogOptionTypes[var15] = var3.getNumericParameters()[(var15 << 1) + 1];
                     this.dialogOptionTexts[var15] = var3.getStringParameters()[var15];
                  }

                  this.dialogOptions = new byte[GameUtils.splitString(var3.getStringParameters()[2], ',').length];

                  for(var15 = 0; var15 < this.dialogOptions.length; ++var15) {
                     this.dialogOptions[var15] = GameUtils.parseByte(GameUtils.splitString(var3.getStringParameters()[2], ',')[var15]);
                     this.dialogTexts[var15] = GameUtils.splitString(var3.getStringParameters()[3], ',')[var15];
                  }

                  this.gameEngine.gameController.a(this.dialogOptionIds, this.dialogOptionTypes, this.dialogOptionTexts, this.dialogTexts);
                  var2.setExecutionState((byte)5);
                  break label1202;
               case 50:
                  if (var3.getNumericParameters()[0] == 0) {
                     this.player.v();
                  } else {
                     this.player.u();
                  }
                  break label1202;
               case 51:
                  this.mapManager.gameController.aE();
                  this.dialogManager.a(var3.getNumericParameters()[1], var3.getNumericParameters()[2]);
                  this.dialogManager.a((byte)(var3.getNumericParameters()[0] / 10 - 1), var3.getStringParameters()[0], var3.getNumericParameters()[0] % 10);
                  this.dialogManager.b(var3.getNumericParameters()[3], var3.getNumericParameters()[4]);
                  break label1202;
               case 52:
                  if (var3.getNumericParameters()[0] == 0) {
                     this.isPlayerControl = true;
                  } else {
                     this.isPlayerControl = false;
                  }

                  if (var3.getNumericParameters()[1] == 0) {
                     isMapControl = true;
                  } else {
                     isMapControl = false;
                  }
                  break label1202;
               case 53:
                  if (var2.getExecutionState() != 5) {
                     if (var3.getNumericParameters()[1] == 0) {
                        this.player.b((byte)((byte)var3.getNumericParameters()[0]), (byte)((byte)var3.getNumericParameters()[1]), (byte)2);

                        for(var15 = 0; var15 < C25.B().C25_f287.length; ++var15) {
                           if (C25.B().C25_f287[var15].C18_f223 == 0 && C25.B().C25_f287[var15].C18_f225 == 1) {
                              C25.B().C25_f287[var15].w();
                           }
                        }
                     } else if (var3.getNumericParameters()[1] == 1) {
                        this.player.b((byte)((byte)var3.getNumericParameters()[0]), (byte)((byte)var3.getNumericParameters()[1]), (byte)1);
                     }

                     this.gameEngine.gameController.a(var3.getNumericParameters()[0]);
                     var2.setExecutionState((byte)5);
                     break label1202;
                  }

                  if (!this.mapManager.isKeyPressed(65537) && !this.gameEngine.isPointerClickInBounds(94, 200, 60, 18)) {
                     break label1202;
                  }

                  this.gameEngine.gameController.Y();
                  break;
               case 54:
                  int[][] var26 = new int[var19 = var3.getNumericParameters()[0]][3];

                  for(var22 = 0; var22 < var19; ++var22) {
                     var26[var22][0] = GameUtils.parseInt(GameUtils.splitString(var3.getStringParameters()[0], ',')[var22]);
                     var26[var22][1] = GameUtils.parseInt(GameUtils.splitString(var3.getStringParameters()[1], ',')[var22]);
                     var26[var22][2] = GameUtils.parseInt(GameUtils.splitString(var3.getStringParameters()[2], ',')[var22]);
                  }

                  C29.B().a(var26);
                  break label1202;
               case 55:
                  if (var3.getNumericParameters()[0] == 0) {
                     if (this.questSpecialObject == null) {
                        this.questSpecialObject = new GameObject();
                        this.questSpecialObject.loadSpriteSet(340, false);
                        this.questSpecialObject.activate();
                        this.questSpecialObjectId = C25.e(var3.getNumericParameters()[3], var3.getNumericParameters()[4]);
                     }

                     this.questSpecialObject.setWorldPosition(var3.getNumericParameters()[1], var3.getNumericParameters()[2]);
                  } else if (var3.getNumericParameters()[0] == 1 && this.questSpecialObject != null) {
                     this.questSpecialObject.deactivate();
                     this.questSpecialObject = null;
                     this.questSpecialObjectId = -1;
                  }
                  break label1202;
               case 56:
                  var19 = var3.getNumericParameters()[1];
                  short var25;
                  if (var3.getNumericParameters()[0] == 0) {
                     var18 = 0;

                     while(true) {
                        if (var18 >= var19) {
                           break label1202;
                        }

                        var25 = GameUtils.parseShort(GameUtils.splitString(var3.getStringParameters()[0], ',')[var18]);
                        var14 = GameUtils.parseByte(GameUtils.splitString(var3.getStringParameters()[1], ',')[var18]);
                        this.mapManager.C25_f287[var25].setCurrentDirection(var14);
                        if (this.mapManager.C25_f287[var25].C18_f225 == 1) {
                           this.mapManager.C25_f287[var25].a((byte)0);
                        }

                        this.mapManager.C25_f287[var25].activate();
                        this.mapManager.a(var25, 1, (byte)1, true);
                        this.mapManager.a(var25, 2, var14, true);
                        this.mapManager.C25_f287[var25].t();
                        ++var18;
                     }
                  }

                  if (var3.getNumericParameters()[0] != 1) {
                     break label1202;
                  }

                  var18 = 0;

                  while(true) {
                     if (var18 >= var19) {
                        break label1202;
                     }

                     var25 = GameUtils.parseShort(GameUtils.splitString(var3.getStringParameters()[0], ',')[var18]);
                     if (this.mapManager.C25_f287[var25].C18_f225 == 1) {
                        this.mapManager.C25_f287[var25].a((byte)0);
                     }

                     this.mapManager.C25_f287[var25].deactivate();
                     this.mapManager.a(var25, 1, (byte)0, true);
                     this.mapManager.C25_f287[var25].t();
                     ++var18;
                  }
               case 58:
                  if (((C18)this.player.followTarget).getFacingDirection() == 1 && ((C18)this.player.followTarget).sprite.isAtLastFrame()) {
                     ((C18)this.player.followTarget).a((byte)0);
                     this.mapManager.C25_f287[var3.getNumericParameters()[0]].setWorldPosition(var3.getNumericParameters()[1], var3.getNumericParameters()[2]);
                     if ((C18)this.mapManager.C25_f287[var3.getNumericParameters()[0]].followTarget != null) {
                        ((C18)this.mapManager.C25_f287[var3.getNumericParameters()[0]].followTarget).s();
                        this.mapManager.C25_f287[var3.getNumericParameters()[0]].setFollowTarget((GameEntity)null);
                     }
                  }
                  break label1202;
               case 60:
                  if (var2.getExecutionState() != 5) {
                     this.eventEntityIds = new short[var3.getNumericParameters()[0]];

                     for(var15 = 0; var15 < this.eventEntityIds.length; ++var15) {
                        this.eventEntityIds[var15] = GameUtils.parseShort(GameUtils.splitString(var3.getStringParameters()[0], ',')[var15]);
                        this.mapManager.C25_f287[this.eventEntityIds[var15]].a(GameUtils.parseByte(GameUtils.splitString(var3.getStringParameters()[1], ',')[var15]));
                        if (this.mapManager.C25_f287[this.eventEntityIds[var15]].C18_f223 == 0 && this.mapManager.C25_f287[this.eventEntityIds[var15]].C18_f225 == 6 && this.mapManager.C25_f287[this.eventEntityIds[var15]].getFacingDirection() == 2) {
                           short var10002 = this.eventEntityIds[var15];
                           C25.B().C25_f285.moveEntityToForeground(C25.B().C25_f287[var10002], 2);
                        }
                     }

                     this.animationCounter = 0;
                     var2.setExecutionState((byte)5);
                     break label1202;
                  }

                  for(var15 = 0; var15 < this.eventEntityIds.length; ++var15) {
                     if (this.mapManager.C25_f287[this.eventEntityIds[var15]].isAnimationComplete()) {
                        ++this.animationCounter;
                     }
                  }

                  if (this.animationCounter < this.eventEntityIds.length) {
                     break label1202;
                  }

                  this.animationCounter = 0;
                  break;
               case 62:
                  int[] var13 = new int[GameUtils.splitString(var3.getStringParameters()[0], ',').length];
                  var22 = -1;
                  var2.setExecutionState((byte)6);

                  for(var18 = 0; var18 < var13.length; ++var18) {
                     var13[var18] = GameUtils.parseInt(GameUtils.splitString(var3.getStringParameters()[0], ',')[var18]);
                     if (this.mapManager.C25_f287[var13[var18]].getFacingDirection() == 2) {
                        var22 = var13[var18];
                        break;
                     }
                  }

                  if (var22 < 0) {
                     break label1202;
                  }

                  if (var22 == var3.getNumericParameters()[0]) {
                     var2.setCurrentCommandIndex((byte)(var3.getNumericParameters()[1] - 2));
                     var2.setExecutionState((byte)1);
                     break label1202;
                  }

                  var2.setCurrentCommandIndex((byte)(var3.getNumericParameters()[2] - 2));
                  break;
               case 63:
                  if (var3.getNumericParameters()[0] == 0) {
                     this.player.h(var3.getNumericParameters()[1]);
                  } else {
                     this.player.t();
                  }

                  this.isEventVisible = var3.getNumericParameters()[2] != 0;
                  break label1202;
               case 64:
                  if (var3.getNumericParameters()[0] == 0) {
                     this.mapManager.l(var3.getNumericParameters()[1]);
                     if (var3.getNumericParameters()[2] == -1) {
                        this.mapManager.a((GameObject)this.player);
                     } else {
                        this.mapManager.a((GameObject)this.mapManager.C25_f287[var3.getNumericParameters()[2]]);
                     }
                  } else {
                     this.mapManager.E();
                  }
                  break label1202;
               case 65:
                  if (var2.getExecutionState() != 5 && !paymentActive) {
                     this.gameEngine.changeState((byte)100);
                     var2.setExecutionState((byte)5);
                     break label1202;
                  }

                  if (paymentActive) {
                     var2.setCurrentCommandIndex((byte)(var3.getNumericParameters()[0] - 2));
                  } else {
                     var2.setCurrentCommandIndex((byte)(var3.getNumericParameters()[1] - 2));
                  }
                  break;
               case 66:
                  GameEngineBase.actionType = (byte)var3.getNumericParameters()[0];
                  GameEngineBase.b(0, 3);
                  break label1202;
               case 67:
                  C25.C25_f319 = var3.getNumericParameters()[0];
                  break label1202;
               case 70:
                  if (var2.getExecutionState() != 5) {
                     isChangingState = false;
                     this.questChangeState = var3.getNumericParameters()[0];
                     switch(var3.getNumericParameters()[0]) {
                     case 0:
                     case 1:
                        this.mapManager.changeState((byte)1);
                        break;
                     case 2:
                        this.mapManager.changeState((byte)16);
                     }

                     var2.setExecutionState((byte)5);
                     break label1202;
                  }

                  if (!isChangingState) {
                     break label1202;
                  }

                  this.questChangeState = -1;
                  break;
               case 71:
                  if (this.player.C53_f783 >= var3.getNumericParameters()[0]) {
                     var2.setCurrentCommandIndex((byte)(var3.getNumericParameters()[1] - 2));
                  } else {
                     var2.setCurrentCommandIndex((byte)(var3.getNumericParameters()[2] - 2));
                  }
                  break label1202;
               case 72:
                  String[] var6 = GameUtils.splitString(var3.getStringParameters()[0], ',');
                  String[] var17 = GameUtils.splitString(var3.getStringParameters()[1], ',');
                  GameObject[] var20 = new GameObject[var6.length];
                  var10 = 0;

                  while(true) {
                     if (var10 >= var6.length) {
                        break label1202;
                     }

                     var20[var10] = new GameObject();
                     var20[var10].loadSpriteSet(259, false);
                     var20[var10].applyColorEffects();
                     if (GameUtils.parseInt(var6[var10]) == -1) {
                        var20[var10].setAnimation(GameUtils.parseByte(var17[var10]), (byte)-1, true);
                        var20[var10].activate();
                        var20[var10].setWorldPosition(this.player.getWorldX(), this.player.getWorldY() - 40);
                        var20[var10].setFollowTarget(this.player);
                     } else {
                        var20[var10].setAnimation(GameUtils.parseByte(var17[var10]), (byte)-1, true);
                        var20[var10].activate();
                        var20[var10].setWorldPosition(this.mapManager.C25_f287[GameUtils.parseInt(var6[var10])].getWorldX(), this.mapManager.C25_f287[GameUtils.parseInt(var6[var10])].getWorldY() - 40);
                        var20[var10].setFollowTarget(this.mapManager.C25_f287[GameUtils.parseInt(var6[var10])]);
                     }

                     effectObjects.addElement(var20[var10]);
                     ++var10;
                  }
               case 74:
                  if (((int[])this.player.C53_f788.elementAt(0))[1] > 0) {
                     var2.setCurrentCommandIndex((byte)(var3.getNumericParameters()[0] - 2));
                  } else {
                     var2.setCurrentCommandIndex((byte)(var3.getNumericParameters()[1] - 2));
                  }
                  break label1202;
               case 76:
                  this.questStates[C25.C25_f297[this.mapManager.C25_f290] + this.mapManager.C25_f291][var2.getEventId()] = 3;
                  C25.B().C25_f290 = var3.getNumericParameters()[0];
                  C25.B().C25_f291 = var3.getNumericParameters()[1];
                  C25.B().C25_f295 = -1;
                  this.mapManager.changeState((byte)29);
                  break label1202;
               case 77:
                  this.questStates[C25.e(var3.getNumericParameters()[0], var3.getNumericParameters()[1])][var3.getNumericParameters()[2]] = 4;
                  if (var3.getNumericParameters()[0] == this.mapManager.C25_f290 && var3.getNumericParameters()[1] == this.mapManager.C25_f291) {
                     this.eventScripts[var3.getNumericParameters()[2]].setExecutionState((byte)4);
                  }
                  break label1202;
               case 80:
                  if (var2.getExecutionState() != 5) {
                     if (var3.getNumericParameters()[0] == 0) {
                        this.animationCounter = 0;
                        this.questEffectCounter = 4;
                     } else if (var3.getNumericParameters()[0] == 1) {
                        GameScreenManager.getInstance().battleStartTime = GameScreenManager.getInstance().pauseStartTime;
                        long var16;
                        if (GameUtils.convertTime(var16 = GameScreenManager.getInstance().battleStartTime - GameScreenManager.getInstance().gameStartTime)[2] <= 60L) {
                           if ((var14 = this.player.z()) == 0) {
                              this.gameEngine.gameController.b("Đạt được #2Lục hành điểu");
                              this.player.a(54, 5, (short)-1, (byte)2, (short)-1, (byte)-1, new int[]{1, 30, 45});
                           } else if (var14 == 1) {
                              this.gameEngine.gameController.b("Đạt được #2Lục hành điểu#0 ba lô đã đủ, đã để vào ngân hàng");
                              var10 = GameUtils.getRandomInRange(ResourceManager.gameDatabase[0][54][3], ResourceManager.gameDatabase[0][54][3]);
                              this.player.a(54, 5, (short)-1, (byte)2, (byte)var10, (byte)-1, 0, 0, 0, new int[]{1, 30, 45});
                           } else {
                              this.gameEngine.gameController.b("Không có không gian, đã phóng sinh");
                           }
                        } else if (GameUtils.convertTime(var16)[2] <= 65L) {
                           this.player.s(1000);
                           this.gameEngine.gameController.b("Thưởng 1000 kim");
                        } else if (GameUtils.convertTime(var16)[2] <= 130L) {
                           this.player.s(750);
                           this.gameEngine.gameController.b("Thưởng 750 kim");
                        } else if (GameUtils.convertTime(var16)[2] <= 200L) {
                           this.player.s(600);
                           this.gameEngine.gameController.b("Thưởng 600 kim");
                        }

                        GameScreenManager.getInstance().pauseStartTime = 0L;
                        GameScreenManager.getInstance().gameStartTime = 0L;
                     }

                     var2.setExecutionState((byte)5);
                     break label1202;
                  }

                  if (var3.getNumericParameters()[0] == 0) {
                     ++this.animationCounter;
                     if (this.questEffectCounter > 0) {
                        if (this.animationCounter / 10 != 0 && this.animationCounter % 10 == 0) {
                           --this.questEffectCounter;
                        }
                        break label1202;
                     }

                     this.questEffectCounter = 0;
                     GameScreenManager.getInstance().gameStartTime = System.currentTimeMillis();
                     GameScreenManager.getInstance().pauseStartTime = GameScreenManager.getInstance().gameStartTime;
                     GameScreenManager.getInstance().battleStartTime = 0L;
                  } else {
                     if (var3.getNumericParameters()[0] != 1 || !this.gameEngine.gameController.aA()) {
                        break label1202;
                     }

                     if (this.questProgress == 0) {
                        this.currentTimeArray = this.getCurrentTimeArray();
                     }

                     ++this.questProgress;
                  }
                  break;
               case 81:
                  if (var3.getNumericParameters()[0] == 0) {
                     if (this.player.u(var3.getNumericParameters()[1])) {
                        var2.setCurrentCommandIndex((byte)(var3.getNumericParameters()[2] - 2));
                     } else {
                        var2.setCurrentCommandIndex((byte)(var3.getNumericParameters()[3] - 2));
                     }
                  } else if (var3.getNumericParameters()[0] == 1) {
                     if (this.player.x(var3.getNumericParameters()[1])) {
                        var2.setCurrentCommandIndex((byte)(var3.getNumericParameters()[2] - 2));
                     } else {
                        var2.setCurrentCommandIndex((byte)(var3.getNumericParameters()[3] - 2));
                     }
                  }
                  break label1202;
               case 82:
                  short var7 = var3.getNumericParameters()[0];
                  byte[] var8 = GameUtils.parseStringToBytes(var3.getStringParameters()[0]);
                  var10 = 0;

                  while(true) {
                     if (var10 >= var7) {
                        break label1202;
                     }

                     this.mapManager.C25_f287[var8[var10]].t();
                     this.mapManager.C25_f287[var8[var10]].u();
                     ++var10;
                  }
               case 83:
                  if (var2.getExecutionState() != 5) {
                     this.gameEngine.changeState((byte)30);
                     var2.setExecutionState((byte)5);
                     break label1202;
                  }

                  var2.setCurrentCommandIndex((byte)(var3.getNumericParameters()[questOptionIndex] - 2));
                  break;
               case 84:
                  if (var2.getExecutionState() != 5) {
                     int[] var4 = null;
                     if (var3.getNumericParameters()[2] == 1) {
                        var4 = new int[]{this.questProgress, 5 - this.questProgress};
                     } else if (var3.getNumericParameters()[2] == 0) {
                        var4 = new int[]{this.player.C53_f786, this.player.C53_f795.length - this.player.C53_f786};
                     }

                     String var9 = a(var3.getStringParameters()[1], var4);
                     this.mapManager.gameController.a((String)var3.getStringParameters()[0], (String)var9, var3.getNumericParameters()[1], var3.getNumericParameters()[0]);
                     var2.setExecutionState((byte)5);
                  } else if (this.mapManager.gameController.d(var3.getNumericParameters()[1], var3.getNumericParameters()[0]) && this.gameEngine.isKeyPressed(196640)) {
                     C25.B().D();
                     if (GameUtils.pageCount < GameUtils.b()) {
                        GameUtils.c();
                        this.mapManager.gameController.b(GameUtils.pageCount);
                     } else {
                        if (C25.C25_f318 != -1 && this.mapManager.C25_f287[C25.C25_f318].sprite.spriteSetId <= 85 && this.mapManager.C25_f287[C25.C25_f318].v() == 0) {
                           C25.B().a((byte)13, C25.B().C25_f287[C25.C25_f318].worldX, C25.B().C25_f287[C25.C25_f318].worldY - 40, C25.B().C25_f287[C25.C25_f318]);
                        }

                        isQuestTriggered = false;
                        isQuestReady = false;
                        this.mapManager.gameController.aF();
                        var2.setExecutionState((byte)1);
                     }
                  }
                  break label1202;
               case 85:
                  if (this.questProgress >= 0 && this.questProgress < 5) {
                     var2.setCurrentCommandIndex((byte)(var3.getNumericParameters()[0] - 2));
                     break label1202;
                  }

                  var2.setCurrentCommandIndex((byte)(var3.getNumericParameters()[1] - 2));
                  break label1202;
               case 87:
                  if (var2.getExecutionState() != 5) {
                     if (var3.getNumericParameters()[0] == 0) {
                        this.player.a(var3.getNumericParameters()[7], var3.getNumericParameters()[1], var3.getNumericParameters()[2], (short)-1, (byte)var3.getNumericParameters()[4], (byte)var3.getNumericParameters()[3], (byte)-1, new int[]{1, var3.getNumericParameters()[5], var3.getNumericParameters()[6]});
                     } else if (var3.getNumericParameters()[0] == 1) {
                        this.player.o(var3.getNumericParameters()[1]);
                     }

                     var2.setExecutionState((byte)5);
                     break label1202;
                  }

                  if (!this.gameEngine.gameController.aA()) {
                     break label1202;
                  }
                  break;
               case 88:
                  if (this.player.z() == 2) {
                     var2.setCurrentCommandIndex((byte)(var3.getNumericParameters()[0] - 2));
                  } else {
                     var2.setCurrentCommandIndex((byte)(var3.getNumericParameters()[1] - 2));
                  }
               }

               var2.setExecutionState((byte)1);
            }

            if (var2.getExecutionState() != 5 && var2.getExecutionState() != 6) {
               var2.executeNextCommand();
            }

            if (var2.getExecutionState() != 3 && var2.getExecutionState() != 4) {
               ++var1;
            } else {
               isQuestReady = false;
               this.activeEvents.removeElement(var2);
               var10 = C25.e(var2.getEventCoordinates()[0], var2.getEventCoordinates()[1]);
               if (this.questStates[var10] != null) {
                  this.questStates[var10][var2.getEventId()] = var2.getExecutionState();
               }

               if (var2.getExecutionState() == 3 && var2.getFirstCommand().getCommandId() == 44 && var2.getFirstCommand().getNumericParameters()[1] == 1) {
                  setQuestFlag(var2.getFirstCommand().getNumericParameters()[0]);
               }

               this.updateQuestEffects();
               if (C25.C25_f345) {
                  this.mapManager.C25_f342.playBackgroundMusic(C25.C25_f344, 1);
               }

               C25.C25_f345 = false;
            }
         }

         return;
      }
   }

   public final byte getCurrentEventIndex() {
      return this.currentEventIndex;
   }

   public final boolean hasActiveEvent() {
      if (this.eventScripts == null) {
         return false;
      } else {
         for(int var1 = 0; var1 < this.activeEvents.size(); ++var1) {
            EventScript var2;
            if ((var2 = (EventScript)this.activeEvents.elementAt(var1)).getExecutionState() != 2 && var2.getExecutionState() != 6) {
               return true;
            }
         }

         return false;
      }
   }

   private boolean checkEventCondition(ScriptCommand var1) {
      boolean var2 = false;
      if (var1.getNumericParameters()[7] == -1 || var1.getNumericParameters()[7] != -1 && this.questStates[C25.e(var1.getNumericParameters()[5], var1.getNumericParameters()[6])] != null && this.questStates[C25.e(var1.getNumericParameters()[5], var1.getNumericParameters()[6])][var1.getNumericParameters()[7]] == 3) {
         label58:
         switch(var1.getNumericParameters()[8]) {
         case 0:
            if (!this.player.C53_f797[var1.getNumericParameters()[9]]) {
               return var2;
            }
            break;
         case 1:
            var2 = true;
            return var2;
         case 2:
            if (this.player.C53_f792.size() + this.player.C53_f778 < var1.getNumericParameters()[9]) {
               return var2;
            }

            int var3;
            for(var3 = 0; var3 < this.player.C53_f778; ++var3) {
               if (this.player.C53_f777[var3].t() == var1.getNumericParameters()[10]) {
                  var2 = true;
                  break;
               }
            }

            if (var2) {
               return var2;
            }

            for(var3 = 0; var3 < this.player.C53_f792.size(); ++var3) {
               if (((int[])this.player.C53_f792.elementAt(var3))[1] == var1.getNumericParameters()[10]) {
                  break label58;
               }
            }

            return var2;
         case 3:
            if (this.player.C53_f783 < var1.getNumericParameters()[9]) {
               return var2;
            }
            break;
         case 4:
            if (this.player.a((byte)((byte)var1.getNumericParameters()[9]), (int)var1.getNumericParameters()[10]) != 2) {
               return var2;
            }
            break;
         case 5:
            if (questDialogState <= var1.getNumericParameters()[9]) {
               return var2;
            }
            break;
         case 6:
            if (questDialogState != var1.getNumericParameters()[9]) {
               return var2;
            }
         }

         var2 = true;
      }

      return var2;
   }

   private boolean checkEventCompletion(ScriptCommand var1) {
      boolean var2 = false;
      if (var1.getNumericParameters()[7] == -1 || var1.getNumericParameters()[7] != -1 && this.questStates[C25.e(var1.getNumericParameters()[5], var1.getNumericParameters()[6])] != null && this.questStates[C25.e(var1.getNumericParameters()[5], var1.getNumericParameters()[6])][var1.getNumericParameters()[7]] == 3) {
         switch(var1.getNumericParameters()[8]) {
         case 0:
            if (this.player.a((byte)((byte)var1.getNumericParameters()[9]), (int)var1.getNumericParameters()[10]) == 2) {
               var2 = true;
            }
            break;
         case 1:
            if (this.player.C53_f797[var1.getNumericParameters()[9]]) {
               var2 = true;
            }
            break;
         case 2:
         case 4:
            if (this.questStates[C25.e(var1.getNumericParameters()[5], var1.getNumericParameters()[6])] != null && this.questStates[C25.e(var1.getNumericParameters()[5], var1.getNumericParameters()[6])][var1.getNumericParameters()[7]] == 3) {
               var2 = true;
            }
            break;
         case 3:
            if (this.player.b((int)var1.getNumericParameters()[9], (int)var1.getNumericParameters()[10], (byte)0)) {
               var2 = true;
            }
            break;
         case 5:
            if (this.player.C53_f783 >= var1.getNumericParameters()[9]) {
               var2 = true;
            }
            break;
         case 6:
            byte[] var5 = new byte[]{0, 1, 2, 3};

            int var3;
            for(var3 = 0; var3 < this.player.C53_f778; ++var3) {
               for(int var4 = 0; var4 < var5.length; ++var4) {
                  if (var5[var4] != -1 && var5[var4] == ResourceManager.getDatabaseValue((byte)0, (short)this.player.C53_f777[var3].r(), (byte)1)) {
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

   public final void updateQuestEffects() {
      if (questEffectObjects == null) {
         questEffectObjects = new Vector();
      }

      questEffectObjects.removeAllElements();
      Vector var2 = new Vector();

      ScriptCommand var1;
      int var3;
      C18 var6;
      GameObject var7;
      for(var3 = 0; var3 < questEndMatrix.length; ++var3) {
         if (C25.e(questEndMatrix[var3][0], questEndMatrix[var3][1]) == C25.e(C25.B().C25_f290, C25.B().C25_f291) && (this.eventScripts[questEndMatrix[var3][2]].getExecutionState() == 0 || this.eventScripts[questEndMatrix[var3][2]].getExecutionState() == 4)) {
            var1 = this.eventScripts[questEndMatrix[var3][2]].getFirstCommand();
            if (!var2.contains("" + var1.getNumericParameters()[4])) {
               if (this.checkEventCompletion(var1)) {
                  (var7 = new GameObject()).loadSpriteSet(259, false);
                  var7.setAnimation((byte)1, (byte)-1, true);
                  var7.setWorldPosition(this.mapManager.C25_f287[var1.getNumericParameters()[4]].worldX, this.mapManager.C25_f287[var1.getNumericParameters()[4]].worldY - 40);
                  this.mapManager.C25_f287[var1.getNumericParameters()[4]].f((byte)1);
                  var6 = this.mapManager.C25_f287[var1.getNumericParameters()[4]];
                  var7.followTarget = var6;
                  var7.activate();
                  questEffectObjects.addElement(var7);
                  var2.addElement("" + var1.getNumericParameters()[4]);
               } else {
                  int var4 = getQuestFlagIndex(var1.getNumericParameters()[0]);
                  if (var1.getNumericParameters()[1] == 0 && this.questStates[C25.e(questStartMatrix[var3][0], questStartMatrix[var3][1])][questStartMatrix[var3][2]] == 3 && this.questStates[C25.e(questEndMatrix[var3][0], questEndMatrix[var3][1])][questEndMatrix[var3][2]] != 3 || var1.getNumericParameters()[1] == 1 && var4 != -1 && questFlags[var4][1] == 1) {
                     (var7 = new GameObject()).loadSpriteSet(259, false);
                     var7.setAnimation((byte)15, (byte)-1, true);
                     var7.setWorldPosition(this.mapManager.C25_f287[var1.getNumericParameters()[4]].worldX, this.mapManager.C25_f287[var1.getNumericParameters()[4]].worldY - 40);
                     this.mapManager.C25_f287[var1.getNumericParameters()[4]].f((byte)1);
                     var6 = this.mapManager.C25_f287[var1.getNumericParameters()[4]];
                     var7.followTarget = var6;
                     var7.activate();
                     questEffectObjects.addElement(var7);
                     var2.addElement("" + var1.getNumericParameters()[4]);
                  }
               }
            }
         }
      }

      for(var3 = 0; var3 < questStartMatrix.length; ++var3) {
         if (C25.e(questStartMatrix[var3][0], questStartMatrix[var3][1]) == C25.e(C25.B().C25_f290, C25.B().C25_f291) && (this.eventScripts[questStartMatrix[var3][2]].getExecutionState() == 0 || this.eventScripts[questStartMatrix[var3][2]].getExecutionState() == 4)) {
            var1 = this.eventScripts[questStartMatrix[var3][2]].getFirstCommand();
            if (!var2.contains("" + var1.getNumericParameters()[4]) && this.checkEventCondition(var1)) {
               (var7 = new GameObject()).loadSpriteSet(259, false);
               var7.setAnimation((byte)7, (byte)-1, true);
               var7.setWorldPosition(this.mapManager.C25_f287[var1.getNumericParameters()[4]].worldX, this.mapManager.C25_f287[var1.getNumericParameters()[4]].worldY - 40);
               this.mapManager.C25_f287[var1.getNumericParameters()[4]].f((byte)1);
               var6 = this.mapManager.C25_f287[var1.getNumericParameters()[4]];
               var7.followTarget = var6;
               var7.activate();
               questEffectObjects.addElement(var7);
            }
         }
      }

   }

   public final int[] getCurrentTimeArray() {
      int[] var1;
      (var1 = new int[4])[0] = this.calendar.get(1);
      var1[1] = this.calendar.get(Calendar.MONTH);
      var1[2] = this.calendar.get(Calendar.DATE);
      var1[3] = this.calendar.get(Calendar.HOUR_OF_DAY);
      return this.currentTimeArray;
   }

   public final int[] getSavedTimeArray() {
      return this.currentTimeArray;
   }

   public final void setSavedTimeArray(int[] var1) {
      this.currentTimeArray = var1;
   }
}
