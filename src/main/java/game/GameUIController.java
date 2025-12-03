package game;

import engine.GameEngineBase;
import engine.GameUtils;
import engine.entity.ResourceManager;
import engine.graphics.EffectEntity;
import engine.graphics.ImageProcessor;
import engine.graphics.ScreenTransitionManager;
import layout.*;

import java.util.Vector;

public final class GameUIController implements DialogHandler {
    private static GameUIController instance;
    private GameEngineBase gameEngine;
    private DialogManager dialogManager = DialogManager.getInstance();
    private PlayerCharacter player;
    protected int tabIndex;
    protected int menuIndex;
    protected int subMenuIndex;
    private int optionIndex;
    protected int npcDialogIndex;
    protected int battleMenuIndex;
    private int scrollOffset;
    protected int dialogState;
    protected boolean needsRedraw;
    private byte statusBarState;
    private byte popupState;
    private int listScrollOffset;
    protected int listSelectedIndex;
    private int listItemCount;
    protected int detailIndex;
    private int[] tempData1;
    private int[] navigationData;
    public byte shopType = -1;
    private static String[] CITY_NAMES = new String[]{"Thủy Kimura", "Bích Thủy thành", "Nguyên Mộc Thành",
            "Niêm Thổ Thành", "Hắc Thạch thành", "Thiên không", "Xa cổ"};
    private static short[] TELEPORT_DATA = new short[]{1, 0, 196, 208, 0, 2, 1, 196, 208, 0, 3, 3, 196, 208, 0, 4, 5, 320,
            352, 0, 5, 3, 320, 196, 0, 7, 2, 288, 112, 0, 8, 0, 160, 144, 0};
    private Vector itemDisplayList = new Vector();
    private int inventoryPage = 0;
    private int inventoryOffset = 0;
    private int inventorySelectedIndex = 0;
    private int inventoryScrollPos = 0;
    public int itemQuantity = 0;
    boolean isItemUsed = false;
    private int pokemonSlot1;
    private int pokemonSlot2;
    private int skillPageIndex = 0;
    private int skillSelectedIndex = 0;
    private int skillOffset = 0;
    private int skillCount = 0;
    private String[] DIALOG_PATHS = new String[]{"/data/ui/option.ui", "/data/ui/answer.ui", "/data/ui/wharf1.ui"};
    public short[] REGION_SPAWN_DATA = new short[]{9, 0, 120, 448, 9, 1, 136, 272, 9, 2, 208, 256, 9, 3, 80, 264, 9, 4, 112, 288,
            9, 5, 40, 280, 9, 6, 136, 328, 9, 7, 104, 328};
    private String[] REWARD_DESCRIPTIONS = new String[]{"Đạt được 2000 kim tiền", "Đạt được 5 Phong ấn cầu",
            "Đạt được 5 Bánh Sandwich", "Đạt được 2 Sinh mệnh thạch", "Đạt được 2 huy hiệu"};
    private short[][] REWARD_TEXT_IDS = new short[][]{{621, 622}, {623, 624}, {625, 626}, {627, 628},
            {629, 630, 631, 632}};
    private short[][] QUEST_LOCATION_DATA = new short[][]{{5, 2, 112, 224, 2, 2, 5, 6, 1, 6, 0, 112, 224, 2, 0, 1, 0, 10},
            {4, 0, 48, 176, 2, 2, 3, 6, 3, 6, 0, 112, 224, 2, 2, 1, 0, 10},
            {3, 6, 288, 224, 3, 0, 3, 6, 3, 6, 0, 112, 224, 2, 2, 1, 0, 10},
            {1, 5, 272, 128, 3, 0, 5, 6, 1, 6, 0, 112, 224, 2, 0, 1, 0, 10}, {1, 5, 272, 128, 3, 2, 0, 0, 0, 3, 6, 288,
            224, 3, 0, 0, 0, 0, 4, 0, 48, 176, 2, 0, 0, 0, 0, 5, 2, 112, 224, 2, 2, 0, 0, 0}};
    private byte questPageIndex;
    private byte questSelectedIndex;
    private String[] GUIDE_MENU_ITEMS = new String[]{"Dẫn thưởng", "Tiến hóa", "Dị hóa", "Tài liệu", "Cách mở"};

    public static GameUIController getInstance() {
        if (instance == null) {
            instance = new GameUIController();
        }

        return instance;
    }

    public GameUIController() {
        if (this.player == null) {
            this.player = PlayerCharacter.getInstance();
        }

    }

    public final void destroy() {
        instance = null;
        this.player = null;
    }

    public final void setGameEngine(GameEngineBase var1) {
        if (this.gameEngine != null) {
            this.gameEngine = null;
        }

        this.gameEngine = var1;
        this.needsRedraw = true;
    }

    public final void showWorldUI() {
        this.dialogManager.showDialog("/data/ui/world.ui", 257, this);
        this.statusBarState = 0;
    }

    public final void showFullWorldUI() {
        this.dialogManager.currentDialog.getChildById(5).setVisible(true);
        this.dialogManager.currentDialog.getChildById(7).setVisible(true);
    }

    private void hideWorldUIElements() {
        if (this.dialogManager.isTopDialog("/data/ui/world.ui")) {
            for (int var1 = 1; var1 <= 7; ++var1) {
                this.dialogManager.currentDialog.getChildById(var1).setVisible(false);
            }
        }

    }

    public void updateStatusBar() {
        if (this.statusBarState < 2 && !QuestManager.isQuestActive && WorldGameSession.showStatusBar
                && this.dialogManager.containsDialog("/data/ui/world.ui")) {
            DialogSystem var2 = this.dialogManager.getDialog("/data/ui/world.ui");
            if (var2.getChildById(1).getComponentData().renderer.getSpriteManager().isAtFrame(4)) {
                this.dialogManager.getDialog("/data/ui/world.ui").getChildById(6)
                        .getComponentData().text = ((WorldGameSession) this.gameEngine).areaName;
                this.statusBarState = 1;
            } else if (this.statusBarState == 1) {
                var2 = this.dialogManager.getDialog("/data/ui/world.ui");
                if (var2.getChildById(1).getComponentData().renderer.getSpriteManager().getCurrentFrameIndex() >= 5) {
                    this.dialogManager.getDialog("/data/ui/world.ui").getChildById(6).getComponentData().text = "";
                    this.statusBarState = 2;
                    WorldGameSession.showStatusBar = false;
                }
            }
        }

        this.handleOpenBoxDialog();
    }

    public boolean handleOpenBoxDialog() {
        if (this.popupState < 2 && this.dialogManager.isTopDialog("/data/ui/openbox.ui")) {
            if (this.popupState == 1) {
                if (this.gameEngine.isKeyPressed(196640)) {
                    this.dialogManager.currentDialog.getChildById(2).getComponentData().text = "";
                    this.popupState = 2;
                    this.needsRedraw = true;
                    this.processChestReward();
                    return true;
                }
            } else {
                this.popupState = 1;
            }
        }

        return this.handleTaskTipDialog();
    }

    public final boolean handleTaskTipDialog() {
        if (this.popupState < 2) {
            if (this.dialogManager.isTopDialog("/data/ui/taskTip.ui")) {
                if (this.popupState == 1) {
                    if (this.gameEngine.isKeyPressed(196640)) {
                        this.popupState = 2;
                        this.needsRedraw = true;
                        if (this.dialogManager.isTopDialog("/data/ui/taskTip.ui")) {
                            this.dialogManager.removeDialog("/data/ui/taskTip.ui");
                        }

                        return true;
                    }
                } else {
                    this.popupState = 1;
                }
            }

            this.needsRedraw = true;
        }

        return false;
    }

    public final void showTeleportMenu() {
        this.dialogManager.showDialog("/data/ui/transmit.ui", 257, this);
        ((UIContainerComponent) this.dialogManager.currentDialog
                .getChildById(0)).primaryListComponent.totalItemCount = CITY_NAMES.length;
        ((UIContainerComponent) this.dialogManager.currentDialog.getChildById(0)).primaryListComponent.setDisplayMode(1);
        this.updateTeleportList();
    }

    private void updateTeleportList() {
        this.listScrollOffset = ((UIContainerComponent) this.dialogManager.currentDialog
                .getChildById(0)).primaryListComponent.scrollOffset;
        this.listSelectedIndex = ((UIContainerComponent) this.dialogManager.currentDialog
                .getChildById(0)).primaryListComponent.selectedIndex;

        for (int var1 = 0; var1 < 5; ++var1) {
            this.dialogManager.currentDialog.getChildById(var1 + 5).getComponentData().text = CITY_NAMES[var1 + this.listScrollOffset];
        }

        this.dialogManager.currentDialog.getChildById(13).setOffsetY(109 + this.listSelectedIndex * 88 / CITY_NAMES.length,
                this.dialogManager.currentDialog.getUIContainerComponent());
    }

    public final void updateTeleportMenu() {
        if (this.gameEngine.isKeyPressed(4100)) {
            this.dialogManager.currentDialog.handleAction(0);
            this.updateTeleportList();
        } else if (this.gameEngine.isKeyPressed(8448)) {
            this.dialogManager.currentDialog.handleAction(1);
            this.updateTeleportList();
        } else if (this.gameEngine.isKeyPressed(196640)) {
            WorldGameSession.getInstance().currentRegionId = TELEPORT_DATA[this.listSelectedIndex * 5];
            WorldGameSession.getInstance().currentAreaId = TELEPORT_DATA[this.listSelectedIndex * 5 + 1];
            WorldGameSession.getInstance().spawnPositionX = TELEPORT_DATA[this.listSelectedIndex * 5 + 2];
            WorldGameSession.getInstance().spawnPositionY = TELEPORT_DATA[this.listSelectedIndex * 5 + 3];
            WorldGameSession.playerDirection = (byte) TELEPORT_DATA[this.listSelectedIndex * 5 + 4];
            WorldGameSession.getInstance().lastInteractedNpcId = -1;
            GameScreenManager.getInstance().changeState((byte) 9);
        } else {
            if (this.gameEngine.isKeyPressed(262144)) {
                this.gameEngine.changeState((byte) 8);
                this.dialogManager.removeDialog("/data/ui/transmit.ui");
            }

        }
    }

    public final boolean isPopupActive() {
        return this.dialogManager.isTopDialog("/data/ui/openbox.ui") || this.dialogManager.isTopDialog("/data/ui/taskTip.ui");
    }

    public final void showGameMenu() {
        String[] var1 = new String[]{"Tùy thân cửa hàng", "Sủng vật", "Lưng bao", "Đồ giám", "Nhiệm vụ",
                "Lưu dữ liệu"};
        this.hideWorldUIElements();
        this.dialogManager.showDialog("/data/ui/gamemenu.ui", 257, this);
        int var2;
        if (GameEngineBase.paymentActive) {
            ((UIContainerComponent) this.dialogManager.currentDialog.getChildById(0)).primaryListComponent.totalItemCount = 6;
            this.dialogManager.currentDialog.getChildById(14).getComponentData().text = GameEngineBase
                    .getLocalizedText(605 + this.menuIndex);
            this.dialogManager.currentDialog.getChildById(15).getComponentData().text = var1[0];

            for (var2 = 0; var2 < 5; ++var2) {
                this.dialogManager.currentDialog.getChildById(var2 + 5).getComponentData().text = var1[var2 + 1];
            }
        } else {
            ((UIContainerComponent) this.dialogManager.currentDialog.getChildById(0)).primaryListComponent.totalItemCount = 5;
            this.dialogManager.currentDialog.getChildById(14).getComponentData().text = GameEngineBase
                    .getLocalizedText(606 + this.menuIndex);
            this.dialogManager.currentDialog.getChildById(15).getComponentData().text = var1[1];

            for (var2 = 0; var2 < 4; ++var2) {
                this.dialogManager.currentDialog.getChildById(var2 + 5).getComponentData().text = var1[var2 + 2];
            }

            this.dialogManager.currentDialog.getChildById(9).setVisible(false);
        }

        ((UIContainerComponent) this.dialogManager.currentDialog
                .getChildById(0)).primaryListComponent.selectedIndex = this.menuIndex;
        this.dialogManager.currentDialog.getChildById(18).getComponentData().text = "" + this.player.getBadges();
        this.dialogManager.currentDialog.getChildById(19).getComponentData().text = "" + this.player.getGold();
        this.dialogState = 0;
    }

    public final void updateGameMenu() {
        this.gameEngine.updateActionSequence();
        if (!GameEngineBase.isActionBlocked((int) this.menuIndex, (int) 0) && !this.isPopupActive()
                && this.gameEngine.isKeyPressed(4100)) {
            this.dialogManager.currentDialog.handleAction(0);
        } else if (!GameEngineBase.isActionBlocked((int) this.menuIndex, (int) 0) && !this.isPopupActive()
                && this.gameEngine.isKeyPressed(8448)) {
            this.dialogManager.currentDialog.handleAction(1);
        } else if (!this.isPopupActive() && GameEngineBase.isConfirmAllowed() && this.gameEngine.isKeyPressed(196640)) {
            if (GameEngineBase.isActionActive() && !GameEngineBase.isActionBlocked((int) this.menuIndex, (int) 0)) {
                return;
            }

            if (GameEngineBase.paymentActive) {
                switch (this.menuIndex) {
                    case 0:
                        this.gameEngine.changeState((byte) 14);
                        this.dialogManager.removeDialog("/data/ui/gamemenu.ui");
                        break;
                    case 1:
                        this.subMenuIndex = 0;
                        this.gameEngine.handleActionResponse();
                        this.gameEngine.changeState((byte) 7);
                        this.dialogManager.removeDialog("/data/ui/gamemenu.ui");
                        break;
                    case 2:
                        this.gameEngine.handleActionResponse();
                        this.gameEngine.changeState((byte) 8);
                        this.dialogManager.removeDialog("/data/ui/gamemenu.ui");
                        break;
                    case 3:
                        this.subMenuIndex = 0;
                        this.gameEngine.changeState((byte) 9);
                        this.dialogManager.removeDialog("/data/ui/gamemenu.ui");
                        break;
                    case 4:
                        this.menuIndex = 0;
                        this.gameEngine.changeState((byte) 10);
                        this.dialogManager.removeDialog("/data/ui/gamemenu.ui");
                        break;
                    case 5:
                        this.dialogManager.currentDialog.getChildById(11).setVisible(false);
                        this.dialogManager.currentDialog.getChildById(12).setVisible(false);
                        this.gameEngine.changeState((byte) 22);
                }
            } else {
                switch (this.menuIndex) {
                    case 0:
                        this.subMenuIndex = 0;
                        this.gameEngine.handleActionResponse();
                        this.gameEngine.changeState((byte) 7);
                        this.dialogManager.removeDialog("/data/ui/gamemenu.ui");
                        break;
                    case 1:
                        this.gameEngine.handleActionResponse();
                        this.gameEngine.changeState((byte) 8);
                        this.dialogManager.removeDialog("/data/ui/gamemenu.ui");
                        break;
                    case 2:
                        this.subMenuIndex = 0;
                        this.gameEngine.changeState((byte) 9);
                        this.dialogManager.removeDialog("/data/ui/gamemenu.ui");
                        break;
                    case 3:
                        this.menuIndex = 0;
                        this.gameEngine.changeState((byte) 10);
                        this.dialogManager.removeDialog("/data/ui/gamemenu.ui");
                        break;
                    case 4:
                        this.dialogManager.currentDialog.getChildById(11).setVisible(false);
                        this.dialogManager.currentDialog.getChildById(12).setVisible(false);
                        this.gameEngine.changeState((byte) 22);
                }
            }
        } else if (GameEngineBase.isCancelAllowed() && this.gameEngine.isKeyPressed(262144)) {
            this.dialogManager.removeDialog("/data/ui/gamemenu.ui");
            this.gameEngine.changeState((byte) 0);
        }

        this.handleTaskTipDialog();
    }

    public final void showSystemMenu() {
        this.hideWorldUIElements();
        this.dialogManager.showDialog("/data/ui/gamesystem.ui", 257, this);
        ((UIContainerComponent) this.dialogManager.currentDialog
                .getChildById(0)).primaryListComponent.selectedIndex = this.menuIndex;
        this.dialogState = 0;
    }

    public final void updateSystemMenu() {
        if (this.gameEngine.isKeyPressed(4100)) {
            this.dialogManager.currentDialog.handleAction(0);
        } else if (this.gameEngine.isKeyPressed(8448)) {
            this.dialogManager.currentDialog.handleAction(1);
        } else if (this.gameEngine.isKeyPressed(196640)) {
            switch (this.menuIndex) {
                case 0:
                    this.dialogManager.removeDialog("/data/ui/gamesystem.ui");
                    this.gameEngine.changeState((byte) 0);
                    return;
                case 1:
                    this.gameEngine.changeState((byte) 20);
                    this.dialogManager.removeDialog("/data/ui/gamesystem.ui");
                    return;
                case 2:
                    this.gameEngine.changeState((byte) 21);
                    this.dialogManager.removeDialog("/data/ui/gamesystem.ui");
                    return;
                case 3:
                    if (this.dialogState == 0) {
                        this.dialogManager.showDialog("/data/ui/option.ui", 257, this);
                        this.subMenuIndex = 1;
                        ((UIContainerComponent) this.dialogManager.currentDialog
                                .getChildById(0)).primaryListComponent.selectedIndex = this.subMenuIndex;
                        this.dialogManager.currentDialog.getChildById(12).getComponentData().text = "";
                        this.dialogManager.currentDialog.getChildById(13).getComponentData().text = "Không";
                        this.dialogState = 1;
                        return;
                    } else {
                        switch (this.subMenuIndex) {
                            case 0:
                                GameScreenManager.getInstance().pauseStartTime = 0L;
                                GameScreenManager.getInstance().gameStartTime = 0L;
                                PlayerCharacter.getInstance().isInitialized = false;
                                GameScreenManager.getInstance().changeState((byte) 7);
                                this.dialogManager.removeDialog("/data/ui/gamesystem.ui");
                                break;
                            case 1:
                                this.dialogManager.removeDialog("/data/ui/option.ui");
                                this.dialogState = 0;
                                this.needsRedraw = true;
                                return;
                        }
                    }
                default:
            }
        } else {
            if (this.gameEngine.isKeyPressed(262144)) {
                if (this.dialogState == 0) {
                    this.dialogManager.removeDialog("/data/ui/gamesystem.ui");
                    this.gameEngine.changeState((byte) 0);
                    return;
                }

                if (this.dialogState == 1) {
                    this.needsRedraw = true;
                    this.dialogManager.removeDialog("/data/ui/option.ui");
                    this.dialogState = 0;
                }
            }

        }
    }

    public final void showHelpMenu() {
        this.dialogManager.showDialog("/data/ui/help1.ui", 257, this);
        this.menuIndex = 0;
        this.dialogManager.currentDialog.getChildById(6).setVisible(true);
        this.dialogManager.currentDialog.getChildById(7).setVisible(false);
        this.updateHelpPageContent(this.menuIndex);
    }

    private void updateHelpPageContent(int var1) {
        int var2;
        if (var1 == 0) {
            this.dialogManager.currentDialog.getChildById(5).getComponentData().text = "Trợ giúp";
            this.dialogManager.currentDialog.getChildById(8)
                    .getComponentData().text = "Nhấn nút 2, 4, 6, 8 để di chuyển#nNút 5: công kích, đối thoại, xác nhận#nNút 1: Xem nhiệm vụ#nNút 9: lựa chọn sủng vật cưỡi#nNút 0: Xem bản đồ#nNút mềm trái: menu hệ thống#nNút mềm phải: menu trò chơi";

            for (var2 = 0; var2 < 28; ++var2) {
                this.dialogManager.currentDialog.getChildById(var2 + 9).setVisible(false);
            }
        } else if (var1 > 0) {
            this.dialogManager.currentDialog.getChildById(8).getComponentData().text = "";

            for (var2 = 0; var2 < 14; ++var2) {
                this.dialogManager.currentDialog.getChildById(9 + (var2 << 1)).setVisible(true);
                this.dialogManager.currentDialog.getChildById(9 + (var2 << 1) + 1).setVisible(true);
                if ((var1 - 1) * 14 + var2 < 26) {
                    this.dialogManager.currentDialog.getChildById(9 + (var2 << 1))
                            .getComponentData().renderer = new SpriteRenderer();
                    this.dialogManager.currentDialog.getChildById(9 + (var2 << 1)).getComponentData().renderer
                            .setSpriteIndex((int) 0);
                    this.dialogManager.currentDialog.getChildById(9 + (var2 << 1)).getComponentData().renderer.spriteType = 2;
                    this.dialogManager.currentDialog.getChildById(9 + (var2 << 1)).getComponentData().renderer
                            .initializeSprite(325, false, (byte) -2);
                    this.dialogManager.currentDialog.getChildById(9 + (var2 << 1)).getComponentData().renderer
                            .setSpriteIndex((var1 - 1) * 14 + var2 + 1);
                    if ((var1 - 1) * 14 + var2 <= 10) {
                        this.dialogManager.currentDialog.getChildById(9 + (var2 << 1) + 1).getComponentData().text = GameEngineBase
                                .getLocalizedText(var2 + 311);
                    } else {
                        this.dialogManager.currentDialog.getChildById(9 + (var2 << 1) + 1).getComponentData().text = GameEngineBase
                                .getLocalizedText(333 + ((var1 - 1) * 14 + var2 - 11));
                    }
                } else {
                    this.dialogManager.currentDialog.getChildById(9 + (var2 << 1)).setVisible(false);
                    this.dialogManager.currentDialog.getChildById(9 + (var2 << 1) + 1).setVisible(false);
                }
            }
        }

        this.dialogManager.currentDialog.getChildById(39).getComponentData().text = var1 + 1 + "/3";
    }

    public final void updateHelpMenu() {
        if (this.gameEngine.isKeyPressed(16400)) {
            --this.menuIndex;
            if (this.menuIndex <= 0) {
                this.menuIndex = 0;
            }

            this.updateHelpPageContent(this.menuIndex);
        } else if (this.gameEngine.isKeyPressed(32832)) {
            ++this.menuIndex;
            if (this.menuIndex >= 2) {
                this.menuIndex = 2;
            }

            this.updateHelpPageContent(this.menuIndex);
        } else {
            if (this.gameEngine.isKeyPressed(262144)) {
                this.gameEngine.changeState((byte) 0);
                this.dialogManager.removeDialog("/data/ui/help1.ui");
            }

        }
    }

    public final void showAboutDialog() {
        this.dialogManager.showDialog("/data/ui/help.ui", 257, this);
        this.dialogManager.currentDialog.getChildById(5).getComponentData().text = "Quan tại";
        this.dialogManager.currentDialog.getChildById(8)
                .getComponentData().text = "Tên trò chơi: Sủng vật Vương quốc - Liệt hỏa#nViệt hóa: BIGAME";
        this.dialogManager.currentDialog.getChildById(6).setVisible(true);
        this.dialogManager.currentDialog.getChildById(7).setVisible(false);

        for (int var1 = 9; var1 < 13; ++var1) {
            this.dialogManager.currentDialog.getChildById(var1).setVisible(false);
        }

    }

    public final void updateAboutDialog() {
        if (this.gameEngine.isKeyPressed(262144)) {
            this.gameEngine.changeState((byte) 0);
            this.dialogManager.removeDialog("/data/ui/help.ui");
        }

    }

    public final void showOptionsMenu() {
        this.dialogManager.showDialog("/data/ui/help.ui", 257, this);
        this.dialogManager.currentDialog.getChildById(5).getComponentData().text = "Tùy chọn";
        this.dialogManager.currentDialog.getChildById(8).getComponentData().text = "";
        this.dialogManager.currentDialog.getChildById(6).setVisible(false);
        this.dialogManager.currentDialog.getChildById(7).setVisible(true);

        for (int var1 = 9; var1 < 13; ++var1) {
            this.dialogManager.currentDialog.getChildById(var1).setVisible(true);
        }

        this.updateVolumeDisplay();
    }

    private void updateVolumeDisplay() {
        for (int var1 = 1; var1 < 4; ++var1) {
            if (var1 <= GameScreenManager.getInstance().difficultyLevel) {
                this.dialogManager.currentDialog.getChildById(var1 + 9).getComponentData().backgroundColor = -2148;
            } else {
                this.dialogManager.currentDialog.getChildById(var1 + 9).getComponentData().backgroundColor = -8540732;
            }
        }

    }

    public final void updateVolumeSettings() {
        if (this.gameEngine.isKeyPressed(16400)) {
            GameScreenManager.getInstance().decreaseDifficulty();
            this.updateVolumeDisplay();
        } else if (this.gameEngine.isKeyPressed(32832)) {
            GameScreenManager.getInstance().increaseDifficulty();
            this.updateVolumeDisplay();
        } else {
            if (this.gameEngine.isKeyPressed(131072)) {
                MainMenuScreen.getInstance().animationCounter = GameScreenManager.getInstance().difficultyLevel;
                this.gameEngine.changeState((byte) 0);
                this.dialogManager.removeDialog("/data/ui/help.ui");
            }

        }
    }

    public final void showBattleMenu() {
        this.dialogManager.showDialog("/data/ui/menu.ui", 336, this);
    }

    public final void showBattleMenu2() {
        this.dialogManager.showDialog("/data/ui/menu1.ui", 336, this);
    }

    public final void hideBattleMenu() {
        this.dialogManager.removeDialog("/data/ui/menu1.ui");
    }

    public final void updateBattleUIElement(int var1, int var2) {
        switch (var1) {
            case 0:
                this.dialogManager.currentDialog.getChildById(var2).setVisible(false);
                return;
            case 1:
                this.dialogManager.currentDialog.getChildById(8).setVisible(false);
                this.dialogManager.currentDialog.getChildById(9).setVisible(false);
                return;
            case 2:
                this.dialogManager.currentDialog.getChildById(10).setVisible(false);
                this.dialogManager.currentDialog.getChildById(11).setVisible(false);
                return;
            case 3:
                this.dialogManager.currentDialog.getChildById(12).setVisible(false);
                this.dialogManager.currentDialog.getChildById(13).setVisible(false);
                return;
            case 4:
                for (var1 = 0; var1 < 2; ++var1) {
                    if (this.dialogManager.currentDialog.getChildById(var1 + 16).getComponentData().renderer == null) {
                        this.dialogManager.currentDialog.getChildById(var1 + 16).getComponentData().renderer = new SpriteRenderer();
                        this.dialogManager.currentDialog.getChildById(var1 + 16).getComponentData().renderer.spriteType = 2;
                        this.dialogManager.currentDialog.getChildById(var1 + 16).getComponentData().renderer
                                .setSpriteIndex((int) 0);
                        this.dialogManager.currentDialog.getChildById(var1 + 16).getComponentData().renderer.initializeSprite(336,
                                false, (byte) 0);
                        if (var1 == 0) {
                            this.dialogManager.currentDialog.getChildById(var1 + 16).getComponentData().renderer
                                    .setSpriteIndex((int) 8);
                        } else {
                            this.dialogManager.currentDialog.getChildById(var1 + 16).getComponentData().renderer
                                    .setSpriteIndex((int) 10);
                        }
                    }
                }

                return;
            case 5:
                this.dialogManager.currentDialog.getChildById(16).setVisible(false);
                this.dialogManager.currentDialog.getChildById(17).setVisible(false);

                for (var1 = 0; var1 < 2; ++var1) {
                    this.dialogManager.currentDialog.getChildById(var1 + 16).cleanUp();
                    if (this.dialogManager.currentDialog.getChildById(var1 + 18).getComponentData().renderer == null) {
                        this.dialogManager.currentDialog.getChildById(var1 + 18).getComponentData().renderer = new SpriteRenderer();
                        this.dialogManager.currentDialog.getChildById(var1 + 18).getComponentData().renderer.spriteType = 2;
                        this.dialogManager.currentDialog.getChildById(var1 + 18).getComponentData().renderer
                                .setSpriteIndex((int) 0);
                        this.dialogManager.currentDialog.getChildById(var1 + 18).getComponentData().renderer.initializeSprite(336,
                                false, (byte) 0);
                        if (var1 == 0) {
                            this.dialogManager.currentDialog.getChildById(var1 + 18).getComponentData().renderer
                                    .setSpriteIndex((int) 8);
                        } else {
                            this.dialogManager.currentDialog.getChildById(var1 + 18).getComponentData().renderer
                                    .setSpriteIndex((int) 11);
                        }
                    }
                }

                return;
            case 6:
                this.dialogManager.currentDialog.getChildById(19).setVisible(false);
                this.dialogManager.currentDialog.getChildById(19).cleanUp();
                if (this.dialogManager.currentDialog.getChildById(20).getComponentData().renderer == null) {
                    this.dialogManager.currentDialog.getChildById(20).getComponentData().renderer = new SpriteRenderer();
                    this.dialogManager.currentDialog.getChildById(20).getComponentData().renderer.spriteType = 2;
                    this.dialogManager.currentDialog.getChildById(20).getComponentData().renderer.setSpriteIndex((int) 0);
                    this.dialogManager.currentDialog.getChildById(20).getComponentData().renderer.initializeSprite(336, false,
                            (byte) 0);
                    this.dialogManager.currentDialog.getChildById(20).getComponentData().renderer.setSpriteIndex((int) 12);
                    return;
                }
                break;
            case 7:
                this.dialogManager.currentDialog.getChildById(20).setVisible(false);
                this.dialogManager.currentDialog.getChildById(20).cleanUp();
                return;
            case 8:
                for (var1 = 0; var1 < 2; ++var1) {
                    if (this.dialogManager.currentDialog.getChildById(var1 + 21).getComponentData().renderer == null) {
                        this.dialogManager.currentDialog.getChildById(var1 + 21).getComponentData().renderer = new SpriteRenderer();
                        this.dialogManager.currentDialog.getChildById(var1 + 21).getComponentData().renderer.spriteType = 2;
                        this.dialogManager.currentDialog.getChildById(var1 + 21).getComponentData().renderer
                                .setSpriteIndex((int) 0);
                        this.dialogManager.currentDialog.getChildById(var1 + 21).getComponentData().renderer.initializeSprite(336,
                                false, (byte) 0);
                        if (var1 == 0) {
                            this.dialogManager.currentDialog.getChildById(var1 + 21).getComponentData().renderer
                                    .setSpriteIndex((int) 7);
                        } else {
                            this.dialogManager.currentDialog.getChildById(var1 + 21).getComponentData().renderer
                                    .setSpriteIndex((int) 13);
                        }
                    }
                }

                return;
            case 9:
                this.dialogManager.currentDialog.getChildById(21).setVisible(false);
                this.dialogManager.currentDialog.getChildById(22).setVisible(false);

                for (var1 = 0; var1 < 2; ++var1) {
                    this.dialogManager.currentDialog.getChildById(var1 + 21).cleanUp();
                    if (this.dialogManager.currentDialog.getChildById(var1 + 23).getComponentData().renderer == null) {
                        this.dialogManager.currentDialog.getChildById(var1 + 23).getComponentData().renderer = new SpriteRenderer();
                        this.dialogManager.currentDialog.getChildById(var1 + 23).getComponentData().renderer.spriteType = 2;
                        this.dialogManager.currentDialog.getChildById(var1 + 23).getComponentData().renderer
                                .setSpriteIndex((int) 0);
                        this.dialogManager.currentDialog.getChildById(var1 + 23).getComponentData().renderer.initializeSprite(336,
                                false, (byte) 0);
                        if (var1 == 0) {
                            this.dialogManager.currentDialog.getChildById(var1 + 23).getComponentData().renderer
                                    .setSpriteIndex((int) 7);
                        } else {
                            this.dialogManager.currentDialog.getChildById(var1 + 23).getComponentData().renderer
                                    .setSpriteIndex((int) 14);
                        }
                    }
                }

                return;
            case 10:
                this.dialogManager.currentDialog.getChildById(24).setVisible(false);
                this.dialogManager.currentDialog.getChildById(24).cleanUp();
                if (this.dialogManager.currentDialog.getChildById(25).getComponentData().renderer == null) {
                    this.dialogManager.currentDialog.getChildById(25).getComponentData().renderer = new SpriteRenderer();
                    this.dialogManager.currentDialog.getChildById(25).getComponentData().renderer.spriteType = 2;
                    this.dialogManager.currentDialog.getChildById(25).getComponentData().renderer.setSpriteIndex((int) 0);
                    this.dialogManager.currentDialog.getChildById(25).getComponentData().renderer.initializeSprite(336, false,
                            (byte) 0);
                    this.dialogManager.currentDialog.getChildById(25).getComponentData().renderer.setSpriteIndex((int) 15);
                    return;
                }
                break;
            case 11:
                this.dialogManager.currentDialog.getChildById(25).setVisible(false);
                this.dialogManager.currentDialog.getChildById(25).cleanUp();
                return;
            case 12:
                if (this.dialogManager.currentDialog.getChildById(26).getComponentData().renderer == null) {
                    this.dialogManager.currentDialog.getChildById(26).getComponentData().renderer = new SpriteRenderer();
                    this.dialogManager.currentDialog.getChildById(26).getComponentData().renderer.spriteType = 2;
                    this.dialogManager.currentDialog.getChildById(26).getComponentData().renderer.setSpriteIndex((int) 0);
                    this.dialogManager.currentDialog.getChildById(26).getComponentData().renderer.initializeSprite(336, false,
                            (byte) 0);
                    this.dialogManager.currentDialog.getChildById(26).getComponentData().renderer.setSpriteIndex((int) 5);
                    return;
                }
                break;
            case 13:
                this.dialogManager.currentDialog.getChildById(26).setVisible(false);
                this.dialogManager.currentDialog.getChildById(26).cleanUp();
                if (this.dialogManager.currentDialog.getChildById(27).getComponentData().renderer == null) {
                    this.dialogManager.currentDialog.getChildById(27).getComponentData().renderer = new SpriteRenderer();
                    this.dialogManager.currentDialog.getChildById(27).getComponentData().renderer.spriteType = 2;
                    this.dialogManager.currentDialog.getChildById(27).getComponentData().renderer.setSpriteIndex((int) 0);
                    this.dialogManager.currentDialog.getChildById(27).getComponentData().renderer.initializeSprite(336, false,
                            (byte) 0);
                    this.dialogManager.currentDialog.getChildById(27).getComponentData().renderer.setSpriteIndex((int) 5);
                    return;
                }
                break;
            case 14:
                return;
            case 15:
                if (this.dialogManager.currentDialog.getChildById(28).getComponentData().renderer == null) {
                    this.dialogManager.currentDialog.getChildById(28).getComponentData().renderer = new SpriteRenderer();
                    this.dialogManager.currentDialog.getChildById(28).getComponentData().renderer.spriteType = 2;
                    this.dialogManager.currentDialog.getChildById(28).getComponentData().renderer.setSpriteIndex((int) 0);
                    this.dialogManager.currentDialog.getChildById(28).getComponentData().renderer.initializeSprite(336, false,
                            (byte) 0);
                    this.dialogManager.currentDialog.getChildById(28).getComponentData().renderer.setSpriteIndex((int) 6);
                    return;
                }
                break;
            case 16:
                this.dialogManager.currentDialog.getChildById(28).setVisible(false);
                this.dialogManager.currentDialog.getChildById(28).cleanUp();
                if (this.dialogManager.currentDialog.getChildById(29).getComponentData().renderer == null) {
                    this.dialogManager.currentDialog.getChildById(29).getComponentData().renderer = new SpriteRenderer();
                    this.dialogManager.currentDialog.getChildById(29).getComponentData().renderer.spriteType = 2;
                    this.dialogManager.currentDialog.getChildById(29).getComponentData().renderer.setSpriteIndex((int) 0);
                    this.dialogManager.currentDialog.getChildById(29).getComponentData().renderer.initializeSprite(336, false,
                            (byte) 0);
                    this.dialogManager.currentDialog.getChildById(29).getComponentData().renderer.setSpriteIndex((int) 6);
                    return;
                }
                break;
            case 17:
                this.dialogManager.currentDialog.getChildById(29).setVisible(false);
                this.dialogManager.currentDialog.getChildById(29).cleanUp();
                if (this.dialogManager.currentDialog.getChildById(30).getComponentData().renderer == null) {
                    this.dialogManager.currentDialog.getChildById(30).getComponentData().renderer = new SpriteRenderer();
                    this.dialogManager.currentDialog.getChildById(30).getComponentData().renderer.spriteType = 2;
                    this.dialogManager.currentDialog.getChildById(30).getComponentData().renderer.setSpriteIndex((int) 0);
                    this.dialogManager.currentDialog.getChildById(30).getComponentData().renderer.initializeSprite(336, false,
                            (byte) 0);
                    this.dialogManager.currentDialog.getChildById(30).getComponentData().renderer.setSpriteIndex((int) 6);
                    return;
                }
                break;
            case 18:
            case 19:
                this.dialogManager.currentDialog.getChildById(30).setVisible(false);
                this.dialogManager.currentDialog.getChildById(30).cleanUp();
                if (this.dialogManager.currentDialog.getChildById(31).getComponentData().renderer == null) {
                    this.dialogManager.currentDialog.getChildById(31).getComponentData().renderer = new SpriteRenderer();
                    this.dialogManager.currentDialog.getChildById(31).getComponentData().renderer.spriteType = 2;
                    this.dialogManager.currentDialog.getChildById(31).getComponentData().renderer.setSpriteIndex((int) 0);
                    this.dialogManager.currentDialog.getChildById(31).getComponentData().renderer.initializeSprite(336, false,
                            (byte) 0);
                    this.dialogManager.currentDialog.getChildById(31).getComponentData().renderer.setSpriteIndex((int) 6);
                    return;
                }
                break;
            case 20:
                ScreenTransitionManager.getInstance().startTransition(16777215, 2);
                ScreenTransitionManager.getInstance().alphaStep = 85;
                return;
            case 21:
            case 22:
            case 23:
                this.dialogManager.currentDialog.getChildById(18).setVisible(false);
                this.dialogManager.currentDialog.getChildById(18).cleanUp();
                this.dialogManager.currentDialog.getChildById(23).setVisible(false);
                this.dialogManager.currentDialog.getChildById(23).cleanUp();
                this.dialogManager.currentDialog.getChildById(27).setVisible(false);
                this.dialogManager.currentDialog.getChildById(27).cleanUp();
                this.dialogManager.currentDialog.getChildById(31).setVisible(false);
                this.dialogManager.currentDialog.getChildById(31).cleanUp();
                this.dialogManager.currentDialog.getChildById(14).setVisible(false);
                this.dialogManager.currentDialog.getChildById(15).setVisible(false);
                return;
            case 24:
                ScreenTransitionManager.getInstance().startTransition(16777215, 1);
                ScreenTransitionManager.getInstance().alphaStep = 255;
                if (this.dialogManager.currentDialog.getChildById(32).getComponentData().renderer == null) {
                    this.dialogManager.currentDialog.getChildById(32).getComponentData().renderer = new SpriteRenderer();
                    this.dialogManager.currentDialog.getChildById(32).getComponentData().renderer.spriteType = 2;
                    this.dialogManager.currentDialog.getChildById(32).getComponentData().renderer.setSpriteIndex((int) 0);
                    this.dialogManager.currentDialog.getChildById(32).getComponentData().renderer.initializeSprite(336, false,
                            (byte) 0);
                    return;
                }
                break;
            case 25:
                this.dialogManager.currentDialog.getChildById(32).setVisible(false);
                this.dialogManager.currentDialog.getChildById(32).cleanUp();

                for (var1 = 0; var1 < 5; ++var1) {
                    if (this.dialogManager.currentDialog.getChildById(var1 + 33).getComponentData().renderer == null) {
                        this.dialogManager.currentDialog.getChildById(var1 + 33).getComponentData().renderer = new SpriteRenderer();
                        this.dialogManager.currentDialog.getChildById(var1 + 33).getComponentData().renderer.spriteType = 2;
                        this.dialogManager.currentDialog.getChildById(var1 + 33).getComponentData().renderer.initializeSprite(336,
                                false, (byte) 0);
                        if (var1 == 0) {
                            this.dialogManager.currentDialog.getChildById(var1 + 33).getComponentData().renderer
                                    .setSpriteIndex((int) 0);
                        } else if (var1 == 1) {
                            this.dialogManager.currentDialog.getChildById(var1 + 33).getComponentData().renderer
                                    .setSpriteIndex((int) 8);
                        } else if (var1 == 2) {
                            this.dialogManager.currentDialog.getChildById(var1 + 33).getComponentData().renderer
                                    .setSpriteIndex((int) 5);
                        } else if (var1 == 3) {
                            this.dialogManager.currentDialog.getChildById(var1 + 33).getComponentData().renderer
                                    .setSpriteIndex((int) 7);
                        } else if (var1 == 4) {
                            this.dialogManager.currentDialog.getChildById(var1 + 33).getComponentData().renderer
                                    .setSpriteIndex((int) 6);
                        }
                    }
                }

                return;
            case 26:
                GameScreenManager.getInstance().backgroundEffect = ImageProcessor
                        .applyAlpha(GameScreenManager.getInstance().backgroundEffect, var2);
                return;
            case 27:
                if (var2 > 38) {
                    this.dialogManager.currentDialog.getChildById(var2 - 1).setVisible(false);
                    this.dialogManager.currentDialog.getChildById(var2 - 1).cleanUp();
                }

                if (this.dialogManager.currentDialog.getChildById(var2).getComponentData().renderer == null) {
                    this.dialogManager.currentDialog.getChildById(var2).getComponentData().renderer = new SpriteRenderer();
                    this.dialogManager.currentDialog.getChildById(var2).getComponentData().renderer.spriteType = 2;
                    this.dialogManager.currentDialog.getChildById(var2).getComponentData().renderer.setSpriteIndex((int) 4);
                    this.dialogManager.currentDialog.getChildById(var2).getComponentData().renderer.initializeSprite(336, false,
                            (byte) 0);
                    return;
                }
                break;
            case 28:
                if (var2 > 43) {
                    this.dialogManager.currentDialog.getChildById(var2 - 1).setVisible(false);
                    this.dialogManager.currentDialog.getChildById(var2 - 1).cleanUp();
                }

                if (this.dialogManager.currentDialog.getChildById(var2).getComponentData().renderer == null) {
                    this.dialogManager.currentDialog.getChildById(var2).getComponentData().renderer = new SpriteRenderer();
                    this.dialogManager.currentDialog.getChildById(var2).getComponentData().renderer.spriteType = 2;
                    this.dialogManager.currentDialog.getChildById(var2).getComponentData().renderer.setSpriteIndex((int) 1);
                    this.dialogManager.currentDialog.getChildById(var2).getComponentData().renderer.initializeSprite(336, false,
                            (byte) 0);
                }
        }

    }

    public final void showHelpFromSystem() {
        this.dialogManager.showDialog("/data/ui/help1.ui", 257, this);
        this.dialogManager.removeDialog("/data/ui/gamesystem.ui");
        this.optionIndex = 0;
        this.dialogManager.currentDialog.getChildById(6).setVisible(true);
        this.dialogManager.currentDialog.getChildById(7).setVisible(false);
        this.updateHelpPageContent(this.optionIndex);
    }

    public final void updateHelpFromSystem() {
        if (this.gameEngine.isKeyPressed(16400)) {
            --this.optionIndex;
            if (this.optionIndex <= 0) {
                this.optionIndex = 0;
            }

            this.updateHelpPageContent(this.optionIndex);
        } else if (this.gameEngine.isKeyPressed(32832)) {
            ++this.optionIndex;
            if (this.optionIndex >= 2) {
                this.optionIndex = 2;
            }

            this.updateHelpPageContent(this.optionIndex);
        } else {
            if (this.gameEngine.isKeyPressed(262144)) {
                this.gameEngine.changeState((byte) 13);
                this.dialogManager.removeDialog("/data/ui/help1.ui");
            }

        }
    }

    public final void showOptionsFromSystem() {
        this.showOptionsMenu();
        this.dialogManager.removeDialog("/data/ui/gamesystem.ui");
    }

    public final void updateOptionsFromSystem() {
        if (this.gameEngine.isKeyPressed(16400)) {
            this.dialogManager.currentDialog.handleAction(2);
            GameScreenManager.getInstance().decreaseDifficulty();
            if (WorldGameSession.getInstance().audioManager != null) {
                WorldGameSession.getInstance().audioManager.setMasterVolume(GameScreenManager.getInstance().difficultyLevel);
            }

            this.updateVolumeDisplay();
        } else if (this.gameEngine.isKeyPressed(32832)) {
            this.dialogManager.currentDialog.handleAction(3);
            GameScreenManager.getInstance().increaseDifficulty();
            if (WorldGameSession.getInstance().audioManager != null) {
                WorldGameSession.getInstance().audioManager.setMasterVolume(GameScreenManager.getInstance().difficultyLevel);
            }

            this.updateVolumeDisplay();
        } else {
            if (this.gameEngine.isKeyPressed(131072)) {
                this.gameEngine.changeState((byte) 13);
                this.dialogManager.removeDialog("/data/ui/help.ui");
            }

        }
    }

    public final void showPokemonStorageMenu() {
        this.dialogManager.showDialog("/data/ui/petstate.ui", 257, this);
        this.dialogState = 0;
        if (this.player.pokemonStorage.size() > 6) {
            ((UIContainerComponent) this.dialogManager.currentDialog.getChildById(0)).primaryListComponent.setDisplayMode(1);
        } else {
            ((UIContainerComponent) this.dialogManager.currentDialog.getChildById(0)).primaryListComponent.setDisplayMode(-1);
        }

        this.dialogManager.currentDialog.getChildById(2).getComponentData().text = "Ngân hàng Sủng vật";
        this.dialogManager.currentDialog.getChildById(75).setVisible(false);
        this.dialogManager.currentDialog.getChildById(76).setVisible(false);
        this.updateStorageDisplay();
    }

    private void updateStorageDisplay() {
        ((UIContainerComponent) this.dialogManager.currentDialog
                .getChildById(0)).primaryListComponent.totalItemCount = this.player.pokemonStorage.size();
        this.listScrollOffset = ((UIContainerComponent) this.dialogManager.currentDialog
                .getChildById(0)).primaryListComponent.scrollOffset;
        this.listSelectedIndex = ((UIContainerComponent) this.dialogManager.currentDialog
                .getChildById(0)).primaryListComponent.selectedIndex;
        if (this.player.pokemonStorage.size() >= 6) {
            ((UIContainerComponent) this.dialogManager.currentDialog.getChildById(0)).primaryListComponent.visibleItemCount = 6;
        } else {
            ((UIContainerComponent) this.dialogManager.currentDialog
                    .getChildById(0)).primaryListComponent.visibleItemCount = this.player.pokemonStorage.size();
        }

        if (this.listSelectedIndex >= this.player.pokemonStorage.size()) {
            this.listSelectedIndex = this.player.pokemonStorage.size() - 1;
            ((UIContainerComponent) this.dialogManager.currentDialog
                    .getChildById(0)).primaryListComponent.selectedIndex = this.listSelectedIndex;
        }

        if (this.listScrollOffset > 0 && this.listSelectedIndex - this.listScrollOffset < 5) {
            --this.listScrollOffset;
            ((UIContainerComponent) this.dialogManager.currentDialog
                    .getChildById(0)).primaryListComponent.scrollOffset = this.listScrollOffset;
        }

        int var1;
        for (var1 = 0; var1 < 6; ++var1) {
            if (this.listScrollOffset + var1 < this.player.pokemonStorage.size()) {
                int[] var2 = (int[]) this.player.pokemonStorage.elementAt(this.listScrollOffset + var1);
                if (var1 == 0) {
                    this.dialogManager.currentDialog.getChildById(14 + var1 * 6).getComponentData().text = ""
                            + (this.listScrollOffset + var1 + 1);
                } else {
                    this.dialogManager.currentDialog.getChildById(15 + var1 * 6).getComponentData().text = ""
                            + (this.listScrollOffset + var1 + 1);
                }

                this.dialogManager.currentDialog.getChildById(16 + var1 * 6).getComponentData().text = "#P"
                        + var2[6] * 100 / PokemonEntity.calculateStat(var2[0], var2[1], var2[4], 1);
                this.dialogManager.currentDialog.getChildById(17 + var1 * 6).getComponentData().text = "#P"
                        + PokemonEntity.calculateExpPercentStatic((short) var2[7], (short) var2[1]);
            } else {
                this.dialogManager.currentDialog.getChildById(16 + var1 * 6).getComponentData().text = "#P0";
                this.dialogManager.currentDialog.getChildById(17 + var1 * 6).getComponentData().text = "#P0";
            }
        }

        int[] var4 = null;
        if (this.player.pokemonStorage.size() > 0) {
            this.dialogManager.currentDialog.getChildById(64).setVisible(true);
            var4 = (int[]) this.player.pokemonStorage.elementAt(this.listSelectedIndex);
        } else {
            this.dialogManager.currentDialog.getChildById(64).setVisible(false);
        }

        if (var4 != null) {
            if (this.dialogManager.currentDialog.getChildById(48).getComponentData().renderer != null) {
                this.dialogManager.currentDialog.getChildById(48).getComponentData().renderer.cleanup();
            } else {
                this.dialogManager.currentDialog.getChildById(48).getComponentData().renderer = new SpriteRenderer();
                this.dialogManager.currentDialog.getChildById(48).getComponentData().renderer.setSpriteIndex((int) 0);
                this.dialogManager.currentDialog.getChildById(48).getComponentData().renderer.spriteType = 3;
            }

            this.dialogManager.currentDialog.getChildById(48).getComponentData().renderer.initializeSprite(
                    ResourceManager.getDatabaseValue((byte) 0, (short) var4[0], (byte) 17), false, (byte) -1);
            this.dialogManager.currentDialog.getChildById(51).getComponentData().text = GameEngineBase
                    .getLocalizedText((int) ResourceManager.getDatabaseValue((byte) 0, (short) var4[0], (byte) 0));
            this.dialogManager.currentDialog.getChildById(52).getComponentData().text = GameEngineBase
                    .getLocalizedText(365 + ResourceManager.getDatabaseValue((byte) 0, (short) var4[0], (byte) 1));
            if (ResourceManager.getDatabaseValue((byte) 0, (short) var4[0], (byte) 19) == -1) {
                this.dialogManager.currentDialog.getChildById(62).getComponentData().text = "";
            } else if (ResourceManager.gameDatabase[0][ResourceManager.getDatabaseValue((byte) 0, (short) var4[0],
                    (byte) 19)][2] != 1
                    && ResourceManager.gameDatabase[0][ResourceManager.getDatabaseValue((byte) 0, (short) var4[0],
                    (byte) 19)][2] != 2) {
                if (ResourceManager.gameDatabase[0][ResourceManager.getDatabaseValue((byte) 0, (short) var4[0],
                        (byte) 19)][2] == 3) {
                    this.dialogManager.currentDialog.getChildById(62).getComponentData().text = "Có thể dị hoá";
                }
            } else {
                this.dialogManager.currentDialog.getChildById(62).getComponentData().text = "Có thể tiến hóa";
            }

            this.dialogManager.currentDialog.getChildById(61).getComponentData().text = PokemonEntity.getTypeNameById(var4[0]);
            if (this.dialogManager.currentDialog.getChildById(59).getComponentData().renderer == null) {
                this.dialogManager.currentDialog.getChildById(59).getComponentData().renderer = new SpriteRenderer();
                this.dialogManager.currentDialog.getChildById(59).getComponentData().renderer.setSpriteIndex((int) 0);
                this.dialogManager.currentDialog.getChildById(59).getComponentData().renderer.spriteType = 2;
                this.dialogManager.currentDialog.getChildById(59).getComponentData().renderer.initializeSprite(258, false,
                        (byte) -1);
            }

            if (var4[2] != -1) {
                this.dialogManager.currentDialog.getChildById(59).getComponentData().renderer
                        .setSpriteIndex((int) ResourceManager.gameDatabase[3][var4[2]][1]);
                this.dialogManager.currentDialog.getChildById(60).getComponentData().text = GameEngineBase
                        .getLocalizedText((int) ResourceManager.gameDatabase[3][var4[2]][0]);
            } else {
                this.dialogManager.currentDialog.getChildById(59).getComponentData().renderer.setSpriteIndex((int) 0);
                this.dialogManager.currentDialog.getChildById(60).getComponentData().text = "";
            }

            this.dialogManager.currentDialog.getChildById(65).getComponentData().text = "" + var4[1];
            this.dialogManager.currentDialog.getChildById(66).getComponentData().text = ""
                    + PokemonEntity.calculateStat(var4[0], var4[1], var4[4], 2);
            this.dialogManager.currentDialog.getChildById(67).getComponentData().text = ""
                    + PokemonEntity.calculateStat(var4[0], var4[1], var4[4], 3);
            this.dialogManager.currentDialog.getChildById(68).getComponentData().text = ""
                    + PokemonEntity.calculateStat(var4[0], var4[1], var4[4], 4);
            int var5 = var4[4];
            var1 = ResourceManager.getDatabaseValue((byte) 0, (short) var4[0], (byte) 4) - 1;

            for (int var3 = 0; var3 < 5; ++var3) {
                this.dialogManager.currentDialog.getChildById(74 - var3).setVisible(true);
                this.dialogManager.currentDialog.getChildById(74 - var3).getComponentData().renderer.spriteType = 3;
                if (var3 > var1) {
                    this.dialogManager.currentDialog.getChildById(74 - var3).setVisible(false);
                } else if (var5 > 0) {
                    this.dialogManager.currentDialog.getChildById(74 - var3).getComponentData().renderer
                            .setAnimationFrame((byte) 14, (byte) -1);
                    --var5;
                } else {
                    this.dialogManager.currentDialog.getChildById(74 - var3).getComponentData().renderer
                            .setAnimationFrame((byte) 16, (byte) -1);
                }
            }

            if (this.menuIndex == 1) {
                this.dialogManager.currentDialog.getChildById(64).getComponentData().text = "Lấy ra";
                return;
            }

            if (this.menuIndex == 2) {
                this.dialogManager.currentDialog.getChildById(64).getComponentData().text = "Phóng sinh";
            }
        }

    }

    public final void updatePokemonStorageMenu() {
        if (this.dialogState == 0 && this.gameEngine.isKeyPressed(4100)) {
            this.dialogManager.currentDialog.handleAction(0);
            this.updateStorageDisplay();
        } else if (this.dialogState == 0 && this.gameEngine.isKeyPressed(8448)) {
            this.dialogManager.currentDialog.handleAction(1);
            this.updateStorageDisplay();
        }

        int[] var1;
        if (this.dialogState == 0) {
            if (this.gameEngine.isKeyPressed(196640)) {
                if (this.menuIndex == 1) {
                    if (this.player.partySize >= 6) {
                        this.dialogManager.showDialog("/data/ui/msgwarm.ui", 257, this);
                        this.setWarningText("Ba lô Sủng vật đã đủ", "Nhấn nút 5 tiếp tục");
                        this.dialogState = 1;
                    } else {
                        if (this.player.pokemonStorage.size() <= 0) {
                            return;
                        }

                        this.player.withdrawFromStorage(this.listSelectedIndex);
                        if (this.player.pokemonStorage.size() <= 0) {
                            this.gameEngine.changeState((byte) 16);
                            this.dialogManager.removeDialog("/data/ui/petstate.ui");
                        } else {
                            this.updateStorageDisplay();
                        }
                    }
                } else if (this.menuIndex == 2) {
                    if (this.player.pokemonStorage.size() <= 0) {
                        return;
                    }

                    var1 = (int[]) this.player.pokemonStorage.elementAt(this.listSelectedIndex);
                    if (ResourceManager.getDatabaseValue((byte) 0, (short) var1[0], (byte) 22) == 2) {
                        this.dialogState = 2;
                        this.showWarningDialog();
                        this.setWarningText("Thần thú không thể phóng sinh", "Nhấn nút 5 tiếp tục");
                    } else {
                        this.dialogState = 1;
                        this.dialogManager.showDialog("/data/ui/msgconfirm.ui", 257, this);
                        this.showConfirmMessage("Bạn muốn phóng sinh sủng vật này?", "Xác nhận");
                    }
                }
            } else if (this.gameEngine.isKeyPressed(786432)) {
                this.gameEngine.changeState((byte) 16);
                this.dialogManager.removeDialog("/data/ui/petstate.ui");
            }
        } else if (this.dialogState > 0) {
            if (this.gameEngine.isKeyPressed(196640) && this.menuIndex == 1
                    || this.gameEngine.isKeyPressed(131072) && this.menuIndex == 2 && this.dialogState == 1
                    || this.gameEngine.isKeyPressed(196640) && this.menuIndex == 2 && this.dialogState == 2) {
                if (this.menuIndex == 1) {
                    this.dialogManager.removeDialog("/data/ui/msgwarm.ui");
                    this.dialogState = 0;
                } else if (this.menuIndex == 2) {
                    if (this.dialogState == 1) {
                        this.dialogManager.removeDialog("/data/ui/msgconfirm.ui");
                        var1 = (int[]) this.player.pokemonStorage.elementAt(this.listSelectedIndex);
                        this.player.deactivateSkill(var1[2]);
                        this.player.removeFromStorage(this.listSelectedIndex);
                        this.updateStorageDisplay();
                    } else if (this.dialogState == 2) {
                        this.hideWarningDialog();
                    }

                    this.dialogState = 0;
                }
            } else if (this.gameEngine.isKeyPressed(786432)) {
                if (this.menuIndex == 1) {
                    return;
                }

                this.dialogManager.removeDialog("/data/ui/msgconfirm.ui");
                this.dialogState = 0;
            }
        }

        this.needsRedraw = true;
    }

    public final void showPokemonBankMenu() {
        this.hideWorldUIElements();
        this.dialogManager.showDialog("/data/ui/shop.ui", 257, this);
        this.menuIndex = 0;
        this.dialogManager.currentDialog.getChildById(5).getComponentData().text = "Ngân hàng Sủng vật";
        this.dialogManager.currentDialog.getChildById(6).getComponentData().text = "Gởi lại";
        this.dialogManager.currentDialog.getChildById(7).getComponentData().text = "Lấy ra";
        this.dialogManager.currentDialog.getChildById(9).getComponentData().text = "Phóng sinh";
    }

    public final void updatePokemonBankMenu() {
        if (this.gameEngine.isKeyPressed(4100)) {
            this.dialogManager.currentDialog.handleAction(0);
        } else if (this.gameEngine.isKeyPressed(8448)) {
            this.dialogManager.currentDialog.handleAction(1);
        } else if (this.gameEngine.isKeyPressed(196640)) {
            switch (this.menuIndex) {
                case 0:
                    this.subMenuIndex = 0;
                    this.gameEngine.changeState((byte) 7);
                    this.dialogManager.removeDialog("/data/ui/shop.ui");
                    return;
                case 1:
                    this.gameEngine.changeState((byte) 15);
                    this.dialogManager.removeDialog("/data/ui/shop.ui");
                    return;
                case 2:
                    this.gameEngine.changeState((byte) 15);
                    this.dialogManager.removeDialog("/data/ui/shop.ui");
                    return;
                case 3:
                    QuestManager.isChangingState = true;
                    this.dialogManager.removeDialog("/data/ui/shop.ui");
                    this.gameEngine.changeState((byte) 0);
                default:
            }
        } else {
            if (this.gameEngine.isKeyPressed(262144)) {
                QuestManager.isChangingState = true;
                this.dialogManager.removeDialog("/data/ui/shop.ui");
                this.gameEngine.changeState((byte) 0);
            }

        }
    }

    public final void showShopTypeMenu() {
        this.hideWorldUIElements();
        this.dialogManager.showDialog("/data/ui/shop.ui", 257, this);
        this.menuIndex = 0;
    }

    public final void updateShopTypeMenu() {
        this.gameEngine.updateActionSequence();
        if (!GameEngineBase.isActionBlocked((int) this.menuIndex, (int) 0) && !this.isPopupActive() && this.dialogState == 0
                && this.gameEngine.isKeyPressed(4100) && this.isNotShowingTipDialog()) {
            this.dialogManager.currentDialog.handleAction(0);
        } else if (!GameEngineBase.isActionBlocked((int) this.menuIndex, (int) 0) && !this.isPopupActive() && this.dialogState == 0
                && this.gameEngine.isKeyPressed(8448) && this.isNotShowingTipDialog()) {
            this.dialogManager.currentDialog.handleAction(1);
        } else if (this.isNotShowingTipDialog() && !this.isPopupActive() && GameEngineBase.isConfirmAllowed() && this.gameEngine.isKeyPressed(196640)) {
            if (GameEngineBase.isActionActive() && !GameEngineBase.isActionBlocked((int) this.menuIndex, (int) 0)) {
                return;
            }

            switch (this.menuIndex) {
                case 0:
                    this.gameEngine.handleActionResponse();
                    this.gameEngine.changeState((byte) 2);
                    this.dialogManager.removeDialog("/data/ui/shop.ui");
                    break;
                case 1:
                    this.gameEngine.changeState((byte) 3);
                    this.dialogManager.removeDialog("/data/ui/shop.ui");
                    break;
                case 2:
                    int var1;
                    if (this.dialogState == 0) {
                        if (PlayerCharacter.getInstance().getMaxHealthDifference() == -1) {
                            this.dialogState = 6;
                            this.showWarningDialog();
                            this.setWarningText("Toàn bộ trạng thái đã đầy, không cần khôi phục", "Nhấn nút 5 tiếp tục");
                        } else if (!GameEngineBase.paymentActive) {
                            this.dialogState = 3;

                            for (var1 = 0; var1 < this.player.partySize; ++var1) {
                                this.player.partyPokemon[var1].fullRestore();
                            }

                            this.showQuickMessage("Ba lô sủng vật trạng thái toàn bộ khôi phục");
                        } else {
                            this.dialogManager.showDialog("/data/ui/msgRecover.ui", 257, this);
                            this.dialogManager.currentDialog.getChildById(4)
                                    .getComponentData().text = "Có khôi phục trạng thái ba lô sủng vật không?";
                            this.dialogManager.currentDialog.getChildById(5).getComponentData().text = "Cần tiền tài: ";
                            this.dialogManager.currentDialog.getChildById(6).getComponentData().text = "" + PlayerCharacter.getInstance().getMaxHealthDifference();
                            this.dialogManager.currentDialog.getChildById(8).getComponentData().text = "" + PlayerCharacter.getInstance().getGold();
                            this.dialogManager.removeDialog("/data/ui/shop.ui");
                            this.dialogState = 1;
                        }
                    } else if (this.dialogState == 1) {
                        var1 = PlayerCharacter.getInstance().getMaxHealthDifference();
                        if (!PlayerCharacter.getInstance().hasEnoughGold(var1)) {
                            this.dialogState = 2;
                            this.showWarningDialog();
                            this.setWarningText("Kim tiền chưa đủ", "Nhấn nút 5 tiếp tục");
                        } else {
                            this.dialogState = 3;
                            PlayerCharacter.getInstance().addGold(-var1);

                            for (var1 = 0; var1 < this.player.partySize; ++var1) {
                                this.player.partyPokemon[var1].fullRestore();
                            }

                            this.showQuickMessage("Ba lô sủng vật trạng thái toàn bộ khôi phục");
                        }

                        this.dialogManager.removeDialog("/data/ui/msgRecover.ui");
                    } else {
                        if (this.dialogState == 2 && GameEngineBase.paymentActive) {
                            this.gameEngine.changeState((byte) 102);
                        }

                        this.dialogState = 0;
                        this.hideWarningDialog();
                    }
                    break;
                case 3:
                    QuestManager.isChangingState = true;
                    this.dialogManager.removeDialog("/data/ui/shop.ui");
                    this.gameEngine.changeState((byte) 0);
            }
        } else if (!this.isPopupActive() && this.gameEngine.isKeyPressed(262144) && GameEngineBase.isCancelAllowed() && this.isNotShowingTipDialog()) {
            if (this.dialogState == 1) {
                this.dialogManager.showDialog("/data/ui/shop.ui", 257, this);
                this.dialogManager.removeDialog("/data/ui/msgRecover.ui");
                this.menuIndex = 0;
                this.dialogState = 0;
            } else if (this.dialogState == 0) {
                QuestManager.isChangingState = true;
                this.dialogManager.removeDialog("/data/ui/shop.ui");
                this.gameEngine.changeState((byte) 0);
            }
        }

        if (this.dialogState == 3 && this.isMessageAnimationComplete()) {
            this.dialogState = 4;
            this.dialogManager.showDialog("/data/ui/shop.ui", 257, this);
            this.showTipDialog();
            this.setTipText("Đang lưu...");
            this.hideConfirmButtons();
        } else if (this.dialogState == 4 && ((WorldGameSession) this.gameEngine).saveAllGameData()) {
            this.setTipText("Lưu thành công");
            this.dialogState = 5;
        } else if (this.dialogState == 5) {
            this.hideTipDialog();
            this.dialogState = 0;
            this.menuIndex = 0;
        }

        this.handleOpenBoxDialog();
        this.needsRedraw = true;
    }

    public final void showBuyMenu(int var1, byte var2) {
        this.dialogManager.showDialog("/data/ui/shopbuy.ui", 257, this);
        this.menuIndex = 0;
        this.dialogState = 0;
        short[][] var10001 = ResourceManager.gameDatabase[var1];
        ((UIContainerComponent) this.dialogManager.currentDialog
                .getChildById(0)).primaryListComponent.totalItemCount = var10001.length;
        ((UIContainerComponent) this.dialogManager.currentDialog.getChildById(0)).primaryListComponent.setDisplayMode(1);
        this.dialogManager.currentDialog.getChildById(5).getComponentData().text = "Mua";
        if (this.gameEngine instanceof WorldGameSession) {
            this.dialogManager.currentDialog.getChildById(57).setVisible(true);
            this.dialogManager.currentDialog.getChildById(58).setVisible(true);
            this.dialogManager.currentDialog.getChildById(57).getComponentData().text = "Mua sắm";
            this.dialogManager.currentDialog.getChildById(58).getComponentData().text = "Quay lại";
            this.dialogManager.currentDialog.getChildById(39).setVisible(false);
            this.dialogManager.currentDialog.getChildById(40).setVisible(false);
        } else if (this.gameEngine instanceof BattleSystemManager) {
            this.dialogManager.currentDialog.getChildById(57).setVisible(false);
            this.dialogManager.currentDialog.getChildById(58).setVisible(false);
            this.dialogManager.currentDialog.getChildById(39).setVisible(true);
            this.dialogManager.currentDialog.getChildById(40).setVisible(true);
            this.dialogManager.currentDialog.getChildById(39).getComponentData().text = "Mua sắm";
            this.dialogManager.currentDialog.getChildById(40).getComponentData().text = "Quay lại";
        }

        this.updateShopBuyProcess(var1, var2);
    }

    private void updateShopBuyProcess(int var1, byte var2) {
        this.listScrollOffset = ((UIContainerComponent) this.dialogManager.currentDialog
                .getChildById(0)).primaryListComponent.scrollOffset;
        this.listSelectedIndex = ((UIContainerComponent) this.dialogManager.currentDialog
                .getChildById(0)).primaryListComponent.selectedIndex;

        for (int var3 = 0; var3 < 5; ++var3) {
            if (this.dialogManager.currentDialog.getChildById(var3 + 51).getComponentData().renderer == null) {
                this.dialogManager.currentDialog.getChildById(var3 + 51).getComponentData().renderer = new SpriteRenderer();
                this.dialogManager.currentDialog.getChildById(var3 + 51).getComponentData().renderer.setSpriteIndex((int) 0);
                this.dialogManager.currentDialog.getChildById(var3 + 51).getComponentData().renderer.spriteType = 2;
                this.dialogManager.currentDialog.getChildById(var3 + 51).getComponentData().renderer.initializeSprite(258, false,
                        (byte) -1);
            }

            this.dialogManager.currentDialog.getChildById(var3 + 51).getComponentData().renderer
                    .setSpriteIndex((int) ResourceManager.gameDatabase[var1][this.listScrollOffset + var3][1]);
            this.dialogManager.currentDialog.getChildById(14 + var3 * 5).getComponentData().text = GameEngineBase
                    .getLocalizedText((int) ResourceManager.gameDatabase[var1][this.listScrollOffset + var3][0]);
            if (this.gameEngine instanceof WorldGameSession) {
                if (this.shopType != 1 && this.shopType != 3) {
                    if (this.shopType == 2) {
                        if (ResourceManager.gameDatabase[var1][this.listScrollOffset + var3][4] == 0) {
                            this.dialogManager.currentDialog.getChildById(15 + var3 * 5).getComponentData().text = ""
                                    + ResourceManager.gameDatabase[var1][this.listScrollOffset + var3][3] * 3 / 2;
                        } else {
                            this.dialogManager.currentDialog.getChildById(15 + var3 * 5).getComponentData().text = ""
                                    + ResourceManager.gameDatabase[var1][this.listScrollOffset + var3][3];
                        }
                    }
                } else {
                    this.dialogManager.currentDialog.getChildById(15 + var3 * 5).getComponentData().text = ""
                            + ResourceManager.gameDatabase[var1][this.listScrollOffset + var3][3];
                }
            } else if (var2 == 0 && var1 == 4 && this.listScrollOffset + var3 == 0) {
                this.dialogManager.currentDialog.getChildById(15 + var3 * 5).getComponentData().text = ""
                        + ResourceManager.gameDatabase[var1][this.listScrollOffset + var3][3];
            } else {
                this.dialogManager.currentDialog.getChildById(15 + var3 * 5).getComponentData().text = ""
                        + (ResourceManager.gameDatabase[var1][this.listScrollOffset + var3][3] << 1);
            }

            if (ResourceManager.gameDatabase[var1][this.listScrollOffset + var3][4] == 0) {
                this.dialogManager.currentDialog.getChildById(var3 + 45).getComponentData().renderer.setSpriteIndex((int) 84);
            } else if (ResourceManager.gameDatabase[var1][this.listScrollOffset + var3][4] == 1) {
                this.dialogManager.currentDialog.getChildById(var3 + 45).getComponentData().renderer.setSpriteIndex((int) 83);
            } else if (ResourceManager.gameDatabase[var1][this.listScrollOffset + var3][4] == 2) {
                this.dialogManager.currentDialog.getChildById(var3 + 45).getComponentData().renderer.setSpriteIndex((int) 74);
            }
        }

        this.dialogManager.currentDialog.getChildById(56).getComponentData().text = GameEngineBase
                .getLocalizedText((int) ResourceManager.gameDatabase[var1][this.listSelectedIndex][2]);
        this.dialogManager.currentDialog.getChildById(43).getComponentData().text = "" + this.player.getBadges();
        this.dialogManager.currentDialog.getChildById(44).getComponentData().text = "" + this.player.getGold();
        this.dialogManager.currentDialog.getChildById(38).setOffsetY(
                102 + this.listSelectedIndex * 84 / ResourceManager.gameDatabase[var1].length,
                this.dialogManager.currentDialog.getUIContainerComponent());
    }

    public final void updateBuyProcess(byte var1, byte var2) {
        this.gameEngine.updateActionSequence();
        if (!GameEngineBase.isActionBlocked(this.menuIndex, 0) && this.dialogState <= 1 && this.gameEngine.isKeyPressed(4100)
                && !this.isPopupActive()) {
            this.dialogManager.currentDialog.handleAction(0);
            if (this.dialogState == 0) {
                this.updateShopBuyProcess((int) var1, var2);
            }
        } else if (!GameEngineBase.isActionBlocked(this.menuIndex, 0) && this.dialogState <= 1
                && this.gameEngine.isKeyPressed(8448)
                && !this.isPopupActive()) {
            this.dialogManager.currentDialog.handleAction(1);
            if (this.dialogState == 0) {
                this.updateShopBuyProcess((int) var1, var2);
            }
        } else if (this.dialogState == 1 && this.gameEngine.isKeyPressed(16400) && this.subMenuIndex > 0 && !this.isPopupActive()) {
            --this.subMenuIndex;
            if (this.subMenuIndex <= 0) {
                this.subMenuIndex = 99 - this.player.getItemCount(this.listSelectedIndex, var2);
            }

            this.updateBuyQuantityDisplay(this.subMenuIndex, this.subMenuIndex * ResourceManager.gameDatabase[var1][this.listSelectedIndex][3],
                    ResourceManager.gameDatabase[var1][this.listSelectedIndex][4], var1);
        } else if (this.dialogState == 1 && this.gameEngine.isKeyPressed(32832) && !this.isPopupActive()) {
            ++this.subMenuIndex;
            if (this.subMenuIndex > 99 - this.player.getItemCount(this.listSelectedIndex, var2)) {
                this.subMenuIndex = 1;
            }

            this.updateBuyQuantityDisplay(this.subMenuIndex, this.subMenuIndex * ResourceManager.gameDatabase[var1][this.listSelectedIndex][3],
                    ResourceManager.gameDatabase[var1][this.listSelectedIndex][4], var1);
        } else if (GameEngineBase.isConfirmAllowed() && this.gameEngine.isKeyPressed(196640) && !this.isPopupActive()) {
            if (GameEngineBase.isActionActive() && !GameEngineBase.isActionBlocked(this.menuIndex, 0)) {
                return;
            }

            if (ResourceManager.gameDatabase[var1][this.listSelectedIndex][4] == 2) {
                if (this.dialogState == 0) {
                    if (GameEngineBase.paymentActive) {
                        if (!this.player.canAddItem((int) this.listSelectedIndex, 1, (byte) 0)) {
                            this.dialogState = 3;
                            this.showWarningDialog();
                            this.setWarningText("Đạo cụ đã đủ", "Nhấn nút 5 tiếp tục");
                        } else {
                            this.gameEngine.changeState((byte) 101);
                        }
                    } else {
                        this.dialogState = 3;
                        this.dialogManager.showDialog("/data/ui/msgwarm.ui", 257, this);
                        this.setWarningText("Công năng còn chưa mở", "Nhấn nút 5 tiếp tục");
                    }
                } else {
                    this.dialogState = 0;
                    this.dialogManager.removeDialog("/data/ui/msgwarm.ui");
                }
            } else if (var2 == 2 && this.listSelectedIndex < 12) {
                this.subMenuIndex = 1;
                if (this.player.canAddItem(this.listSelectedIndex, this.subMenuIndex, var2)) {
                    if (this.dialogState == 0) {
                        this.optionIndex = 0;
                        this.processBuyConfirmation(var1, var2);
                    } else if (this.dialogState > 0) {
                        if (GameEngineBase.paymentActive) {
                            if (this.dialogState == 4) {
                                this.gameEngine.changeState((byte) 104);
                            } else if (this.dialogState == 3) {
                                this.gameEngine.changeState((byte) 102);
                            }
                        }

                        this.dialogState = 0;
                        this.dialogManager.removeDialog("/data/ui/msgwarm.ui");
                    }
                } else if (this.dialogState == 0) {
                    this.dialogState = 2;
                    this.dialogManager.showDialog("/data/ui/msgwarm.ui", 257, this);
                    this.setWarningText("Đạo cụ này đã đủ", "Nhấn nút 5 tiếp tục");
                } else {
                    this.gameEngine.handleActionResponse();
                    this.dialogState = 0;
                    this.dialogManager.removeDialog("/data/ui/msgwarm.ui");
                    this.updateShopBuyProcess((int) var1, var2);
                }
            } else if (this.player.canAddItem(this.listSelectedIndex, this.subMenuIndex, var2)) {
                if (this.dialogState == 0) {
                    this.dialogState = 1;
                    this.dialogManager.showDialog("/data/ui/msgyn.ui", 257, this);
                    this.subMenuIndex = 1;
                    this.optionIndex = 0;
                    this.updateBuyQuantityDisplay(this.subMenuIndex, this.subMenuIndex * ResourceManager.gameDatabase[var1][this.listSelectedIndex][3],
                            ResourceManager.gameDatabase[var1][this.listSelectedIndex][4], var1);
                } else if (this.dialogState == 1) {
                    this.processBuyConfirmation(var1, var2);
                } else if (this.dialogState == 2) {
                    WorldGameSession.getInstance().questManager.updateQuestEffects();
                    this.dialogState = 0;
                    this.subMenuIndex = 0;
                    this.dialogManager.removeDialog("/data/ui/msgwarm.ui");
                    this.updateShopBuyProcess((int) var1, var2);
                } else {
                    if (GameEngineBase.paymentActive) {
                        if (this.dialogState == 4) {
                            this.dialogManager.removeDialog("/data/ui/msgyn.ui");
                            this.gameEngine.changeState((byte) 104);
                        } else if (this.dialogState == 3) {
                            this.dialogManager.removeDialog("/data/ui/msgyn.ui");
                            this.gameEngine.changeState((byte) 102);
                        }
                    }

                    this.dialogState = 0;
                    this.subMenuIndex = 0;
                    this.dialogManager.removeDialog("/data/ui/msgwarm.ui");
                }
            } else if (this.dialogState == 0) {
                this.dialogState = 2;
                this.dialogManager.showDialog("/data/ui/msgwarm.ui", 257, this);
                this.setWarningText("Đạo cụ này đã đủ", "Nhấn nút 5 tiếp tục");
            } else {
                this.dialogState = 0;
                this.dialogManager.removeDialog("/data/ui/msgwarm.ui");
            }
        } else if (this.gameEngine.isKeyPressed(262144) && !this.isPopupActive() && GameEngineBase.isCancelAllowed()) {
            if (this.dialogState == 0) {
                if (this.gameEngine instanceof WorldGameSession) {
                    if (this.shopType == 1) {
                        this.gameEngine.changeState((byte) 1);
                    } else if (this.shopType == 2) {
                        this.gameEngine.changeState((byte) 14);
                    } else if (this.shopType == 3) {
                        this.gameEngine.changeState((byte) 27);
                    }

                    this.dialogManager.removeDialog("/data/ui/shopbuy.ui");
                } else {
                    this.dialogManager.removeDialog("/data/ui/shopbuy.ui");
                    this.gameEngine.changeState((byte) 20);
                }
            } else if (this.dialogState == 1) {
                this.dialogState = 0;
                this.subMenuIndex = 0;
                this.dialogManager.removeDialog("/data/ui/msgyn.ui");
            }
        }

        this.handleOpenBoxDialog();
    }

    private void processBuyConfirmation(byte var1, byte var2) {
        if ((!(this.gameEngine instanceof WorldGameSession) || this.shopType != 1 && this.shopType != 3
                || !this.player.canAfford((int) this.listSelectedIndex,
                (int) (this.subMenuIndex * ResourceManager.gameDatabase[var1][this.listSelectedIndex][3]), (int) var1))
                && (this.shopType != 2 || (ResourceManager.gameDatabase[var1][this.listSelectedIndex][4] != 0
                || !this.player.canAfford((int) this.listSelectedIndex,
                (int) (this.subMenuIndex * ResourceManager.gameDatabase[var1][this.listSelectedIndex][3] * 3 / 2), (int) var1))
                && (ResourceManager.gameDatabase[var1][this.listSelectedIndex][4] == 0 || !this.player.canAfford((int) this.listSelectedIndex,
                (int) (this.subMenuIndex * ResourceManager.gameDatabase[var1][this.listSelectedIndex][3]), (int) var1)))) {
            if (this.gameEngine instanceof BattleSystemManager && this.player.canAfford((int) this.listSelectedIndex,
                    (int) (this.subMenuIndex * ResourceManager.gameDatabase[var1][this.listSelectedIndex][3] << 1), (int) var1)) {
                if (this.optionIndex == 0) {
                    this.player.addItem(this.listSelectedIndex, this.subMenuIndex, var2);
                    if (ResourceManager.gameDatabase[var1][this.listSelectedIndex][4] == 0) {
                        this.player.addGold(-this.subMenuIndex * ResourceManager.gameDatabase[var1][this.listSelectedIndex][3] << 1);
                    } else {
                        this.player.addBadges(-this.subMenuIndex * ResourceManager.gameDatabase[var1][this.listSelectedIndex][3] << 1);
                    }

                    this.dialogManager.showDialog("/data/ui/msgwarm.ui", 257, this);
                    if (var1 == 3 && this.listSelectedIndex == 17) {
                        this.setWarningText("Đã thành công mua sắm #2"
                                + GameEngineBase.getLocalizedText(ResourceManager.gameDatabase[var1][this.listSelectedIndex][0]) + " * "
                                + 5 * this.subMenuIndex, "Nhấn nút 5 tiếp tục");
                    } else {
                        this.setWarningText("Đã thành công mua sắm #2"
                                + GameEngineBase.getLocalizedText(ResourceManager.gameDatabase[var1][this.listSelectedIndex][0]) + " * "
                                + this.subMenuIndex, "Nhấn nút 5 tiếp tục");
                    }

                    this.dialogState = 2;
                    this.subMenuIndex = 1;
                } else {
                    this.dialogState = 0;
                }

                this.dialogManager.removeDialog("/data/ui/msgyn.ui");
            } else {
                if (this.optionIndex == 0) {
                    this.dialogManager.showDialog("/data/ui/msgwarm.ui", 257, this);
                    if (ResourceManager.gameDatabase[var1][this.listSelectedIndex][4] == 0) {
                        this.dialogState = 3;
                        this.setWarningText("Kim tiền chưa đủ", "Nhấn nút 5 tiếp tục");
                    } else {
                        this.dialogState = 4;
                        this.setWarningText("Số lượng Huy hiệu chưa đủ", "Nhấn nút 5 tiếp tục");
                    }

                    int var3;
                    if (this.gameEngine instanceof WorldGameSession) {
                        label133:
                        {
                            if (this.shopType != 1 && this.shopType != 3) {
                                if (this.shopType != 2) {
                                    break label133;
                                }

                                if (ResourceManager.gameDatabase[var1][this.listSelectedIndex][4] == 0) {
                                    var3 = this.subMenuIndex * ResourceManager.gameDatabase[var1][this.listSelectedIndex][3] * 3 / 2;
                                } else {
                                    var3 = this.subMenuIndex * ResourceManager.gameDatabase[var1][this.listSelectedIndex][3];
                                }
                            } else {
                                var3 = this.subMenuIndex * ResourceManager.gameDatabase[var1][this.listSelectedIndex][3];
                            }

                            this.showSmsRewardDialog(new int[]{var1, var2, this.listSelectedIndex, ResourceManager.gameDatabase[var1][this.listSelectedIndex][4],
                                    var3, this.subMenuIndex});
                        }
                    } else if (this.gameEngine instanceof BattleSystemManager) {
                        var3 = this.subMenuIndex * ResourceManager.gameDatabase[var1][this.listSelectedIndex][3] << 1;
                        this.showSmsRewardDialog(new int[]{var1, var2, this.listSelectedIndex, ResourceManager.gameDatabase[var1][this.listSelectedIndex][4],
                                var3, this.subMenuIndex});
                    }
                } else {
                    this.dialogState = 0;
                }

                this.dialogManager.removeDialog("/data/ui/msgyn.ui");
            }
        } else {
            if (this.optionIndex == 0) {
                this.player.addItem(this.listSelectedIndex, this.subMenuIndex, var2);
                if (ResourceManager.gameDatabase[var1][this.listSelectedIndex][4] == 0) {
                    if (this.shopType != 1 && this.shopType != 3) {
                        if (this.shopType == 2) {
                            this.player.addGold(-this.subMenuIndex * ResourceManager.gameDatabase[var1][this.listSelectedIndex][3] * 3 / 2);
                        }
                    } else {
                        this.player.addGold(-this.subMenuIndex * ResourceManager.gameDatabase[var1][this.listSelectedIndex][3]);
                    }
                } else if (this.shopType != 1 && this.shopType != 3) {
                    if (this.shopType == 2) {
                        this.player.addBadges(-this.subMenuIndex * ResourceManager.gameDatabase[var1][this.listSelectedIndex][3]);
                    }
                } else {
                    this.player.addBadges(-this.subMenuIndex * ResourceManager.gameDatabase[var1][this.listSelectedIndex][3]);
                }

                this.dialogManager.showDialog("/data/ui/msgwarm.ui", 257, this);
                if (var1 == 3 && this.listSelectedIndex == 17) {
                    this.setWarningText("Đã thành công mua sắm #2"
                            + GameEngineBase.getLocalizedText((int) ResourceManager.gameDatabase[var1][this.listSelectedIndex][0])
                            + " * " + 5 * this.subMenuIndex, "Nhấn nút 5 tiếp tục");
                } else {
                    this.setWarningText("Đã thành công mua sắm #2"
                            + GameEngineBase.getLocalizedText((int) ResourceManager.gameDatabase[var1][this.listSelectedIndex][0])
                            + " * " + this.subMenuIndex, "Nhấn nút 5 tiếp tục");
                }

                this.dialogState = 2;
                this.subMenuIndex = 1;
            } else {
                this.dialogState = 0;
            }

            this.dialogManager.removeDialog("/data/ui/msgyn.ui");
        }
    }

    private void showSmsRewardDialog(int[] var1) {
        if (this.itemDisplayList == null) {
            this.itemDisplayList = new Vector();
        } else {
            this.itemDisplayList.removeAllElements();
        }

        this.itemDisplayList.addElement(var1);
    }

    private void updateBuyQuantityDisplay(int var1, int var2, int var3, int var4) {
        if (var4 == 3 && this.listSelectedIndex == 17) {
            this.dialogManager.currentDialog.getChildById(9).getComponentData().text = "" + var1 * 5;
        } else {
            this.dialogManager.currentDialog.getChildById(9).getComponentData().text = "" + var1;
        }

        if (this.gameEngine instanceof WorldGameSession) {
            if (this.shopType != 1 && this.shopType != 3) {
                if (this.shopType == 2) {
                    if (var3 == 0) {
                        this.dialogManager.currentDialog.getChildById(11).getComponentData().text = "" + var2 * 3 / 2;
                    } else {
                        this.dialogManager.currentDialog.getChildById(11).getComponentData().text = "" + var2;
                    }
                }
            } else {
                this.dialogManager.currentDialog.getChildById(11).getComponentData().text = "" + var2;
            }
        } else {
            this.dialogManager.currentDialog.getChildById(11).getComponentData().text = "" + (var2 << 1);
        }

        if (var3 == 0) {
            this.dialogManager.currentDialog.getChildById(12).getComponentData().renderer.setSpriteIndex((int) 84);
        } else {
            if (var3 == 1) {
                this.dialogManager.currentDialog.getChildById(12).getComponentData().renderer.setSpriteIndex((int) 83);
            }

        }
    }

    public final void showWarningDialog() {
        this.dialogManager.showDialog("/data/ui/msgwarm.ui", 257, this);
    }

    public final void hideWarningDialog() {
        this.dialogManager.removeDialog("/data/ui/msgwarm.ui");
    }

    public final boolean isNotShowingWarning() {
        return !this.dialogManager.isTopDialog("/data/ui/msgwarm.ui");
    }

    public final void setWarningText(String var1, String var2) {
        this.dialogManager.currentDialog.getChildById(6).getComponentData().text = var2;
        this.dialogManager.currentDialog.getChildById(7).getComponentData().text = var1;
    }

    public final void showTipDialog() {
        this.dialogManager.showDialog("/data/ui/msgtip.ui", 257, this);
    }

    public final void hideTipDialog() {
        this.dialogManager.removeDialog("/data/ui/msgtip.ui");
    }

    private boolean isNotShowingTipDialog() {
        return !this.dialogManager.isTopDialog("/data/ui/msgtip.ui");
    }

    public final void setTipText(String var1) {
        this.dialogManager.currentDialog.getChildById(2).getComponentData().text = var1;
    }

    public final void hideConfirmButtons() {
        this.dialogManager.currentDialog.getChildById(3).setVisible(false);
        this.dialogManager.currentDialog.getChildById(4).setVisible(false);
    }

    public final void updateSaveDialog() {
        if (this.dialogState == 0) {
            if (this.gameEngine.isKeyPressed(196640)) {
                this.dialogState = 1;
                this.setTipText("Đang lưu...");
                this.hideConfirmButtons();
                return;
            }

            if (this.gameEngine.isKeyPressed(262144)) {
                if (GameEngineBase.paymentActive) {
                    this.menuIndex = 5;
                } else {
                    this.menuIndex = 4;
                }

                this.gameEngine.changeState((byte) 6);
                this.dialogManager.removeDialog("/data/ui/msgtip.ui");
                this.dialogState = 0;
                return;
            }
        } else if (this.dialogState == 1) {
            if (((WorldGameSession) this.gameEngine).saveAllGameData()) {
                this.setTipText("Lưu thành công");
                this.dialogState = 2;
                return;
            }
        } else if (this.dialogState == 2) {
            this.dialogManager.removeDialog("/data/ui/msgtip.ui");
            this.dialogManager.removeDialog("/data/ui/gamemenu.ui");
            this.gameEngine.changeState((byte) 0);
            this.dialogState = 0;
        }

    }

    private void showConfirmMessage(String var1, String var2) {
        this.dialogManager.currentDialog.getChildById(2).getComponentData().text = var2;
        this.dialogManager.currentDialog.getChildById(4).getComponentData().text = var1;
    }

    public final void showNPCShopMenu() {
        this.dialogManager.showDialog("/data/ui/shopbuy.ui", 257, this);
        this.menuIndex = 0;
        this.dialogState = 0;
        this.dialogManager.currentDialog.getChildById(5).getComponentData().text = "Bán ra";
        this.dialogManager.currentDialog.getChildById(39).getComponentData().text = "";
        this.dialogManager.currentDialog.getChildById(40).getComponentData().text = "";
        this.dialogManager.currentDialog.getChildById(57).getComponentData().text = "Bán đi";
        this.dialogManager.currentDialog.getChildById(58).getComponentData().text = "Quay lại";
        this.player.refreshUsableItems();
        this.updateShopItemDisplay();
    }

    private void updateShopItemDisplay() {
        if (this.player.usableItems.size() > 5) {
            ((UIContainerComponent) this.dialogManager.currentDialog.getChildById(0)).primaryListComponent.setDisplayMode(1);
        } else {
            ((UIContainerComponent) this.dialogManager.currentDialog.getChildById(0)).primaryListComponent.setDisplayMode(0);
        }

        ((UIContainerComponent) this.dialogManager.currentDialog
                .getChildById(0)).primaryListComponent.totalItemCount = this.player.usableItems.size();
        this.listScrollOffset = ((UIContainerComponent) this.dialogManager.currentDialog
                .getChildById(0)).primaryListComponent.scrollOffset;
        this.listSelectedIndex = ((UIContainerComponent) this.dialogManager.currentDialog
                .getChildById(0)).primaryListComponent.selectedIndex;
        if (this.listSelectedIndex >= this.player.usableItems.size()) {
            this.listSelectedIndex = this.player.usableItems.size() - 1;
            ((UIContainerComponent) this.dialogManager.currentDialog
                    .getChildById(0)).primaryListComponent.selectedIndex = this.listSelectedIndex;
        }

        if (this.listScrollOffset > 0 && this.listSelectedIndex - this.listScrollOffset < 4) {
            --this.listScrollOffset;
            ((UIContainerComponent) this.dialogManager.currentDialog
                    .getChildById(0)).primaryListComponent.scrollOffset = this.listScrollOffset;
        }

        for (int var1 = 0; var1 < 5; ++var1) {
            if (this.listScrollOffset + var1 < this.player.usableItems.size()) {
                int var2 = ((int[]) this.player.usableItems.elementAt(this.listScrollOffset + var1))[0];
                if (this.dialogManager.currentDialog.getChildById(var1 + 51).getComponentData().renderer == null) {
                    this.dialogManager.currentDialog.getChildById(var1 + 51).getComponentData().renderer = new SpriteRenderer();
                    this.dialogManager.currentDialog.getChildById(var1 + 51).getComponentData().renderer.setSpriteIndex((int) 0);
                    this.dialogManager.currentDialog.getChildById(var1 + 51).getComponentData().renderer.spriteType = 2;
                    this.dialogManager.currentDialog.getChildById(var1 + 51).getComponentData().renderer.initializeSprite(258,
                            false, (byte) -1);
                }

                this.dialogManager.currentDialog.getChildById(var1 + 51).getComponentData().renderer
                        .setSpriteIndex((int) ResourceManager.gameDatabase[4][var2][1]);
                this.dialogManager.currentDialog.getChildById(14 + var1 * 5).getComponentData().text = GameEngineBase
                        .getLocalizedText((int) ResourceManager.gameDatabase[4][var2][0]);
                this.dialogManager.currentDialog.getChildById(15 + var1 * 5).getComponentData().text = ""
                        + ResourceManager.gameDatabase[4][var2][3] / 2;
                if (ResourceManager.gameDatabase[4][var2][4] == 0) {
                    this.dialogManager.currentDialog.getChildById(var1 + 45).getComponentData().renderer.setSpriteIndex((int) 84);
                } else if (ResourceManager.gameDatabase[4][var2][4] == 1) {
                    this.dialogManager.currentDialog.getChildById(var1 + 45).getComponentData().renderer.setSpriteIndex((int) 83);
                } else if (ResourceManager.gameDatabase[4][var2][4] == 2) {
                    this.dialogManager.currentDialog.getChildById(var1 + 45).getComponentData().renderer.setSpriteIndex((int) 74);
                }
            } else {
                if (this.dialogManager.currentDialog.getChildById(var1 + 51).getComponentData().renderer != null) {
                    this.dialogManager.currentDialog.getChildById(var1 + 51).getComponentData().renderer.cleanup();
                }

                this.dialogManager.currentDialog.getChildById(14 + var1 * 5).getComponentData().text = "";
                this.dialogManager.currentDialog.getChildById(15 + var1 * 5).getComponentData().text = "";
                this.dialogManager.currentDialog.getChildById(var1 + 45).getComponentData().renderer.setSpriteIndex((int) 86);
            }
        }

        if (!this.player.usableItems.isEmpty()) {
            this.dialogManager.currentDialog.getChildById(56).getComponentData().text = GameEngineBase.getLocalizedText(
                    ResourceManager.gameDatabase[4][((int[]) this.player.usableItems.elementAt(this.listSelectedIndex))[0]][2]);
        } else {
            this.dialogManager.currentDialog.getChildById(56).getComponentData().text = "";
        }

        if (!this.player.usableItems.isEmpty()) {
            this.dialogManager.currentDialog.getChildById(43).getComponentData().text = "" + this.player.getBadges();
            this.dialogManager.currentDialog.getChildById(44).getComponentData().text = "" + this.player.getGold();
            this.dialogManager.currentDialog.getChildById(38).setOffsetY(102 + this.listSelectedIndex * 84 / this.player.usableItems.size(),
                    this.dialogManager.currentDialog.getUIContainerComponent());
        }
    }

    public final void updateNPCShopMenu() {
        if (this.gameEngine.isKeyPressed(4100)) {
            this.dialogManager.currentDialog.handleAction(0);
        } else if (this.gameEngine.isKeyPressed(8448)) {
            this.dialogManager.currentDialog.handleAction(1);
        } else {
            int[] var1;
            if (this.dialogState == 1 && this.gameEngine.isKeyPressed(16400) && this.subMenuIndex > 0) {
                var1 = (int[]) this.player.usableItems.elementAt(this.listSelectedIndex);
                --this.subMenuIndex;
                if (this.subMenuIndex <= 0) {
                    this.subMenuIndex = this.player.getItemCount((int) var1[0], (byte) 0);
                }

                this.updateBuyQuantityDisplay(this.subMenuIndex, this.subMenuIndex * ResourceManager.gameDatabase[4][var1[0]][3] / 2,
                        ResourceManager.gameDatabase[4][var1[0]][4], 4);
            } else if (this.dialogState == 1 && this.gameEngine.isKeyPressed(32832)) {
                var1 = (int[]) this.player.usableItems.elementAt(this.listSelectedIndex);
                ++this.subMenuIndex;
                if (this.subMenuIndex > this.player.getItemCount(var1[0], (byte) 0)) {
                    this.subMenuIndex = 1;
                }

                this.updateBuyQuantityDisplay(this.subMenuIndex, this.subMenuIndex * ResourceManager.gameDatabase[4][var1[0]][3] / 2,
                        ResourceManager.gameDatabase[4][var1[0]][4], 4);
            } else {
                if (this.gameEngine.isKeyPressed(196640) && !this.player.usableItems.isEmpty()) {
                    var1 = (int[]) this.player.usableItems.elementAt(this.listSelectedIndex);
                    if (this.dialogState == 0) {
                        this.dialogState = 1;
                        this.dialogManager.showDialog("/data/ui/msgyn.ui", 257, this);
                        this.subMenuIndex = 1;
                        this.optionIndex = 0;
                        this.updateBuyQuantityDisplay(this.subMenuIndex, this.subMenuIndex * ResourceManager.gameDatabase[4][var1[0]][3] / 2,
                                ResourceManager.gameDatabase[4][var1[0]][4], 4);
                        return;
                    }

                    if (this.optionIndex != 0) {
                        this.dialogManager.removeDialog("/data/ui/msgyn.ui");
                        this.dialogState = 0;
                        return;
                    }

                    this.player.removeItem(var1[0], this.subMenuIndex, (byte) 0);
                    this.player.addGold(this.subMenuIndex * ResourceManager.gameDatabase[4][var1[0]][3] / 2);
                } else {
                    if (!this.gameEngine.isKeyPressed(262144)) {
                        return;
                    }

                    if (this.dialogState == 0) {
                        this.gameEngine.changeState((byte) 1);
                        this.dialogManager.removeDialog("/data/ui/shopbuy.ui");
                        this.menuIndex = 1;
                        ((UIContainerComponent) this.dialogManager.currentDialog
                                .getChildById(0)).primaryListComponent.selectedIndex = this.menuIndex;
                        return;
                    }
                }

                this.dialogState = 0;
                this.dialogManager.removeDialog("/data/ui/msgyn.ui");
                this.player.refreshUsableItems();
                this.updateShopItemDisplay();
            }
        }
    }

    public final void showQuestLog() {
        this.dialogManager.showDialog("/data/ui/record.ui", 257, this);
        this.dialogManager.removeDialog("/data/ui/gamemenu.ui");
        this.dialogManager.currentDialog.getChildById(14).getComponentData().text = ""
                + (this.player.partySize + this.player.pokemonStorage.size());
        this.dialogManager.currentDialog.getChildById(17).getComponentData().text = "" + this.player.totalCreaturesSeen;
        this.dialogManager.currentDialog.getChildById(20).getComponentData().text = "" + this.player.commonCreaturesSeen;
        this.dialogManager.currentDialog.getChildById(26).getComponentData().text = "" + this.player.rareCreaturesSeen;
        int var1 = 0;

        for (byte var2 = 0; var2 < this.player.badgeStates.length; ++var2) {
            if (this.player.getBadgeState(var2, (byte) 0) == 2) {
                ++var1;
            }
        }

        this.dialogManager.currentDialog.getChildById(29).getComponentData().text = "" + var1;
        long var4 = GameScreenManager.getInstance().storyStartTime + GameScreenManager.getInstance().worldMapTime
                - GameScreenManager.getInstance().currentTime;
        ComponentData var10000 = this.dialogManager.currentDialog.getChildById(31).getComponentData();
        WorldGameSession.getInstance();
        var10000.text = WorldGameSession.formatTimeString(var4)[1];
        ((UIContainerComponent) this.dialogManager.currentDialog
                .getChildById(0)).secondaryListComponent.selectedIndex = this.subMenuIndex;
        this.menuIndex = 0;
        this.dialogState = 0;
        this.needsRedraw = true;
    }

    public final void updateQuestLog() {
        if (this.gameEngine.isKeyPressed(16400)) {
            this.dialogManager.currentDialog.handleAction(2);
            this.needsRedraw = true;
        } else if (this.gameEngine.isKeyPressed(32832)) {
            this.dialogManager.currentDialog.handleAction(3);
            this.needsRedraw = true;
        } else if (this.gameEngine.isKeyPressed(196640)) {
            if (this.dialogState == 0) {
                switch (this.subMenuIndex) {
                    case 0:
                        if (PlayerCharacter.getInstance().isSkillActive(5)) {
                            this.gameEngine.changeState((byte) 11);
                        } else {
                            this.showWarningDialog();
                            this.setWarningText("Không đạt được sủng vật sách tranh đạo cụ", "Nhấn nút 5 tiếp tục");
                            this.dialogState = 1;
                        }
                        break;
                    case 1:
                        this.gameEngine.changeState((byte) 12);
                }
            } else {
                this.dialogState = 0;
                this.hideWarningDialog();
            }
        } else if (this.gameEngine.isKeyPressed(262144) && this.dialogState == 0) {
            if (GameEngineBase.paymentActive) {
                this.menuIndex = 3;
            } else {
                this.menuIndex = 2;
            }

            this.gameEngine.changeState((byte) 6);
            this.dialogManager.removeDialog("/data/ui/record.ui");
        }

        this.needsRedraw = true;
    }

    public final void showPokedex() {
        this.dialogManager.showDialog("/data/ui/petmap.ui", 257, this);
        this.dialogManager.removeDialog("/data/ui/record.ui");
        this.menuIndex = 0;
        this.subMenuIndex = 0;
        this.dialogState = 0;
        ((UIContainerComponent) this.dialogManager.currentDialog.getChildById(0)).primaryListComponent.setDisplayMode(1);
        this.bb();
        this.needsRedraw = true;
    }

    private void ba() {
        ((UIContainerComponent) this.dialogManager.currentDialog.getChildById(0)).primaryListComponent.scrollOffset = 0;
        ((UIContainerComponent) this.dialogManager.currentDialog.getChildById(0)).primaryListComponent.selectedIndex = 0;
    }

    private void bb() {
        ((UIContainerComponent) this.dialogManager.currentDialog
                .getChildById(0)).primaryListComponent.totalItemCount = this.player.categorySizes[this.menuIndex];
        this.listScrollOffset = ((UIContainerComponent) this.dialogManager.currentDialog
                .getChildById(0)).primaryListComponent.scrollOffset;
        this.listSelectedIndex = ((UIContainerComponent) this.dialogManager.currentDialog
                .getChildById(0)).primaryListComponent.selectedIndex;
        short var1 = ResourceManager.gameDatabase[0][this.player.categoryOffsets[this.menuIndex] + this.listSelectedIndex][17];
        if (this.player.getCreatureStatus((byte) this.menuIndex, this.listSelectedIndex + this.player.categoryOffsets[this.menuIndex]) > 0) {
            this.dialogManager.currentDialog.getChildById(21).setVisible(true);
            if (this.dialogManager.currentDialog.getChildById(21).getComponentData().renderer != null) {
                this.dialogManager.currentDialog.getChildById(21).getComponentData().renderer.cleanup();
            } else {
                this.dialogManager.currentDialog.getChildById(21).getComponentData().renderer = new SpriteRenderer();
                this.dialogManager.currentDialog.getChildById(21).getComponentData().renderer.setSpriteIndex((int) 0);
                this.dialogManager.currentDialog.getChildById(21).getComponentData().renderer.spriteType = 3;
            }

            this.dialogManager.currentDialog.getChildById(21).getComponentData().renderer.initializeSprite(var1, false,
                    (byte) -1);
            this.dialogManager.currentDialog.getChildById(21).getComponentData().renderer.setSpriteState((byte) 1);
        } else {
            this.dialogManager.currentDialog.getChildById(21).setVisible(false);
        }

        int var3 = 0;

        int var2;
        for (var2 = 0; var2 < this.player.categorySizes[this.menuIndex]; ++var2) {
            if (this.player.getCreatureStatus((byte) this.menuIndex, this.player.categoryOffsets[this.menuIndex] + var2) == 2) {
                ++var3;
            }
        }

        for (var2 = 0; var2 < 5; ++var2) {
            if (this.dialogManager.currentDialog.getChildById(var2 + 44).getComponentData().renderer == null) {
                this.dialogManager.currentDialog.getChildById(var2 + 44).getComponentData().renderer = new SpriteRenderer();
                this.dialogManager.currentDialog.getChildById(var2 + 44).getComponentData().renderer.setSpriteIndex((int) 102);
                this.dialogManager.currentDialog.getChildById(var2 + 44).getComponentData().renderer.spriteType = 2;
                this.dialogManager.currentDialog.getChildById(var2 + 44).getComponentData().renderer.initializeSprite(257, false,
                        (byte) -1);
            }

            if (this.player.getCreatureStatus((byte) this.menuIndex, var2 + this.listScrollOffset + this.player.categoryOffsets[this.menuIndex]) == 2) {
                this.dialogManager.currentDialog.getChildById(var2 + 44).getComponentData().renderer.setSpriteIndex((int) 101);
            } else {
                this.dialogManager.currentDialog.getChildById(var2 + 44).getComponentData().renderer.setSpriteIndex((int) 102);
            }

            this.dialogManager.currentDialog.getChildById(24 + (var2 << 2) + 3).getComponentData().text = GameEngineBase
                    .getLocalizedText((int) ResourceManager.gameDatabase[0][this.player.categoryOffsets[this.menuIndex] + var2
                            + this.listScrollOffset][0]);
        }

        this.dialogManager.currentDialog.getChildById(20).getComponentData().text = GameEngineBase
                .getLocalizedText(365 + this.menuIndex) + var3 + "/" + this.player.categorySizes[this.menuIndex];
        this.dialogManager.currentDialog.getChildById(23).setOffsetY(
                99 + (this.listSelectedIndex << 6) / this.player.categorySizes[this.menuIndex],
                this.dialogManager.currentDialog.getUIContainerComponent());
    }

    public final void updatePokedex() {
        if (this.gameEngine.isKeyPressed(4100)) {
            this.dialogManager.currentDialog.handleAction(0);
            this.bb();
        } else if (this.gameEngine.isKeyPressed(8448)) {
            this.dialogManager.currentDialog.handleAction(1);
            this.bb();
        } else if (this.gameEngine.isKeyPressed(16400)) {
            this.dialogManager.currentDialog.handleAction(2);
            this.ba();
            this.bb();
        } else if (this.gameEngine.isKeyPressed(32832)) {
            this.dialogManager.currentDialog.handleAction(3);
            this.ba();
            this.bb();
        } else if (!this.gameEngine.isKeyPressed(196640) && this.gameEngine.isKeyPressed(786432)) {
            if (this.gameEngine.previousState == 8) {
                this.gameEngine.changeState((byte) 8);
            } else {
                this.subMenuIndex = 0;
                this.gameEngine.changeState((byte) 9);
            }

            this.dialogManager.removeDialog("/data/ui/petmap.ui");
        }

        this.needsRedraw = true;
    }

    public final void showQuestDetails() {
        this.dialogManager.showDialog("/data/ui/task.ui", 257, this);
        this.dialogManager.removeDialog("/data/ui/gamemenu.ui");
        ((UIContainerComponent) this.dialogManager.currentDialog
                .getChildById(0)).secondaryListComponent.selectedIndex = this.menuIndex;
        this.subMenuIndex = 0;
        this.optionIndex = 0;
        this.bc();
        this.bd();
    }

    private void bc() {
        int var1;
        int var2;
        switch (this.menuIndex) {
            case 0:
                if (QuestManager.questDialogState >= QuestManager.questDescriptions.length / 2 - 1) {
                    int var10001 = QuestManager.questDescriptions.length;
                    ((UIContainerComponent) this.dialogManager.currentDialog
                            .getChildById(0)).primaryListComponent.totalItemCount = var10001 / 2;
                    ((UIContainerComponent) this.dialogManager.currentDialog
                            .getChildById(0)).primaryListComponent.selectedIndex = QuestManager.questDescriptions.length / 2
                            - 1;
                } else {
                    ((UIContainerComponent) this.dialogManager.currentDialog
                            .getChildById(0)).primaryListComponent.totalItemCount = QuestManager.questDialogState + 1;
                    ((UIContainerComponent) this.dialogManager.currentDialog
                            .getChildById(0)).primaryListComponent.selectedIndex = QuestManager.questDialogState;
                }

                this.dialogManager.currentDialog.getChildById(36).getComponentData().text = "";
                this.listSelectedIndex = QuestManager.questDialogState;
                this.listScrollOffset = QuestManager.questDialogState - 4;
                if (this.listSelectedIndex <= 0) {
                    this.listSelectedIndex = 0;
                }

                if (this.listScrollOffset <= 0) {
                    this.listScrollOffset = 0;
                }

                ((UIContainerComponent) this.dialogManager.currentDialog
                        .getChildById(0)).primaryListComponent.scrollOffset = this.listScrollOffset;
                this.dialogManager.currentDialog.getChildById(37).getComponentData().text = "Đầu mối chính hoàn thành độ: ";
                if (QuestManager.questDialogState >= QuestManager.questDescriptions.length / 2) {
                    this.dialogManager.currentDialog.getChildById(38)
                            .getComponentData().text = QuestManager.questDescriptions[QuestManager.questDescriptions.length
                            - 1];
                } else {
                    this.dialogManager.currentDialog.getChildById(38)
                            .getComponentData().text = QuestManager.questDescriptions[QuestManager.questDescriptions.length / 2
                            + QuestManager.questDialogState];
                }

                var1 = QuestManager.questDialogState * 1000 / (QuestManager.questDescriptions.length / 2);
                if (!GameEngineBase.paymentActive) {
                    if ((var2 = var1 % 10) == 0) {
                        var2 = 1;
                    }

                    this.dialogManager.currentDialog.getChildById(38).getComponentData().text = var1 / 50 + "." + var2 + "%";
                } else {
                    this.dialogManager.currentDialog.getChildById(38).getComponentData().text = var1 / 10 + "." + var1 % 10 + "%";
                }

                if (QuestManager.questDialogState > 4) {
                    ((UIContainerComponent) this.dialogManager.currentDialog.getChildById(0)).primaryListComponent
                            .setDisplayMode(1);
                } else {
                    ((UIContainerComponent) this.dialogManager.currentDialog.getChildById(0)).primaryListComponent
                            .setDisplayMode(0);
                }

                this.dialogManager.currentDialog.getChildById(8).getComponentData().focusedTextColor = 11290624;
                break;
            case 1:
                ((UIContainerComponent) this.dialogManager.currentDialog
                        .getChildById(0)).primaryListComponent.totalItemCount = QuestManager.questFlagCount;
                ((UIContainerComponent) this.dialogManager.currentDialog.getChildById(0)).primaryListComponent.selectedIndex = 0;
                ((UIContainerComponent) this.dialogManager.currentDialog.getChildById(0)).primaryListComponent.scrollOffset = 0;
                this.dialogManager.currentDialog.getChildById(36).getComponentData().text = "";
                this.dialogManager.currentDialog.getChildById(37).getComponentData().text = "Chi nhánh hoàn thành độ: ";
                var1 = 0;

                for (var2 = 0; var2 < QuestManager.questFlags.length; ++var2) {
                    if (QuestManager.questFlags[var2][1] == 3) {
                        ++var1;
                    }
                }

                var2 = var1 * 1000 / (QuestManager.questNames.length / 2);
                this.dialogManager.currentDialog.getChildById(38).getComponentData().text = var2 / 10 + "." + var2 % 10 + "%";
                if (QuestManager.questFlagCount > 5) {
                    ((UIContainerComponent) this.dialogManager.currentDialog.getChildById(0)).primaryListComponent
                            .setDisplayMode(1);
                } else {
                    ((UIContainerComponent) this.dialogManager.currentDialog.getChildById(0)).primaryListComponent
                            .setDisplayMode(0);
                }

                this.dialogManager.currentDialog.getChildById(9).getComponentData().focusedTextColor = 11290624;
        }

        this.bd();
    }

    private void bd() {
        this.listScrollOffset = ((UIContainerComponent) this.dialogManager.currentDialog
                .getChildById(0)).primaryListComponent.scrollOffset;
        this.listSelectedIndex = ((UIContainerComponent) this.dialogManager.currentDialog
                .getChildById(0)).primaryListComponent.selectedIndex;

        for (int var1 = 0; var1 < 5; ++var1) {
            if (this.menuIndex == 0) {
                if (QuestManager.questDialogState > 0) {
                    if (this.listScrollOffset + var1 < QuestManager.questDialogState) {
                        this.dialogManager.currentDialog.getChildById(10 + var1 * 5 + 2).getComponentData().text = ""
                                + (var1 + this.listScrollOffset + 1);
                        this.dialogManager.currentDialog.getChildById(10 + var1 * 5 + 3)
                                .getComponentData().text = QuestManager.questDescriptions[this.listScrollOffset + var1];
                        this.dialogManager.currentDialog.getChildById(10 + var1 * 5 + 4).getComponentData().text = "Hoàn thành";
                    } else if (this.listScrollOffset + var1 == QuestManager.questDialogState
                            && this.listScrollOffset + var1 <= QuestManager.questDescriptions.length / 2 - 1) {
                        this.dialogManager.currentDialog.getChildById(10 + var1 * 5 + 2).getComponentData().text = ""
                                + (var1 + this.listScrollOffset + 1);
                        this.dialogManager.currentDialog.getChildById(10 + var1 * 5 + 3)
                                .getComponentData().text = QuestManager.questDescriptions[this.listScrollOffset + var1];
                        this.dialogManager.currentDialog.getChildById(10 + var1 * 5 + 4).getComponentData().text = "";
                    } else {
                        this.dialogManager.currentDialog.getChildById(10 + var1 * 5 + 2).getComponentData().text = "";
                        this.dialogManager.currentDialog.getChildById(10 + var1 * 5 + 3).getComponentData().text = "";
                        this.dialogManager.currentDialog.getChildById(10 + var1 * 5 + 4).getComponentData().text = "";
                        this.dialogManager.currentDialog.getChildById(36).getComponentData().text = "";
                    }
                } else {
                    this.dialogManager.currentDialog.getChildById(12).getComponentData().text = "1";
                    this.dialogManager.currentDialog.getChildById(13).getComponentData().text = QuestManager.questDescriptions[0];
                    this.dialogManager.currentDialog.getChildById(14).getComponentData().text = "";
                }
            } else if (this.menuIndex == 1) {
                if (this.listScrollOffset + var1 < QuestManager.questFlagCount) {
                    this.dialogManager.currentDialog.getChildById(10 + var1 * 5 + 2).getComponentData().text = ""
                            + (var1 + this.listScrollOffset + 1);
                    this.dialogManager.currentDialog.getChildById(10 + var1 * 5 + 3)
                            .getComponentData().text = QuestManager.questNames[QuestManager.questFlags[this.listScrollOffset
                            + var1][0]];
                    if (QuestManager.questFlags[this.listScrollOffset + var1][1] == 3) {
                        this.dialogManager.currentDialog.getChildById(10 + var1 * 5 + 4).getComponentData().text = "Hoàn thành";
                    } else {
                        this.dialogManager.currentDialog.getChildById(10 + var1 * 5 + 4).getComponentData().text = "";
                    }
                } else {
                    this.dialogManager.currentDialog.getChildById(10 + var1 * 5 + 2).getComponentData().text = "";
                    this.dialogManager.currentDialog.getChildById(10 + var1 * 5 + 3).getComponentData().text = "";
                    this.dialogManager.currentDialog.getChildById(10 + var1 * 5 + 4).getComponentData().text = "";
                }
            }
        }

        switch (this.menuIndex) {
            case 0:
                this.dialogManager.currentDialog.getChildById(36)
                        .getComponentData().text = QuestManager.questDescriptions[QuestManager.questDescriptions.length / 2
                        + this.listSelectedIndex];
                break;
            case 1:
                if (QuestManager.questFlagCount > 0) {
                    this.dialogManager.currentDialog.getChildById(36)
                            .getComponentData().text = QuestManager.questNames[QuestManager.questNames.length / 2
                            + QuestManager.questFlags[this.listSelectedIndex][0]];
                }
        }

        if (((UIContainerComponent) this.dialogManager.currentDialog.getChildById(0)).primaryListComponent.totalItemCount > 0) {
            this.dialogManager.currentDialog.getChildById(40).setOffsetY(
                    104 + (this.listSelectedIndex << 6) / ((UIContainerComponent) this.dialogManager.currentDialog
                            .getChildById(0)).primaryListComponent.totalItemCount,
                    this.dialogManager.currentDialog.getUIContainerComponent());
        }

    }

    public final void updateQuestDetails() {
        if (this.gameEngine.isKeyPressed(4100)) {
            this.dialogManager.currentDialog.handleAction(0);
            this.bd();
        } else if (this.gameEngine.isKeyPressed(8448)) {
            this.dialogManager.currentDialog.handleAction(1);
            this.bd();
        } else if (this.gameEngine.isKeyPressed(16400)) {
            this.dialogManager.currentDialog.handleAction(2);
            this.bc();
        } else if (this.gameEngine.isKeyPressed(32832)) {
            this.dialogManager.currentDialog.handleAction(3);
            this.bc();
        } else if (this.gameEngine.isKeyPressed(983072)) {
            if (GameEngineBase.paymentActive) {
                this.menuIndex = 4;
            } else {
                this.menuIndex = 3;
            }

            this.dialogManager.removeDialog("/data/ui/task.ui");
            if (this.gameEngine.previousState == 0) {
                this.menuIndex = 0;
                this.gameEngine.changeState((byte) 0);
            } else if (this.gameEngine.previousState == 33) {
                this.gameEngine.changeState((byte) 33);
            } else {
                this.gameEngine.changeState((byte) 6);
            }
        } else {
            if (this.gameEngine.isKeyPressed(10)) {
                this.dialogManager.removeDialog("/data/ui/task.ui");
                this.gameEngine.changeState((byte) 0);
            }

        }
    }

    public final void showGuideMenu() {
        this.dialogManager.showDialog("/data/ui/badge.ui", 257, this);
        this.dialogManager.removeDialog("/data/ui/record.ui");
        this.menuIndex = 0;
        this.dialogState = 0;

        for (int var1 = 0; var1 < 8; ++var1) {
            if (this.player.badgeStates[var1][0] != 0) {
                this.dialogManager.currentDialog.getChildById(var1 + 25).getComponentData().renderer.setSpriteIndex(var1 + 46);
            }
        }

        this.be();
    }

    private void be() {
        this.dialogManager.currentDialog.getChildById(13).getComponentData().text = GameEngineBase
                .getLocalizedText((int) ResourceManager.gameDatabase[2][this.menuIndex][0]);
        this.dialogManager.currentDialog.getChildById(14).getComponentData().text = GameEngineBase.getLocalizedText(
                (int) ResourceManager.gameDatabase[2][this.menuIndex][2 + this.player.getBadgeState((byte) this.menuIndex, (byte) 1)]);
        if (this.player.getBadgeState((byte) this.menuIndex, (byte) 0) == 0) {
            this.dialogManager.currentDialog.getChildById(16).getComponentData().text = "Chưa đạt";
        } else {
            this.dialogManager.currentDialog.getChildById(16).getComponentData().text = "Đã đạt được";
            this.player.getBadgeState((byte) this.menuIndex, (byte) 1);
            this.dialogManager.currentDialog.getChildById(33).getComponentData().text = "";
        }
    }

    public final void updateGuideMenu() {
        if (this.gameEngine.isKeyPressed(4100)) {
            this.dialogManager.currentDialog.handleAction(0);
            this.be();
        } else if (this.gameEngine.isKeyPressed(8448)) {
            this.dialogManager.currentDialog.handleAction(1);
            this.be();
        } else if (this.gameEngine.isKeyPressed(16400)) {
            this.dialogManager.currentDialog.handleAction(2);
            this.be();
        } else if (this.gameEngine.isKeyPressed(32832)) {
            this.dialogManager.currentDialog.handleAction(3);
            this.be();
        } else {
            if (this.gameEngine.isKeyPressed(786432)) {
                if (this.gameEngine.previousState == 8) {
                    this.gameEngine.changeState((byte) 8);
                } else {
                    this.subMenuIndex = 1;
                    this.gameEngine.changeState((byte) 9);
                }

                this.dialogManager.removeDialog("/data/ui/badge.ui");
            }

        }
    }

    public final void showSmsRewardDialog(int var1) {
        this.dialogManager.showDialog("/data/ui/smsTip.ui", 257, this);
        if (this.dialogManager.currentDialog.getChildById(6).getComponentData().renderer == null) {
            this.dialogManager.currentDialog.getChildById(6).getComponentData().renderer = new SpriteRenderer();
            this.dialogManager.currentDialog.getChildById(6).getComponentData().renderer.spriteType = 2;
            this.dialogManager.currentDialog.getChildById(6).getComponentData().renderer.setSpriteIndex((int) -1);
            this.dialogManager.currentDialog.getChildById(6).getComponentData().renderer.initializeSprite(257, false, (byte) -1);
            this.dialogManager.currentDialog.getChildById(6).getComponentData().renderer.setSpriteIndex(var1 + 46);
        }

        this.dialogManager.currentDialog.getChildById(7).getComponentData().text = GameEngineBase.getLocalizedText(var1 + 187)
                + ":" + GameEngineBase.getLocalizedText(var1 + 195);
        this.dialogManager.currentDialog.getChildById(8).getComponentData().text = GameEngineBase.getLocalizedText((int) 377);
    }

    public final void hideSmsRewardDialog() {
        this.dialogManager.removeDialog("/data/ui/smsTip.ui");
    }

    public final void showPokemonPartyMenu() {
        this.menuIndex = 0;
        this.showPokemonPartyDetail(this.subMenuIndex);
    }

    private void showPokemonPartyDetail(int var1) {
        PokemonEntity[] var2 = this.player.partyPokemon;
        GameUIController var5 = this;
        this.dialogManager.showDialog("/data/ui/petstate.ui", 257, this);
        this.updatePokemonDetailDisplay(var1);
        this.dialogState = 0;
        int var4;
        if (this.gameEngine instanceof WorldGameSession) {
            for (var4 = 0; var4 < 6; ++var4) {
                if (var2[var4] != null) {
                    var5.dialogManager.currentDialog.getChildById(16 + var4 * 6).getComponentData().text = "#P" + var2[var4].getHpPercent();
                    var5.dialogManager.currentDialog.getChildById(17 + var4 * 6).getComponentData().text = "#P" + var2[var4].getExpPercent();
                } else {
                    var5.dialogManager.currentDialog.getChildById(16 + var4 * 6).getComponentData().text = "#P0";
                    var5.dialogManager.currentDialog.getChildById(17 + var4 * 6).getComponentData().text = "#P0";
                }
            }

            if (var5.gameEngine.previousState == 16) {
                var5.dialogManager.currentDialog.getChildById(64).getComponentData().text = "Gởi lại";
            }

            var5.dialogManager.currentDialog.getChildById(75).setVisible(false);
            var5.dialogManager.currentDialog.getChildById(76).setVisible(false);
        } else if (this.gameEngine instanceof BattleSystemManager) {
            for (var4 = 0; var4 < 6; ++var4) {
                if (var4 < ((BattleSystemManager) var5.gameEngine).C29_f405.length
                        && var2[((BattleSystemManager) var5.gameEngine).C29_f405[var4]] != null) {
                    var5.dialogManager.currentDialog.getChildById(16 + var4 * 6).getComponentData().text = "#P"
                            + var2[((BattleSystemManager) var5.gameEngine).C29_f405[var4]].getHpPercent();
                    var5.dialogManager.currentDialog.getChildById(17 + var4 * 6).getComponentData().text = "#P"
                            + var2[((BattleSystemManager) var5.gameEngine).C29_f405[var4]].getExpPercent();
                } else {
                    var5.dialogManager.currentDialog.getChildById(16 + var4 * 6).getComponentData().text = "#P0";
                    var5.dialogManager.currentDialog.getChildById(17 + var4 * 6).getComponentData().text = "#P0";
                }
            }

            var5.dialogManager.currentDialog.getChildById(63).setVisible(false);
            var5.dialogManager.currentDialog.getChildById(64).setVisible(false);
            if (var5.gameEngine.previousState == 4) {
                var5.dialogManager.currentDialog.getChildById(75).getComponentData().text = "Sử dụng";
            } else if (var5.gameEngine.currentState == 5) {
                var5.dialogManager.currentDialog.getChildById(75).getComponentData().text = "Xuất chiến";
            }
        }

        ((UIContainerComponent) var5.dialogManager.currentDialog
                .getChildById(0)).primaryListComponent.totalItemCount = var5.player.partySize;
        ((UIContainerComponent) var5.dialogManager.currentDialog
                .getChildById(0)).primaryListComponent.visibleItemCount = var5.player.partySize;
        ((UIContainerComponent) var5.dialogManager.currentDialog.getChildById(0)).primaryListComponent.selectedIndex = var1;
        var5.needsRedraw = true;
    }

    private void a(PokemonEntity[] var1, int var2) {
        if (var1[var2] != null) {
            if (this.dialogManager.currentDialog.getChildById(48).getComponentData().renderer != null) {
                this.dialogManager.currentDialog.getChildById(48).getComponentData().renderer.cleanup();
            } else {
                this.dialogManager.currentDialog.getChildById(48).getComponentData().renderer = new SpriteRenderer();
                this.dialogManager.currentDialog.getChildById(48).getComponentData().renderer.setSpriteIndex((int) 0);
                this.dialogManager.currentDialog.getChildById(48).getComponentData().renderer.spriteType = 3;
            }

            short var10001 = var1[var2].spriteId;
            this.dialogManager.currentDialog.getChildById(48).getComponentData().renderer.initializeSprite(var10001, false,
                    (byte) -1);
            this.dialogManager.currentDialog.getChildById(51).getComponentData().text = GameEngineBase
                    .getLocalizedText(var1[var2].getDatabaseValue((byte) 0));
            this.dialogManager.currentDialog.getChildById(52).getComponentData().text = GameEngineBase
                    .getLocalizedText(365 + var1[var2].getDatabaseValue((byte) 1));
            if (var1[var2].getDatabaseValue((byte) 19) == -1) {
                this.dialogManager.currentDialog.getChildById(62).getComponentData().text = "";
            } else if (ResourceManager.gameDatabase[0][var1[var2].getDatabaseValue((byte) 19)][2] != 1
                    && ResourceManager.gameDatabase[0][var1[var2].getDatabaseValue((byte) 19)][2] != 2) {
                if (ResourceManager.gameDatabase[0][var1[var2].getDatabaseValue((byte) 19)][2] == 3) {
                    this.dialogManager.currentDialog.getChildById(62).getComponentData().text = "Có thể dị hoá";
                }
            } else {
                this.dialogManager.currentDialog.getChildById(62).getComponentData().text = "Có thể tiến hóa";
            }

            this.dialogManager.currentDialog.getChildById(61).getComponentData().text = var1[var2].getTypeName();
            if (this.gameEngine instanceof BattleSystemManager) {
                this.dialogManager.currentDialog.getChildById(64).getComponentData().text = "Xuất chiến";
            } else if (this.gameEngine instanceof WorldGameSession) {
                this.dialogManager.currentDialog.getChildById(64).getComponentData().text = "Xác nhận";
            }

            if (this.dialogManager.currentDialog.getChildById(59).getComponentData().renderer == null) {
                this.dialogManager.currentDialog.getChildById(59).getComponentData().renderer = new SpriteRenderer();
                this.dialogManager.currentDialog.getChildById(59).getComponentData().renderer.setSpriteIndex((int) 0);
                this.dialogManager.currentDialog.getChildById(59).getComponentData().renderer.spriteType = 2;
                this.dialogManager.currentDialog.getChildById(59).getComponentData().renderer.initializeSprite(258, false,
                        (byte) -1);
            }

            PokemonEntity var10000 = var1[var2];
            byte var4 = 5;
            if (var10000.primaryStates[var4] != -1) {
                short[][] var7 = ResourceManager.gameDatabase[3];
                PokemonEntity var10002 = var1[var2];
                var4 = 5;
                short[] var8 = var7[var10002.primaryStates[var4]];
                this.dialogManager.currentDialog.getChildById(59).getComponentData().renderer.setSpriteIndex((int) var8[1]);
                ComponentData var6 = this.dialogManager.currentDialog.getChildById(60).getComponentData();
                var7 = ResourceManager.gameDatabase[3];
                var10002 = var1[var2];
                var4 = 5;
                var6.text = GameEngineBase.getLocalizedText((int) var7[var10002.primaryStates[var4]][0]);
            } else {
                this.dialogManager.currentDialog.getChildById(59).getComponentData().renderer.setSpriteIndex((int) 0);
                this.dialogManager.currentDialog.getChildById(60).getComponentData().text = "";
            }

            this.dialogManager.currentDialog.getChildById(65).getComponentData().text = "" + var1[var2].getLevel();
            this.dialogManager.currentDialog.getChildById(66).getComponentData().text = "" + var1[var2].getStatWithBuffs((byte) 2);
            this.dialogManager.currentDialog.getChildById(67).getComponentData().text = "" + var1[var2].getStatWithBuffs((byte) 3);
            this.dialogManager.currentDialog.getChildById(68).getComponentData().text = "" + var1[var2].getStatWithBuffs((byte) 4);
            var10000 = var1[var2];
            var4 = 0;
            int var3 = var10000.secondaryStates[var4];
            int var5 = ResourceManager.getDatabaseValue((byte) 0, (short) var1[var2].getSpeciesId(), (byte) 4) - 1;

            for (var2 = 0; var2 < 5; ++var2) {
                this.dialogManager.currentDialog.getChildById(74 - var2).setVisible(true);
                this.dialogManager.currentDialog.getChildById(74 - var2).getComponentData().renderer.initializeSprite(257, false,
                        (byte) -1);
                this.dialogManager.currentDialog.getChildById(74 - var2).getComponentData().renderer.spriteType = 3;
                if (var2 > var5) {
                    this.dialogManager.currentDialog.getChildById(74 - var2).setVisible(false);
                } else if (var3 > 0) {
                    this.dialogManager.currentDialog.getChildById(74 - var2).getComponentData().renderer
                            .setAnimationFrame((byte) 14, (byte) -1);
                    --var3;
                } else {
                    this.dialogManager.currentDialog.getChildById(74 - var2).getComponentData().renderer
                            .setAnimationFrame((byte) 16, (byte) -1);
                }
            }
        }

    }

    private void updatePokemonDetailDisplay(int var1) {
        if (this.gameEngine instanceof WorldGameSession) {
            this.a(this.player.partyPokemon, var1);
        } else {
            if (this.gameEngine instanceof BattleSystemManager) {
                this.a((PokemonEntity[]) this.player.partyPokemon, ((BattleSystemManager) this.gameEngine).C29_f405[var1]);
            }

        }
    }

    public final void updatePokemonPartyMenu() {
        if (this.dialogState == 0) {
            if (!GameEngineBase.isActionBlocked((int) this.menuIndex, (int) 0) && !this.isPopupActive()
                    && this.gameEngine.isKeyPressed(4100)) {
                this.dialogManager.currentDialog.handleAction(0);
            } else if (!GameEngineBase.isActionBlocked((int) this.menuIndex, (int) 0) && !this.isPopupActive()
                    && this.gameEngine.isKeyPressed(8448)) {
                this.dialogManager.currentDialog.handleAction(1);
            } else if (GameEngineBase.isConfirmAllowed() && !this.isPopupActive() && this.gameEngine.isKeyPressed(196640)) {
                if (GameEngineBase.isActionActive() && !GameEngineBase.isActionBlocked((int) this.menuIndex, (int) 0)) {
                    return;
                }

                if (this.gameEngine instanceof BattleSystemManager) {
                    int var1;
                    if ((var1 = ((BattleSystemManager) this.gameEngine).l(this.menuIndex)) == 0) {
                        this.dialogState = 2;
                        this.dialogManager.showDialog("/data/ui/msgwarm.ui", 257, this);
                        this.setWarningText("Sủng vật này không thể tham chiến", "Nhấn nút 5 tiếp tục");
                        this.dialogManager.removeDialog("/data/ui/petsetting.ui");
                    } else if (var1 == 1) {
                        this.dialogState = 2;
                        this.dialogManager.showDialog("/data/ui/msgwarm.ui", 257, this);
                        this.setWarningText("Sủng vật này đã đặt ở vị trí chiến đấu", "Nhấn nút 5 tiếp tục");
                        this.dialogManager.removeDialog("/data/ui/petsetting.ui");
                    } else if (var1 == -1) {
                        ((BattleSystemManager) this.gameEngine).e(((BattleSystemManager) this.gameEngine).C29_f407, 0);
                        this.tabIndex = 0;
                        this.gameEngine.changeState((byte) 15);
                        this.dialogManager.removeDialog("/data/ui/petsetting.ui");
                        this.dialogManager.removeDialog("/data/ui/petstate.ui");
                    }
                } else if (this.gameEngine instanceof WorldGameSession) {
                    if (this.gameEngine.previousState == 16) {
                        if (this.player.hasStorageSpace()) {
                            if (this.player.hasOtherAlivePokemon(this.menuIndex)) {
                                this.player.deactivateSkill(this.player.partyPokemon[this.menuIndex].getPrimaryState((byte) 5));
                                this.player.partyPokemon[this.menuIndex].setPrimaryState((byte) 5, (short) -1);
                                this.player.addPokemonDataToStorage(this.player.partyPokemon[this.menuIndex].toSaveData());
                                this.player.removePokemonAt(this.menuIndex);
                                if (this.menuIndex >= this.player.partySize) {
                                    --this.menuIndex;
                                }

                                this.showPokemonPartyDetail(this.menuIndex);
                            } else {
                                this.dialogState = 1;
                                this.dialogManager.showDialog("/data/ui/msgwarm.ui", 257, this);
                                this.setWarningText("Ba lô phải lưu ít nhất 1 sủng vật", "Nhấn nút 5 tiếp tục");
                            }
                        } else {
                            this.dialogState = 1;
                            this.dialogManager.showDialog("/data/ui/msgwarm.ui", 257, this);
                            this.setWarningText("Ngân hàng đã đầy, không thể gởi lại", "Nhấn nút 5 tiếp tục");
                        }
                    } else if (this.gameEngine.previousState != 6 && this.gameEngine.previousState != 0) {
                        if (this.gameEngine.previousState == 27) {
                            if (this.npcDialogIndex == 1 && this.player.partyPokemon[this.menuIndex].getEvolutionTier() == 1
                                    || this.npcDialogIndex == 2 && this.player.partyPokemon[this.menuIndex].getEvolutionTier() == 2) {
                                this.bl();
                            } else {
                                this.dialogState = 4;
                                this.showWarningDialog();
                                if (this.npcDialogIndex == 1) {
                                    this.setWarningText("Sủng vật này không thể tiến hóa", "Nhấn nút 5 tiếp tục");
                                } else if (this.npcDialogIndex == 2) {
                                    this.setWarningText("Sủng vật này không thể dị hoá", "Nhấn nút 5 tiếp tục");
                                } else {
                                    this.setWarningText("Không thể vào hóa cùng dị hoá", "Nhấn nút 5 tiếp tục");
                                }
                            }
                        }
                    } else {
                        this.subMenuIndex = 0;
                        this.gameEngine.handleActionResponse();
                        this.dialogState = 1;
                        this.dialogManager.showDialog("/data/ui/petsetting.ui", 257, this);
                        ((UIContainerComponent) this.dialogManager.currentDialog
                                .getChildById(0)).primaryListComponent.selectedIndex = this.subMenuIndex;
                        if (this.player.partyPokemon[this.menuIndex].getEvolutionTier() == 2) {
                            this.dialogManager.currentDialog.getChildById(9).getComponentData().text = "Dị hoá";
                            ((UIContainerComponent) this.dialogManager.currentDialog
                                    .getChildById(0)).primaryListComponent.totalItemCount = 6;
                            ((UIContainerComponent) this.dialogManager.currentDialog
                                    .getChildById(0)).primaryListComponent.visibleItemCount = 6;
                        } else if (this.player.partyPokemon[this.menuIndex].getEvolutionTier() == 1) {
                            this.dialogManager.currentDialog.getChildById(9).getComponentData().text = "Tiến hóa";
                            ((UIContainerComponent) this.dialogManager.currentDialog
                                    .getChildById(0)).primaryListComponent.totalItemCount = 6;
                            ((UIContainerComponent) this.dialogManager.currentDialog
                                    .getChildById(0)).primaryListComponent.visibleItemCount = 6;
                        } else {
                            this.dialogManager.currentDialog.getChildById(9).getComponentData().text = "";
                            ((UIContainerComponent) this.dialogManager.currentDialog
                                    .getChildById(0)).primaryListComponent.totalItemCount = 5;
                            ((UIContainerComponent) this.dialogManager.currentDialog
                                    .getChildById(0)).primaryListComponent.visibleItemCount = 5;
                        }
                    }
                }
            } else if (QuestManager.isCancelAllowed() && !this.isPopupActive() && this.gameEngine.isKeyPressed(262144)) {
                if (this.gameEngine instanceof WorldGameSession) {
                    if (this.gameEngine.previousState == 16) {
                        this.gameEngine.changeState((byte) 16);
                    } else if (this.gameEngine.previousState == 6) {
                        if (GameEngineBase.paymentActive) {
                            this.menuIndex = 1;
                        } else {
                            this.menuIndex = 0;
                        }

                        this.gameEngine.changeState((byte) 6);
                    } else if (this.gameEngine.previousState == 27) {
                        this.gameEngine.changeState((byte) 27);
                    } else if (this.gameEngine.previousState == 0) {
                        this.gameEngine.changeState((byte) 23);
                    }

                    this.dialogManager.removeDialog("/data/ui/petstate.ui");
                } else if (this.gameEngine instanceof BattleSystemManager) {
                    if (((BattleSystemManager) this.gameEngine).previousState == 7
                            || ((BattleSystemManager) this.gameEngine).previousState == 13) {
                        return;
                    }

                    this.dialogManager.removeDialog("/data/ui/petstate.ui");
                    BattleSystemManager.B().C29_f415 = false;
                    this.tabIndex = 0;
                    this.gameEngine.changeState((byte) 20);
                }
            }
        } else if (this.dialogState == 1) {
            if (!GameEngineBase.isActionBlocked((int) this.subMenuIndex, (int) 0) && !this.isPopupActive()
                    && this.gameEngine.isKeyPressed(4100)) {
                this.dialogManager.currentDialog.handleAction(0);
            } else if (!GameEngineBase.isActionBlocked((int) this.subMenuIndex, (int) 0) && !this.isPopupActive()
                    && this.gameEngine.isKeyPressed(8448)) {
                this.dialogManager.currentDialog.handleAction(1);
            } else if (GameEngineBase.isConfirmAllowed() && !this.isPopupActive() && this.gameEngine.isKeyPressed(196640)) {
                if (GameEngineBase.isActionActive() && !GameEngineBase.isActionBlocked((int) this.subMenuIndex, (int) 0)) {
                    return;
                }

                if (this.gameEngine.previousState == 16) {
                    this.gameEngine.changeState((byte) 16);
                    this.dialogManager.removeDialog("/data/ui/msgwarm.ui");
                    this.dialogManager.removeDialog("/data/ui/petstate.ui");
                    this.dialogState = 0;
                } else if (this.gameEngine.previousState == 6 || this.gameEngine.previousState == 0) {
                    switch (this.subMenuIndex) {
                        case 0:
                            this.bh();
                            break;
                        case 1:
                            if (!this.player.partyPokemon[this.menuIndex].isAlive()) {
                                this.dialogState = 2;
                                this.dialogManager.showDialog("/data/ui/msgwarm.ui", 257, this);
                                this.setWarningText("Sủng vật này không thể tham chiến", "Nhấn nút 5 tiếp tục");
                                this.dialogManager.removeDialog("/data/ui/petsetting.ui");
                                this.menuIndex = 0;
                            } else if (this.menuIndex == 0) {
                                this.dialogState = 2;
                                this.menuIndex = 0;
                                this.dialogManager.showDialog("/data/ui/msgwarm.ui", 257, this);
                                this.setWarningText("Sủng vật này đã xuất chiến", "Nhấn nút 5 tiếp tục");
                                this.dialogManager.removeDialog("/data/ui/petsetting.ui");
                            } else {
                                this.player.movePokemonToFront(this.menuIndex);
                                this.dialogState = 0;
                                this.menuIndex = 0;
                                this.showPokemonPartyDetail(this.menuIndex);
                                this.dialogManager.removeDialog("/data/ui/petsetting.ui");
                                ((UIContainerComponent) this.dialogManager.currentDialog
                                        .getChildById(0)).primaryListComponent.selectedIndex = 0;
                                ((UIContainerComponent) this.dialogManager.currentDialog
                                        .getChildById(0)).primaryListComponent.scrollOffset = 0;
                            }
                            break;
                        case 2:
                            this.gameEngine.handleActionResponse();
                            this.bf();
                            break;
                        case 3:
                            if (ResourceManager.getDatabaseValue((byte) 0, (short) this.player.partyPokemon[this.menuIndex].getSpeciesId(),
                                    (byte) 22) == 2) {
                                this.dialogState = 3;
                                this.showWarningDialog();
                                this.dialogManager.removeDialog("/data/ui/petsetting.ui");
                                this.setWarningText("Thần thú không thể phóng sinh", "Nhấn nút 5 tiếp tục");
                            } else {
                                this.dialogState = 2;
                                this.dialogManager.showDialog("/data/ui/msgconfirm.ui", 257, this);
                                this.dialogManager.removeDialog("/data/ui/petsetting.ui");
                                this.showConfirmMessage("Bạn muốn phóng sinh sủng vật này?", "Xác nhận");
                            }
                            break;
                        case 4:
                            this.bj();
                            break;
                        case 5:
                            this.gameEngine.handleActionResponse();
                            this.bl();
                    }
                }
            } else if (QuestManager.isCancelAllowed() && !this.isPopupActive() && this.gameEngine.isKeyPressed(262144)) {
                if (this.gameEngine.previousState == 16) {
                    return;
                }

                this.dialogState = 0;
                this.dialogManager.removeDialog("/data/ui/petsetting.ui");
            }
        } else if (this.dialogState >= 2) {
            if (this.gameEngine instanceof BattleSystemManager) {
                if (this.gameEngine.isKeyPressed(196640)) {
                    this.dialogState = 0;
                    this.dialogManager.removeDialog("/data/ui/msgwarm.ui");
                }
            } else if (this.gameEngine.previousState != 6 && this.gameEngine.previousState != 0) {
                if (this.dialogState <= 3) {
                    this.bp();
                } else if (this.dialogState == 4 && this.gameEngine.isKeyPressed(196640)) {
                    this.dialogState = 0;
                    this.dialogManager.removeDialog("/data/ui/msgwarm.ui");
                }
            } else {
                switch (this.subMenuIndex) {
                    case 0:
                        this.bn();
                        break;
                    case 1:
                        if (this.gameEngine.isKeyPressed(196640)) {
                            this.dialogState = 0;
                            this.dialogManager.removeDialog("/data/ui/msgwarm.ui");
                        }
                        break;
                    case 2:
                        this.bm();
                        break;
                    case 3:
                        if (this.gameEngine.isKeyPressed(131072) && this.dialogState == 2
                                || this.gameEngine.isKeyPressed(196640) && this.dialogState == 3) {
                            if (this.dialogState == 2) {
                                if (this.player.hasOtherAlivePokemon(this.menuIndex)) {
                                    this.player.deactivateSkill(this.player.partyPokemon[this.menuIndex].getPrimaryState((byte) 5));
                                    this.player.partyPokemon[this.menuIndex].setPrimaryState((byte) 5, (short) -1);
                                    this.player.removePokemonAt(this.menuIndex);
                                    if (this.menuIndex >= this.player.partySize) {
                                        --this.menuIndex;
                                    }

                                    this.showPokemonPartyDetail(this.menuIndex);
                                    ((WorldGameSession) this.gameEngine).questManager.updateQuestEffects();
                                    this.dialogManager.removeDialog("/data/ui/msgconfirm.ui");
                                    this.dialogState = 0;
                                } else {
                                    this.dialogState = 3;
                                    this.dialogManager.showDialog("/data/ui/msgwarm.ui", 257, this);
                                    this.setWarningText("Ba lô phải lưu ít nhất 1 sủng vật", "Nhấn nút 5 tiếp tục");
                                    this.dialogManager.removeDialog("/data/ui/msgconfirm.ui");
                                }
                            } else {
                                this.dialogState = 0;
                                this.dialogManager.removeDialog("/data/ui/msgwarm.ui");
                            }
                        } else if (this.gameEngine.isKeyPressed(786432) && this.dialogState <= 2) {
                            this.dialogState = 0;
                            this.dialogManager.removeDialog("/data/ui/msgconfirm.ui");
                        }
                        break;
                    case 4:
                        this.bo();
                        break;
                    case 5:
                        this.bp();
                }
            }
        }

        this.needsRedraw = true;
        this.handleTaskTipDialog();
    }

    private void bf() {
        this.dialogState = 2;
        this.optionIndex = 0;
        this.dialogManager.showDialog("/data/ui/choice.ui", 257, this);
        this.dialogManager.removeDialog("/data/ui/petsetting.ui");
        this.dialogManager.removeDialog("/data/ui/petstate.ui");
        this.dialogManager.currentDialog.getChildById(8).getComponentData().text = "Vật phẩm trang sức";
        this.dialogManager.currentDialog.getChildById(9).getComponentData().text = "Trạng thái";
        if (this.gameEngine instanceof WorldGameSession) {
            this.dialogManager.currentDialog.getChildById(5).setVisible(false);
            this.dialogManager.currentDialog.getChildById(6).setVisible(false);
            this.dialogManager.currentDialog.getChildById(59).setVisible(true);
            this.dialogManager.currentDialog.getChildById(60).setVisible(true);
            this.dialogManager.currentDialog.getChildById(59).getComponentData().text = "Mang theo";
        } else {
            this.dialogManager.currentDialog.getChildById(5).setVisible(true);
            this.dialogManager.currentDialog.getChildById(6).setVisible(true);
            this.dialogManager.currentDialog.getChildById(59).setVisible(false);
            this.dialogManager.currentDialog.getChildById(60).setVisible(false);
            this.dialogManager.currentDialog.getChildById(5).getComponentData().text = "Mang theo";
        }

        this.bg();
        this.needsRedraw = true;
    }

    private void bg() {
        if (this.player.keyItems.size() > 5) {
            ((UIContainerComponent) this.dialogManager.currentDialog.getChildById(0)).primaryListComponent.setDisplayMode(1);
        } else {
            ((UIContainerComponent) this.dialogManager.currentDialog.getChildById(0)).primaryListComponent.setDisplayMode(0);
        }

        ((UIContainerComponent) this.dialogManager.currentDialog
                .getChildById(0)).primaryListComponent.totalItemCount = this.player.keyItems.size();
        this.listScrollOffset = ((UIContainerComponent) this.dialogManager.currentDialog
                .getChildById(0)).primaryListComponent.scrollOffset;
        this.listSelectedIndex = ((UIContainerComponent) this.dialogManager.currentDialog
                .getChildById(0)).primaryListComponent.selectedIndex;
        if (this.listSelectedIndex >= this.player.keyItems.size()) {
            this.listSelectedIndex = this.player.keyItems.size() - 1;
            ((UIContainerComponent) this.dialogManager.currentDialog
                    .getChildById(0)).primaryListComponent.selectedIndex = this.listSelectedIndex;
        }

        if (this.listScrollOffset > 0 && this.listSelectedIndex - this.listScrollOffset < 4) {
            --this.listScrollOffset;
            ((UIContainerComponent) this.dialogManager.currentDialog
                    .getChildById(0)).primaryListComponent.scrollOffset = this.listScrollOffset;
        }

        if (this.player.keyItems.size() > 0) {
            PokemonEntity var10000 = this.player.partyPokemon[this.menuIndex];
            byte var4 = 5;
            if (var10000.primaryStates[var4] == ((int[]) this.player.keyItems.elementAt(this.listSelectedIndex))[0]) {
                if (this.gameEngine instanceof WorldGameSession) {
                    this.dialogManager.currentDialog.getChildById(59).getComponentData().text = "Dỡ xuống";
                } else {
                    this.dialogManager.currentDialog.getChildById(5).getComponentData().text = "Dỡ xuống";
                }
            } else if (this.gameEngine instanceof WorldGameSession) {
                this.dialogManager.currentDialog.getChildById(59).getComponentData().text = "Mang theo";
            } else {
                this.dialogManager.currentDialog.getChildById(5).getComponentData().text = "Mang theo";
            }

            for (int var1 = 0; var1 < 5; ++var1) {
                if (this.listScrollOffset + var1 < this.player.keyItems.size()) {
                    int[] var2 = (int[]) this.player.keyItems.elementAt(this.listScrollOffset + var1);
                    if (this.dialogManager.currentDialog.getChildById(var1 + 54).getComponentData().renderer == null) {
                        this.dialogManager.currentDialog.getChildById(var1 + 54).getComponentData().renderer = new SpriteRenderer();
                        this.dialogManager.currentDialog.getChildById(var1 + 54).getComponentData().renderer
                                .setSpriteIndex((int) 0);
                        this.dialogManager.currentDialog.getChildById(var1 + 54).getComponentData().renderer.spriteType = 2;
                        this.dialogManager.currentDialog.getChildById(var1 + 54).getComponentData().renderer.initializeSprite(258,
                                false, (byte) -1);
                    }

                    this.dialogManager.currentDialog.getChildById(var1 + 54).getComponentData().renderer
                            .setSpriteIndex((int) ResourceManager.gameDatabase[3][var2[0]][1]);
                    this.dialogManager.currentDialog.getChildById(13 + var1 * 5).getComponentData().text = GameEngineBase
                            .getLocalizedText((int) ResourceManager.gameDatabase[3][var2[0]][0]);
                    var10000 = PlayerCharacter.getInstance().partyPokemon[this.menuIndex];
                    var4 = 5;
                    if (var10000.primaryStates[var4] == var2[0]) {
                        this.dialogManager.currentDialog.getChildById(14 + var1 * 5).getComponentData().text = "Đã mang theo";
                    } else if (var2[1] == 1) {
                        this.dialogManager.currentDialog.getChildById(14 + var1 * 5).getComponentData().text = "Bị mang theo";
                    } else {
                        this.dialogManager.currentDialog.getChildById(14 + var1 * 5).getComponentData().text = "";
                    }
                } else {
                    if (this.dialogManager.currentDialog.getChildById(var1 + 54).getComponentData().renderer != null) {
                        this.dialogManager.currentDialog.getChildById(var1 + 54).getComponentData().renderer.cleanup();
                    }

                    this.dialogManager.currentDialog.getChildById(13 + var1 * 5).getComponentData().text = "";
                    this.dialogManager.currentDialog.getChildById(14 + var1 * 5).getComponentData().text = "";
                }
            }

            if (this.player.keyItems.size() > 0) {
                this.dialogManager.currentDialog.getChildById(53).getComponentData().text = GameEngineBase.getLocalizedText(
                        (int) ResourceManager.gameDatabase[3][((int[]) this.player.keyItems.elementAt(this.listSelectedIndex))[0]][2]);
            } else {
                this.dialogManager.currentDialog.getChildById(53).getComponentData().text = "";
            }

            if (this.player.keyItems.size() > 0) {
                this.dialogManager.currentDialog.getChildById(51).setOffsetY(
                        98 + this.listSelectedIndex * 62 / this.player.keyItems.size(),
                        this.dialogManager.currentDialog.getUIContainerComponent());
            } else {
                this.dialogManager.currentDialog.getChildById(51).setOffsetY(98,
                        this.dialogManager.currentDialog.getUIContainerComponent());
            }
        }
    }

    private void bh() {
        this.dialogState = 2;
        this.optionIndex = 0;
        this.dialogManager.showDialog("/data/ui/choice.ui", 257, this);
        this.dialogManager.removeDialog("/data/ui/petsetting.ui");
        this.dialogManager.removeDialog("/data/ui/petstate.ui");
        this.dialogManager.currentDialog.getChildById(8).getComponentData().text = "Đạo cụ";
        this.dialogManager.currentDialog.getChildById(9).getComponentData().text = "Số lượng";
        if (this.gameEngine instanceof WorldGameSession) {
            this.dialogManager.currentDialog.getChildById(5).setVisible(false);
            this.dialogManager.currentDialog.getChildById(6).setVisible(false);
            this.dialogManager.currentDialog.getChildById(59).setVisible(true);
            this.dialogManager.currentDialog.getChildById(60).setVisible(true);
            this.dialogManager.currentDialog.getChildById(59).getComponentData().text = "Sử dụng";
        } else {
            this.dialogManager.currentDialog.getChildById(5).setVisible(true);
            this.dialogManager.currentDialog.getChildById(6).setVisible(true);
            this.dialogManager.currentDialog.getChildById(59).setVisible(false);
            this.dialogManager.currentDialog.getChildById(60).setVisible(false);
            this.dialogManager.currentDialog.getChildById(5).getComponentData().text = "Sử dụng";
        }

        this.updateSkillDisplay();
        this.needsRedraw = true;
    }

    private void updateSkillDisplay() {
        if (this.player.equipmentInventory.size() > 5) {
            ((UIContainerComponent) this.dialogManager.currentDialog.getChildById(0)).primaryListComponent.setDisplayMode(1);
        } else {
            ((UIContainerComponent) this.dialogManager.currentDialog.getChildById(0)).primaryListComponent.setDisplayMode(0);
        }

        ((UIContainerComponent) this.dialogManager.currentDialog
                .getChildById(0)).primaryListComponent.totalItemCount = this.player.equipmentInventory.size();
        this.listScrollOffset = ((UIContainerComponent) this.dialogManager.currentDialog
                .getChildById(0)).primaryListComponent.scrollOffset;
        this.listSelectedIndex = ((UIContainerComponent) this.dialogManager.currentDialog
                .getChildById(0)).primaryListComponent.selectedIndex;
        if (this.listSelectedIndex >= this.player.equipmentInventory.size()) {
            this.listSelectedIndex = this.player.equipmentInventory.size() - 1;
            ((UIContainerComponent) this.dialogManager.currentDialog
                    .getChildById(0)).primaryListComponent.selectedIndex = this.listSelectedIndex;
        }

        if (this.listScrollOffset > 0 && this.listSelectedIndex - this.listScrollOffset < 4) {
            --this.listScrollOffset;
            ((UIContainerComponent) this.dialogManager.currentDialog
                    .getChildById(0)).primaryListComponent.scrollOffset = this.listScrollOffset;
        }

        for (int var1 = 0; var1 < 5; ++var1) {
            if (this.listScrollOffset + var1 < this.player.equipmentInventory.size()) {
                int[] var2 = (int[]) this.player.equipmentInventory.elementAt(this.listScrollOffset + var1);
                if (this.dialogManager.currentDialog.getChildById(var1 + 54).getComponentData().renderer == null) {
                    this.dialogManager.currentDialog.getChildById(var1 + 54).getComponentData().renderer = new SpriteRenderer();
                    this.dialogManager.currentDialog.getChildById(var1 + 54).getComponentData().renderer.setSpriteIndex((int) 0);
                    this.dialogManager.currentDialog.getChildById(var1 + 54).getComponentData().renderer.spriteType = 2;
                    this.dialogManager.currentDialog.getChildById(var1 + 54).getComponentData().renderer.initializeSprite(258,
                            false, (byte) -1);
                }

                this.dialogManager.currentDialog.getChildById(var1 + 54).getComponentData().renderer
                        .setSpriteIndex((int) ResourceManager.gameDatabase[4][var2[0]][1]);
                this.dialogManager.currentDialog.getChildById(13 + var1 * 5).getComponentData().text = GameEngineBase
                        .getLocalizedText((int) ResourceManager.gameDatabase[4][var2[0]][0]);
                this.dialogManager.currentDialog.getChildById(14 + var1 * 5).getComponentData().text = "" + var2[1];
            } else {
                if (this.dialogManager.currentDialog.getChildById(var1 + 54).getComponentData().renderer != null) {
                    this.dialogManager.currentDialog.getChildById(var1 + 54).getComponentData().renderer.cleanup();
                }

                this.dialogManager.currentDialog.getChildById(13 + var1 * 5).getComponentData().text = "";
                this.dialogManager.currentDialog.getChildById(14 + var1 * 5).getComponentData().text = "";
            }
        }

        if (this.player.equipmentInventory.size() > 0) {
            this.dialogManager.currentDialog.getChildById(53).getComponentData().text = GameEngineBase.getLocalizedText(
                    (int) ResourceManager.gameDatabase[4][((int[]) this.player.equipmentInventory.elementAt(this.listSelectedIndex))[0]][2]);
        } else {
            this.dialogManager.currentDialog.getChildById(53).getComponentData().text = "";
        }

        if (this.player.equipmentInventory.size() > 0) {
            this.dialogManager.currentDialog.getChildById(51).setOffsetY(98 + this.listSelectedIndex * 80 / this.player.equipmentInventory.size(),
                    this.dialogManager.currentDialog.getUIContainerComponent());
        } else {
            this.dialogManager.currentDialog.getChildById(51).setOffsetY(98,
                    this.dialogManager.currentDialog.getUIContainerComponent());
        }
    }

    private void bj() {
        this.dialogState = 2;
        this.optionIndex = 0;
        this.dialogManager.showDialog("/data/ui/skill.ui", 257, this);
        this.dialogManager.removeDialog("/data/ui/petsetting.ui");
        this.dialogManager.removeDialog("/data/ui/petstate.ui");
        this.dialogManager.currentDialog.getChildById(12).getComponentData().text = GameEngineBase
                .getLocalizedText(this.player.partyPokemon[this.menuIndex].getDatabaseValue((byte) 0));
        this.dialogManager.currentDialog.getChildById(14).getComponentData().text = ""
                + this.player.partyPokemon[this.menuIndex].getLevel();
        if (this.dialogManager.currentDialog.getChildById(16).getComponentData().renderer != null) {
            this.dialogManager.currentDialog.getChildById(16).getComponentData().renderer.cleanup();
        } else {
            this.dialogManager.currentDialog.getChildById(16).getComponentData().renderer = new SpriteRenderer();
            this.dialogManager.currentDialog.getChildById(16).getComponentData().renderer.setSpriteIndex((int) 0);
            this.dialogManager.currentDialog.getChildById(16).getComponentData().renderer.spriteType = 3;
        }

        short var10001 = this.player.partyPokemon[this.menuIndex].spriteId;
        this.dialogManager.currentDialog.getChildById(16).getComponentData().renderer.initializeSprite(var10001, false,
                (byte) -1);
        int var1 = this.player.partyPokemon[this.menuIndex].getSkillCount();

        for (int var2 = 0; var2 < var1; ++var2) {
            this.dialogManager.currentDialog.getChildById(var2 + 18).getComponentData().text = GameEngineBase
                    .getLocalizedText((int) ResourceManager.gameDatabase[1][this.player.partyPokemon[this.menuIndex].getSkillAt(var2)][1]);
        }

        this.bk();
        this.needsRedraw = true;
    }

    private void bk() {
        if (this.player.partyPokemon[this.menuIndex].getSkillAt(this.optionIndex) != -1) {
            String[] var1 = new String[]{"Nhất định", "Nhất định"};
            this.dialogManager.currentDialog.getChildById(9).getComponentData().text = GameEngineBase.formatString(
                    ResourceManager.gameDatabase[1][this.player.partyPokemon[this.menuIndex].getSkillAt(this.optionIndex)][2],
                    (String[]) var1);
        } else {
            this.dialogManager.currentDialog.getChildById(9).getComponentData().text = "";
        }
    }

    private void bl() {
        this.dialogState = 2;
        this.optionIndex = 0;
        this.dialogManager.showDialog("/data/ui/evolve.ui", 257, this);
        this.dialogManager.removeDialog("/data/ui/petsetting.ui");
        this.dialogManager.removeDialog("/data/ui/petstate.ui");
        if (this.dialogManager.currentDialog.getChildById(10).getComponentData().renderer == null) {
            this.dialogManager.currentDialog.getChildById(10).getComponentData().renderer = new SpriteRenderer();
            this.dialogManager.currentDialog.getChildById(10).getComponentData().renderer.setSpriteIndex((int) 0);
            this.dialogManager.currentDialog.getChildById(10).getComponentData().renderer.spriteType = 3;
        }

        short var10001 = this.player.partyPokemon[this.menuIndex].spriteId;
        this.dialogManager.currentDialog.getChildById(10).getComponentData().renderer.initializeSprite(var10001, false,
                (byte) -1);
        short var1 = (short) (ResourceManager.getDatabaseValue((byte) 0, (byte) this.player.partyPokemon[this.menuIndex].getSpeciesId(),
                (byte) 20) + 12);
        short var2 = ResourceManager.getDatabaseValue((byte) 0, (byte) this.player.partyPokemon[this.menuIndex].getSpeciesId(),
                (byte) 21);
        this.dialogManager.currentDialog.getChildById(38).getComponentData().text = GameEngineBase.getLocalizedText(
                (int) ResourceManager.getDatabaseValue((byte) 0, (byte) this.player.partyPokemon[this.menuIndex].getSpeciesId(), (byte) 0));
        this.dialogManager.currentDialog.getChildById(40).getComponentData().text = ""
                + this.player.partyPokemon[this.menuIndex].getLevel();
        this.dialogManager.currentDialog.getChildById(45).getComponentData().text = GameEngineBase
                .getLocalizedText((int) ResourceManager.getDatabaseValue((byte) 3, var1, (byte) 0));
        this.dialogManager.currentDialog.getChildById(46).getComponentData().text = this.player.getItemCount((int) var1, (byte) 2) + "/"
                + var2;
        var1 = ResourceManager.getDatabaseValue((byte) 0, (byte) this.player.partyPokemon[this.menuIndex].getSpeciesId(), (byte) 19);
        PokemonEntity var6;
        (var6 = new PokemonEntity()).initialize(var1, (byte) this.player.partyPokemon[this.menuIndex].getLevel(), (short) -1, (byte) -1,
                (short) -1, (byte) -1);

        for (int var5 = 0; var5 < 4; ++var5) {
            ComponentData var10000 = this.dialogManager.currentDialog.getChildById(var5 + 19).getComponentData();
            StringBuffer var7 = new StringBuffer();
            PokemonEntity var10002 = this.player.partyPokemon[this.menuIndex];
            byte var4 = (byte) (var5 + 1);
            var10000.text = var7.append(var10002.primaryStates[var4]).toString();
            var10000 = this.dialogManager.currentDialog.getChildById(var5 + 31).getComponentData();
            var7 = new StringBuffer();
            var4 = (byte) (var5 + 1);
            var10000.text = var7.append(var6.primaryStates[var4]).toString();
        }

        this.needsRedraw = true;
    }

    private void bm() {
        if (!GameEngineBase.isActionBlocked((int) this.menuIndex, (int) 0) && !this.isPopupActive() && this.dialogState == 2
                && this.gameEngine.isKeyPressed(4100)) {
            this.dialogManager.currentDialog.handleAction(0);
            this.bg();
        } else if (!GameEngineBase.isActionBlocked((int) this.menuIndex, (int) 0) && !this.isPopupActive() && this.dialogState == 2
                && this.gameEngine.isKeyPressed(8448)) {
            this.dialogManager.currentDialog.handleAction(1);
            this.bg();
        } else if (GameEngineBase.isConfirmAllowed() && !this.isPopupActive() && this.gameEngine.isKeyPressed(196640)
                && this.player.keyItems.size() > 0) {
            if (!GameEngineBase.isActionActive() || GameEngineBase.isActionBlocked((int) this.menuIndex, (int) 0)) {
                if (this.dialogState == 2) {
                    int[] var1 = (int[]) this.player.keyItems.elementAt(this.listSelectedIndex);
                    PokemonEntity var10000 = this.player.partyPokemon[this.menuIndex];
                    byte var3 = 5;
                    if (var10000.primaryStates[var3] == var1[0]) {
                        PokemonEntity var10001 = this.player.partyPokemon[this.menuIndex];
                        var3 = 5;
                        this.player.deactivateSkill(var10001.primaryStates[var3]);
                        var10000 = this.player.partyPokemon[this.menuIndex];
                        byte var4 = -1;
                        var3 = 5;
                        var10000.primaryStates[var3] = var4;
                        this.bg();
                        this.showWarningDialog();
                        this.setWarningText("Thành công dỡ xuống", "Nhấn nút 5 tiếp tục");
                    } else {
                        this.player.assignSkillToPokemon(var1[0], this.menuIndex);
                        this.bg();
                        this.showWarningDialog();
                        this.setWarningText("Thành công mang theo", "Nhấn nút 5 tiếp tục");
                    }

                    this.dialogState = 3;
                } else {
                    this.dialogState = 2;
                    this.gameEngine.handleActionResponse();
                    this.showPokemonPartyDetail(this.menuIndex);
                    this.hideWarningDialog();
                    this.dialogManager.removeDialog("/data/ui/choice.ui");
                }
            }
        } else {
            if (QuestManager.isCancelAllowed() && !this.isPopupActive() && this.dialogState == 2 && this.gameEngine.isKeyPressed(262144)) {
                this.showPokemonPartyDetail(this.menuIndex);
                this.dialogManager.removeDialog("/data/ui/choice.ui");
            }

        }
    }

    private void bn() {
        if (this.dialogState == 2 && this.gameEngine.isKeyPressed(4100)) {
            this.dialogManager.currentDialog.handleAction(0);
        } else if (this.dialogState == 2 && this.gameEngine.isKeyPressed(8448)) {
            this.dialogManager.currentDialog.handleAction(1);
        } else {
            if (this.gameEngine.isKeyPressed(196640)) {
                if (this.player.equipmentInventory.size() <= 0) {
                    return;
                }

                if (this.dialogState == 2) {
                    this.dialogState = 3;
                    int[] var1;
                    switch ((var1 = (int[]) this.player.equipmentInventory.elementAt(this.optionIndex))[0]) {
                        case 13:
                        case 14:
                            this.dialogManager.showDialog("/data/ui/msgwarm.ui", 257, this);
                            this.setWarningText("Đạo cụ này không thể sử dụng", "Nhấn nút 5 tiếp tục");
                            return;
                        default:
                            switch (this.player.partyPokemon[this.menuIndex].canUseItem(var1[0])) {
                                case 0:
                                    this.dialogManager.showDialog("/data/ui/msgwarm.ui", 257, this);
                                    this.setWarningText("Sủng vật này đã tử vong, không thể sử dụng", "Nhấn nút 5 tiếp tục");
                                    return;
                                case 1:
                                    this.dialogManager.showDialog("/data/ui/msgwarm.ui", 257, this);
                                    this.setWarningText("Sủng vật này không có, không thể sử dụng", "Nhấn nút 5 tiếp tục");
                                    return;
                                case 2:
                                    this.dialogManager.showDialog("/data/ui/msgwarm.ui", 257, this);
                                    this.setWarningText("Máu đầy, không cần sử dụng", "Nhấn nút 5 tiếp tục");
                                    return;
                                case 3:
                                    this.dialogManager.showDialog("/data/ui/msgwarm.ui", 257, this);
                                    this.setWarningText("Kỹ năng giá trị đã đầy, không cần sử dụng", "Nhấn nút 5 tiếp tục");
                                    return;
                                case 4:
                                    this.dialogManager.showDialog("/data/ui/msgwarm.ui", 257, this);
                                    this.setWarningText("Trên người đều bị lợi hiệu quả", "Nhấn nút 5 tiếp tục");
                                    return;
                                case 5:
                                    this.dialogManager.showDialog("/data/ui/msgwarm.ui", 257, this);
                                    this.setWarningText("Trong hưng phấn, không thể dùng", "Nhấn nút 5 tiếp tục");
                                    return;
                                case 6:
                                default:
                                    this.player.partyPokemon[this.menuIndex].useItem(var1[0]);
                                    this.showPokemonPartyDetail(this.menuIndex);
                                    this.dialogState = 4;
                                    this.dialogManager.showDialog("/data/ui/msgwarm.ui", 257, this);
                                    this.setWarningText("Thành công sử dụng đạo cụ", "Nhấn nút 5 tiếp tục");
                                    this.dialogManager.removeDialog("/data/ui/choice.ui");
                                    return;
                                case 7:
                                    this.dialogManager.showDialog("/data/ui/msgwarm.ui", 257, this);
                                    this.setWarningText("Máu và tinh khí đều đã đầy, không cần sử dụng", "Nhấn nút 5 tiếp tục");
                                    return;
                                case 8:
                                    this.dialogManager.showDialog("/data/ui/msgwarm.ui", 257, this);
                                    this.setWarningText("Sủng vật đã chết, không thể sử dụng", "Nhấn nút 5 tiếp tục");
                                    return;
                            }
                    }
                }

                if (this.dialogState == 3) {
                    this.dialogState = 2;
                    this.dialogManager.removeDialog("/data/ui/msgwarm.ui");
                    return;
                }

                if (this.dialogState == 4) {
                    this.dialogState = 0;
                    this.dialogManager.removeDialog("/data/ui/msgwarm.ui");
                    return;
                }
            } else if (this.dialogState == 2 && this.gameEngine.isKeyPressed(262144)) {
                this.showPokemonPartyDetail(this.menuIndex);
                this.dialogManager.removeDialog("/data/ui/choice.ui");
            }

        }
    }

    private void bo() {
        if (this.gameEngine.isKeyPressed(4100)) {
            this.dialogManager.currentDialog.handleAction(0);
            this.bk();
        } else if (this.gameEngine.isKeyPressed(8448)) {
            this.dialogManager.currentDialog.handleAction(1);
            this.bk();
        } else if (this.gameEngine.isKeyPressed(16400)) {
            this.dialogManager.currentDialog.handleAction(2);
            this.bk();
        } else if (this.gameEngine.isKeyPressed(32832)) {
            this.dialogManager.currentDialog.handleAction(3);
            this.bk();
        } else {
            if (this.gameEngine.isKeyPressed(262144)) {
                this.showPokemonPartyDetail(this.menuIndex);
                this.dialogManager.removeDialog("/data/ui/skill.ui");
            }

        }
    }

    private void bp() {
        short var1;
        short var3;
        short var4;
        if (WorldGameSession.activeEffect != null) {
            if (!WorldGameSession.activeEffect.isActive()) {
                var1 = ResourceManager.getDatabaseValue((byte) 0, (byte) this.player.partyPokemon[this.menuIndex].getSpeciesId(),
                        (byte) 19);
                String var9 = GameEngineBase
                        .getLocalizedText((int) ResourceManager.getDatabaseValue((byte) 0, var1, (byte) 0));
                var3 = ResourceManager.getDatabaseValue((byte) 0, (byte) this.player.partyPokemon[this.menuIndex].getSpeciesId(),
                        (byte) 19);
                var4 = ResourceManager.getDatabaseValue((byte) 0, var3, (byte) 17);
                ResourceManager.getDatabaseValue((byte) 0, (byte) this.player.partyPokemon[this.menuIndex].getSpeciesId(), (byte) 21);
                this.dialogManager.currentDialog.getChildById(10).setVisible(true);
                this.dialogManager.currentDialog.getChildById(10).getComponentData().renderer.initializeSprite(var4, false,
                        (byte) -1);
                this.dialogManager.currentDialog.getChildById(38).getComponentData().text = GameEngineBase
                        .getLocalizedText((int) ResourceManager.getDatabaseValue((byte) 0, var3, (byte) 0));
                PokemonEntity var11 = new PokemonEntity();
                short var5 = ResourceManager.getDatabaseValue((byte) 0, var3, (byte) 3);
                byte var6 = -1;
                PokemonEntity var10000 = this.player.partyPokemon[this.menuIndex];
                byte var8 = 0;
                if (var10000.primaryStates[var8] >= var5) {
                    var10000 = this.player.partyPokemon[this.menuIndex];
                    var8 = 0;
                    var6 = (byte) var10000.primaryStates[var8];
                }

                int var10002 = this.player.partyPokemon[this.menuIndex].getLevel();
                PokemonEntity var10003 = this.player.partyPokemon[this.menuIndex];
                var8 = 5;
                short var13 = var10003.primaryStates[var8];
                PokemonEntity var10004 = this.player.partyPokemon[this.menuIndex];
                var8 = 6;
                var11.initialize(var3, var10002, var13, (byte) var10004.secondaryStates[var8], (short) var6, (byte) -1);
                var8 = 1;
                var11.setExpAndFriendship((short) var11.primaryStates[var8], this.player.partyPokemon[this.menuIndex].getCurrentExp(),
                        this.player.partyPokemon[this.menuIndex].friendship);
                var11.loadSkillData(this.player.partyPokemon[this.menuIndex].getSkillSaveData());
                this.player.registerCreature((byte) ((byte) this.player.partyPokemon[this.menuIndex].getDatabaseValue((byte) 1)), var3, (byte) 2);
                this.player.partyPokemon[this.menuIndex].initializeFromData(var11.toSaveData());
                var5 = (short) (ResourceManager.getDatabaseValue((byte) 0, (byte) this.player.partyPokemon[this.menuIndex].getSpeciesId(),
                        (byte) 20) + 12);
                var4 = ResourceManager.getDatabaseValue((byte) 0, (byte) this.player.partyPokemon[this.menuIndex].getSpeciesId(),
                        (byte) 21);
                var3 = ResourceManager.getDatabaseValue((byte) 0, (byte) this.player.partyPokemon[this.menuIndex].getSpeciesId(),
                        (byte) 19);
                int var12 = this.player.getItemCount((int) var5, (byte) 2);
                if (var3 == -1) {
                    this.dialogManager.currentDialog.getChildById(42).getComponentData().text = "";
                    this.dialogManager.currentDialog.getChildById(45).getComponentData().text = "";
                    this.dialogManager.currentDialog.getChildById(46).getComponentData().text = "";
                } else {
                    this.dialogManager.currentDialog.getChildById(45).getComponentData().text = GameEngineBase
                            .getLocalizedText((int) ResourceManager.getDatabaseValue((byte) 3, var5, (byte) 0));
                    this.dialogManager.currentDialog.getChildById(46).getComponentData().text = var12 + "/" + var4;
                }

                if (this.player.partyPokemon[this.menuIndex].getEvolutionTier() == 2) {
                    this.dialogState = 3;
                    this.dialogManager.showDialog("/data/ui/msgwarm.ui", 257, this);
                    this.setWarningText("Dị hoá thành #2" + var9, "Nhấn nút 5 tiếp tục");
                } else {
                    this.dialogState = 3;
                    this.dialogManager.showDialog("/data/ui/msgwarm.ui", 257, this);
                    this.setWarningText("Tiến hóa thành #2" + var9, "Nhấn nút 5 tiếp tục");
                }

                WorldGameSession.activeEffect = null;
            }

        } else {
            if (GameEngineBase.isConfirmAllowed() && !this.isPopupActive() && this.gameEngine.isKeyPressed(196640)) {
                if (this.dialogState == 2) {
                    var1 = (short) (ResourceManager.getDatabaseValue((byte) 0,
                            (byte) this.player.partyPokemon[this.menuIndex].getSpeciesId(), (byte) 20) + 12);
                    short var2 = ResourceManager.getDatabaseValue((byte) 0, (byte) this.player.partyPokemon[this.menuIndex].getSpeciesId(),
                            (byte) 21);
                    if ((var3 = ResourceManager.getDatabaseValue((byte) 0, (byte) this.player.partyPokemon[this.menuIndex].getSpeciesId(),
                            (byte) 19)) == -1) {
                        this.dialogState = 3;
                        this.showWarningDialog();
                        this.setWarningText("Không thể lại tiến hóa hoặc dị hoá", "Nhấn nút 5 tiếp tục");
                        return;
                    }

                    var4 = ResourceManager.getDatabaseValue((byte) 0, var3, (byte) 17);
                    if (this.player.partyPokemon[this.menuIndex]
                            .getLevel() >= PokemonEntity.EVOLUTION_LEVEL_REQUIREMENTS[ResourceManager.getDatabaseValue((byte) 0, var3, (byte) 2) - 1]) {
                        if (this.player.getItemCount((int) var1, (byte) 2) >= var2) {
                            this.dialogManager.currentDialog.getChildById(10).setVisible(false);
                            WorldGameSession.activeEffect = new EffectEntity();
                            short[] var10 = new short[]{0, 0, 10, 116, 164, this.player.partyPokemon[this.menuIndex].spriteId, 0,
                                    0, var4, 0, 0};
                            WorldGameSession.activeEffect.initializeEffect(var10);
                            WorldGameSession.activeEffect.setInteractable(true);
                            WorldGameSession.activeEffect.activateEffect();
                            this.player.removeItem(var1, var2, (byte) 2);
                            return;
                        }

                        this.dialogState = 3;
                        this.dialogManager.showDialog("/data/ui/msgwarm.ui", 257, this);
                        if (this.player.partyPokemon[this.menuIndex].getEvolutionTier() == 2) {
                            this.setWarningText("Tài liệu chưa đủ, không thể dị hoá", "Nhấn nút 5 tiếp tục");
                            return;
                        }

                        this.setWarningText("Tài liệu chưa đủ, không thể tiến hóa", "Nhấn nút 5 tiếp tục");
                        return;
                    }

                    this.dialogState = 3;
                    this.dialogManager.showDialog("/data/ui/msgwarm.ui", 257, this);
                    this.setWarningText("Còn chưa tới"
                            + PokemonEntity.EVOLUTION_LEVEL_REQUIREMENTS[ResourceManager.getDatabaseValue((byte) 0, var3, (byte) 2) - 1]
                            + " cấp, không thể vào hóa", "Nhấn nút 5 tiếp tục");
                    return;
                }

                if (this.dialogState == 3) {
                    if (this.gameEngine.previousState == 6 || this.gameEngine.previousState == 0) {
                        this.dialogState = 2;
                        this.dialogManager.removeDialog("/data/ui/msgwarm.ui");
                        this.gameEngine.handleActionResponse();
                        return;
                    }

                    if (this.gameEngine.previousState == 27) {
                        this.showPokemonPartyDetail(this.menuIndex);
                        this.dialogState = 0;
                        this.subMenuIndex = 0;
                        this.dialogManager.removeDialog("/data/ui/msgwarm.ui");
                        this.dialogManager.removeDialog("/data/ui/evolve.ui");
                        return;
                    }
                }
            } else if (this.dialogState < 3 && this.gameEngine.isKeyPressed(262144) && !this.isPopupActive()
                    && GameEngineBase.isCancelAllowed()) {
                this.dialogState = 0;
                this.showPokemonPartyDetail(this.menuIndex);
                this.dialogManager.removeDialog("/data/ui/evolve.ui");
            }

        }
    }

    public final void updateBagMenu() {
        this.dialogManager.showDialog("/data/ui/bag.ui", 257, this);
        this.menuIndex = 0;
        this.bq();
        this.dialogManager.currentDialog.handleAction(5);
        this.dialogManager.currentDialog.getChildById(14).getComponentData().text = "Vật phẩm";
        this.menuIndex = 0;
    }

    private void bq() {
        ((UIContainerComponent) this.dialogManager.currentDialog
                .getChildById(8 + this.menuIndex * 39)).primaryListComponent.scrollOffset = 0;
        ((UIContainerComponent) this.dialogManager.currentDialog
                .getChildById(8 + this.menuIndex * 39)).primaryListComponent.selectedIndex = 0;
        this.updateInventoryDisplay();
    }

    private void updateInventoryDisplay() {
        switch (this.menuIndex) {
            case 0:
                this.bs();
                break;
            case 1:
                GameUIController var4 = this;
                if (this.player.keyItems.size() > 5) {
                    ((UIContainerComponent) this.dialogManager.currentDialog.getChildById(47)).primaryListComponent
                            .setDisplayMode(1);
                } else {
                    ((UIContainerComponent) this.dialogManager.currentDialog.getChildById(47)).primaryListComponent
                            .setDisplayMode(0);
                }

                ((UIContainerComponent) this.dialogManager.currentDialog
                        .getChildById(47)).primaryListComponent.totalItemCount = this.player.keyItems.size();
                this.listScrollOffset = ((UIContainerComponent) this.dialogManager.currentDialog
                        .getChildById(47)).primaryListComponent.scrollOffset;
                this.listSelectedIndex = ((UIContainerComponent) this.dialogManager.currentDialog
                        .getChildById(47)).primaryListComponent.selectedIndex;
                this.dialogManager.currentDialog.getChildById(7).setVisible(false);

                for (int var2 = 0; var2 < 5; ++var2) {
                    if (var4.listScrollOffset + var2 < var4.player.keyItems.size()) {
                        int[] var3 = (int[]) var4.player.keyItems.elementAt(var4.listScrollOffset + var2);
                        if (var4.dialogManager.currentDialog.getChildById(59 + var2 * 5).getComponentData().renderer == null) {
                            var4.dialogManager.currentDialog.getChildById(59 + var2 * 5)
                                    .getComponentData().renderer = new SpriteRenderer();
                            var4.dialogManager.currentDialog.getChildById(59 + var2 * 5).getComponentData().renderer
                                    .setSpriteIndex((int) 0);
                            var4.dialogManager.currentDialog.getChildById(59 + var2 * 5).getComponentData().renderer.spriteType = 2;
                            var4.dialogManager.currentDialog.getChildById(59 + var2 * 5).getComponentData().renderer
                                    .initializeSprite(258, false, (byte) -1);
                        }

                        if (var4.dialogManager.currentDialog.getChildById(59 + var2 * 5)
                                .getComponentData().focusedRenderer == null) {
                            var4.dialogManager.currentDialog.getChildById(59 + var2 * 5)
                                    .getComponentData().focusedRenderer = new SpriteRenderer();
                            var4.dialogManager.currentDialog.getChildById(59 + var2 * 5).getComponentData().focusedRenderer
                                    .setSpriteIndex((int) 0);
                            var4.dialogManager.currentDialog.getChildById(59 + var2 * 5)
                                    .getComponentData().focusedRenderer.spriteType = 2;
                            var4.dialogManager.currentDialog.getChildById(59 + var2 * 5).getComponentData().focusedRenderer
                                    .initializeSprite(258, false, (byte) -1);
                        }

                        var4.dialogManager.currentDialog.getChildById(59 + var2 * 5).getComponentData().renderer
                                .setSpriteIndex((int) ResourceManager.gameDatabase[3][var3[0]][1]);
                        var4.dialogManager.currentDialog.getChildById(59 + var2 * 5).getComponentData().focusedRenderer
                                .setSpriteIndex((int) ResourceManager.gameDatabase[3][var3[0]][1]);
                        var4.dialogManager.currentDialog.getChildById(60 + var2 * 5).getComponentData().text = GameEngineBase
                                .getLocalizedText((int) ResourceManager.gameDatabase[3][var3[0]][0]);
                        if (var3[1] == 1) {
                            var4.dialogManager.currentDialog.getChildById(61 + var2 * 5).getComponentData().text = "Đã mang theo";
                        } else {
                            var4.dialogManager.currentDialog.getChildById(61 + var2 * 5).getComponentData().text = "";
                        }
                    } else {
                        if (var4.dialogManager.currentDialog.getChildById(59 + var2 * 5).getComponentData().renderer != null) {
                            var4.dialogManager.currentDialog.getChildById(59 + var2 * 5).getComponentData().renderer.cleanup();
                        }

                        var4.dialogManager.currentDialog.getChildById(60 + var2 * 5).getComponentData().text = "";
                        var4.dialogManager.currentDialog.getChildById(61 + var2 * 5).getComponentData().text = "";
                    }
                }

                if (var4.player.keyItems.size() > 0) {
                    var4.dialogManager.currentDialog.getChildById(85).getComponentData().text = GameEngineBase
                            .getLocalizedText((int) ResourceManager.gameDatabase[3][((int[]) var4.player.keyItems
                                    .elementAt(var4.listSelectedIndex))[0]][2]);
                } else {
                    var4.dialogManager.currentDialog.getChildById(85).getComponentData().text = "";
                }

                if (var4.player.keyItems.size() > 0) {
                    var4.dialogManager.currentDialog.getChildById(84).setOffsetY(
                            127 + var4.listSelectedIndex * 72 / var4.player.keyItems.size(),
                            var4.dialogManager.currentDialog.getUIContainerComponent());
                } else {
                    var4.dialogManager.currentDialog.getChildById(84).setOffsetY(127,
                            var4.dialogManager.currentDialog.getUIContainerComponent());
                }
                break;
            case 2:
                this.bt();
                break;
            case 3:
                this.bu();
                if (this.listSelectedIndex < 0 || this.player.skills.size() <= 0) {
                    return;
                }

                int[] var1 = (int[]) this.player.skills.elementAt(this.listSelectedIndex);
                this.dialogManager.currentDialog.getChildById(164).setVisible(false);
                this.dialogManager.currentDialog.getChildById(165).setVisible(false);
                switch (var1[0]) {
                    case 0:
                        if (this.player.isSkillActive(var1[0])) {
                            this.dialogManager.currentDialog.getChildById(7).setVisible(true);
                            this.dialogManager.currentDialog.getChildById(7).getComponentData().text = "Ấp trứng";
                            this.dialogManager.currentDialog.getChildById(164).setVisible(true);
                            this.dialogManager.currentDialog.getChildById(165).setVisible(true);
                            if (this.player.pokedexCount == 0) {
                                this.dialogManager.currentDialog.getChildById(164).getComponentData().text = "#P"
                                        + WorldGameSession.totalBattleCount * 100 / 10;
                                this.dialogManager.currentDialog.getChildById(165).getComponentData().text = WorldGameSession.totalBattleCount
                                        + "/10";
                            } else {
                                this.dialogManager.currentDialog.getChildById(164).getComponentData().text = "#P"
                                        + WorldGameSession.totalBattleCount * 100 / 30;
                                this.dialogManager.currentDialog.getChildById(165).getComponentData().text = WorldGameSession.totalBattleCount
                                        + "/30";
                            }
                        } else {
                            this.dialogManager.currentDialog.getChildById(7).setVisible(false);
                        }
                        break;
                    case 1:
                    case 2:
                    case 3:
                    case 4:
                        this.dialogManager.currentDialog.getChildById(7).setVisible(false);
                        break;
                    case 5:
                    case 6:
                    case 10:
                        this.dialogManager.currentDialog.getChildById(7).getComponentData().text = "Mở ra";
                        break;
                    case 7:
                    case 8:
                    case 9:
                        this.dialogManager.currentDialog.getChildById(7).getComponentData().text = "Sử dụng";
                }
        }

        this.needsRedraw = true;
    }

    private void bs() {
        int var1;
        if ((var1 = this.player.consumableInventory.size() + this.player.equipmentInventory.size()) > 5) {
            ((UIContainerComponent) this.dialogManager.currentDialog.getChildById(8)).primaryListComponent.setDisplayMode(1);
        } else {
            ((UIContainerComponent) this.dialogManager.currentDialog.getChildById(8)).primaryListComponent.setDisplayMode(0);
        }

        ((UIContainerComponent) this.dialogManager.currentDialog.getChildById(8)).primaryListComponent.totalItemCount = var1;
        this.listScrollOffset = ((UIContainerComponent) this.dialogManager.currentDialog
                .getChildById(8)).primaryListComponent.scrollOffset;
        this.listSelectedIndex = ((UIContainerComponent) this.dialogManager.currentDialog
                .getChildById(8)).primaryListComponent.selectedIndex;
        this.dialogManager.currentDialog.getChildById(7).setVisible(true);
        this.dialogManager.currentDialog.getChildById(7).getComponentData().text = "Sử dụng";

        for (int var2 = 0; var2 < 5; ++var2) {
            if (this.listScrollOffset + var2 < var1) {
                int[] var3;
                if (this.listScrollOffset + var2 < this.player.consumableInventory.size()) {
                    var3 = (int[]) this.player.consumableInventory.elementAt(this.listScrollOffset + var2);
                } else {
                    var3 = (int[]) this.player.equipmentInventory.elementAt(this.listScrollOffset + var2 - this.player.consumableInventory.size());
                }

                if (this.dialogManager.currentDialog.getChildById(18 + var2 * 5).getComponentData().renderer == null) {
                    this.dialogManager.currentDialog.getChildById(18 + var2 * 5)
                            .getComponentData().renderer = new SpriteRenderer();
                    this.dialogManager.currentDialog.getChildById(18 + var2 * 5).getComponentData().renderer
                            .setSpriteIndex((int) 0);
                    this.dialogManager.currentDialog.getChildById(18 + var2 * 5).getComponentData().renderer.spriteType = 2;
                    this.dialogManager.currentDialog.getChildById(18 + var2 * 5).getComponentData().renderer.initializeSprite(258,
                            false, (byte) -1);
                }

                if (this.dialogManager.currentDialog.getChildById(18 + var2 * 5).getComponentData().focusedRenderer == null) {
                    this.dialogManager.currentDialog.getChildById(18 + var2 * 5)
                            .getComponentData().focusedRenderer = new SpriteRenderer();
                    this.dialogManager.currentDialog.getChildById(18 + var2 * 5).getComponentData().focusedRenderer
                            .setSpriteIndex((int) 0);
                    this.dialogManager.currentDialog.getChildById(18 + var2 * 5).getComponentData().focusedRenderer.spriteType = 2;
                    this.dialogManager.currentDialog.getChildById(18 + var2 * 5).getComponentData().focusedRenderer
                            .initializeSprite(258, false, (byte) -1);
                }

                this.dialogManager.currentDialog.getChildById(18 + var2 * 5).getComponentData().renderer
                        .setSpriteIndex((int) ResourceManager.gameDatabase[4][var3[0]][1]);
                this.dialogManager.currentDialog.getChildById(18 + var2 * 5).getComponentData().focusedRenderer
                        .setSpriteIndex((int) ResourceManager.gameDatabase[4][var3[0]][1]);
                this.dialogManager.currentDialog.getChildById(19 + var2 * 5).getComponentData().text = GameEngineBase
                        .getLocalizedText((int) ResourceManager.gameDatabase[4][var3[0]][0]);
                this.dialogManager.currentDialog.getChildById(20 + var2 * 5).getComponentData().text = "" + var3[1];
            } else {
                if (this.dialogManager.currentDialog.getChildById(18 + var2 * 5).getComponentData().renderer != null) {
                    this.dialogManager.currentDialog.getChildById(18 + var2 * 5).getComponentData().renderer.cleanup();
                }

                this.dialogManager.currentDialog.getChildById(19 + var2 * 5).getComponentData().text = "";
                this.dialogManager.currentDialog.getChildById(20 + var2 * 5).getComponentData().text = "";
            }
        }

        if (var1 > 0) {
            if (this.listSelectedIndex < this.player.consumableInventory.size()) {
                this.dialogManager.currentDialog.getChildById(46).getComponentData().text = GameEngineBase.getLocalizedText(
                        (int) ResourceManager.gameDatabase[4][((int[]) this.player.consumableInventory.elementAt(this.listSelectedIndex))[0]][2]);
            } else {
                this.dialogManager.currentDialog.getChildById(46).getComponentData().text = GameEngineBase
                        .getLocalizedText((int) ResourceManager.gameDatabase[4][((int[]) this.player.equipmentInventory
                                .elementAt(this.listSelectedIndex - this.player.consumableInventory.size()))[0]][2]);
            }
        } else {
            this.dialogManager.currentDialog.getChildById(46).getComponentData().text = "";
        }

        if (var1 > 0) {
            this.dialogManager.currentDialog.getChildById(43).setOffsetY(127 + this.listSelectedIndex * 72 / var1,
                    this.dialogManager.currentDialog.getUIContainerComponent());
        } else {
            this.dialogManager.currentDialog.getChildById(43).setOffsetY(127,
                    this.dialogManager.currentDialog.getUIContainerComponent());
        }
    }

    private void bt() {
        if (this.player.specialItems.size() > 5) {
            ((UIContainerComponent) this.dialogManager.currentDialog.getChildById(86)).primaryListComponent.setDisplayMode(1);
        } else {
            ((UIContainerComponent) this.dialogManager.currentDialog.getChildById(86)).primaryListComponent.setDisplayMode(0);
        }

        ((UIContainerComponent) this.dialogManager.currentDialog
                .getChildById(86)).primaryListComponent.totalItemCount = this.player.specialItems.size();
        this.listScrollOffset = ((UIContainerComponent) this.dialogManager.currentDialog
                .getChildById(86)).primaryListComponent.scrollOffset;
        this.listSelectedIndex = ((UIContainerComponent) this.dialogManager.currentDialog
                .getChildById(86)).primaryListComponent.selectedIndex;
        this.dialogManager.currentDialog.getChildById(7).setVisible(false);

        for (int var1 = 0; var1 < 5; ++var1) {
            if (this.listScrollOffset + var1 < this.player.specialItems.size()) {
                int[] var2 = (int[]) this.player.specialItems.elementAt(this.listScrollOffset + var1);
                if (this.dialogManager.currentDialog.getChildById(98 + var1 * 5).getComponentData().renderer == null) {
                    this.dialogManager.currentDialog.getChildById(98 + var1 * 5)
                            .getComponentData().renderer = new SpriteRenderer();
                    this.dialogManager.currentDialog.getChildById(98 + var1 * 5).getComponentData().renderer
                            .setSpriteIndex((int) 0);
                    this.dialogManager.currentDialog.getChildById(98 + var1 * 5).getComponentData().renderer.spriteType = 2;
                    this.dialogManager.currentDialog.getChildById(98 + var1 * 5).getComponentData().renderer.initializeSprite(258,
                            false, (byte) -1);
                }

                if (this.dialogManager.currentDialog.getChildById(98 + var1 * 5).getComponentData().focusedRenderer == null) {
                    this.dialogManager.currentDialog.getChildById(98 + var1 * 5)
                            .getComponentData().focusedRenderer = new SpriteRenderer();
                    this.dialogManager.currentDialog.getChildById(98 + var1 * 5).getComponentData().focusedRenderer
                            .setSpriteIndex((int) 0);
                    this.dialogManager.currentDialog.getChildById(98 + var1 * 5).getComponentData().focusedRenderer.spriteType = 2;
                    this.dialogManager.currentDialog.getChildById(98 + var1 * 5).getComponentData().focusedRenderer
                            .initializeSprite(258, false, (byte) -1);
                }

                this.dialogManager.currentDialog.getChildById(98 + var1 * 5).getComponentData().renderer
                        .setSpriteIndex((int) ResourceManager.gameDatabase[3][var2[0]][1]);
                this.dialogManager.currentDialog.getChildById(98 + var1 * 5).getComponentData().focusedRenderer
                        .setSpriteIndex((int) ResourceManager.gameDatabase[3][var2[0]][1]);
                if (var2[0] == 17) {
                    this.dialogManager.currentDialog.getChildById(99 + var1 * 5).getComponentData().text = "Chìa khóa vàng";
                } else {
                    this.dialogManager.currentDialog.getChildById(99 + var1 * 5).getComponentData().text = GameEngineBase
                            .getLocalizedText((int) ResourceManager.gameDatabase[3][var2[0]][0]);
                }

                this.dialogManager.currentDialog.getChildById(100 + var1 * 5).getComponentData().text = "" + var2[1];
            } else {
                if (this.dialogManager.currentDialog.getChildById(98 + var1 * 5).getComponentData().renderer != null) {
                    this.dialogManager.currentDialog.getChildById(98 + var1 * 5).getComponentData().renderer.cleanup();
                }

                this.dialogManager.currentDialog.getChildById(99 + var1 * 5).getComponentData().text = "";
                this.dialogManager.currentDialog.getChildById(100 + var1 * 5).getComponentData().text = "";
            }
        }

        if (this.player.specialItems.size() > 0) {
            this.dialogManager.currentDialog.getChildById(124).getComponentData().text = GameEngineBase.getLocalizedText(
                    (int) ResourceManager.gameDatabase[3][((int[]) this.player.specialItems.elementAt(this.listSelectedIndex))[0]][2]);
        } else {
            this.dialogManager.currentDialog.getChildById(124).getComponentData().text = "";
        }

        if (this.player.specialItems.size() > 0) {
            this.dialogManager.currentDialog.getChildById(123).setOffsetY(127 + this.listSelectedIndex * 72 / this.player.specialItems.size(),
                    this.dialogManager.currentDialog.getUIContainerComponent());
        } else {
            this.dialogManager.currentDialog.getChildById(123).setOffsetY(127,
                    this.dialogManager.currentDialog.getUIContainerComponent());
        }
    }

    private void bu() {
        if (this.player.skills.size() > 5) {
            ((UIContainerComponent) this.dialogManager.currentDialog.getChildById(125)).primaryListComponent.setDisplayMode(1);
        } else {
            ((UIContainerComponent) this.dialogManager.currentDialog.getChildById(125)).primaryListComponent.setDisplayMode(0);
        }

        ((UIContainerComponent) this.dialogManager.currentDialog
                .getChildById(125)).primaryListComponent.totalItemCount = this.player.skills.size();
        this.listScrollOffset = ((UIContainerComponent) this.dialogManager.currentDialog
                .getChildById(125)).primaryListComponent.scrollOffset;
        this.listSelectedIndex = ((UIContainerComponent) this.dialogManager.currentDialog
                .getChildById(125)).primaryListComponent.selectedIndex;

        int var1;
        for (var1 = 0; var1 < 5; ++var1) {
            if (this.listScrollOffset + var1 < this.player.skills.size()) {
                int[] var2 = (int[]) this.player.skills.elementAt(this.listScrollOffset + var1);
                if (this.dialogManager.currentDialog.getChildById(137 + var1 * 5).getComponentData().renderer == null) {
                    this.dialogManager.currentDialog.getChildById(137 + var1 * 5)
                            .getComponentData().renderer = new SpriteRenderer();
                    this.dialogManager.currentDialog.getChildById(137 + var1 * 5).getComponentData().renderer
                            .setSpriteIndex((int) 0);
                    this.dialogManager.currentDialog.getChildById(137 + var1 * 5).getComponentData().renderer.spriteType = 2;
                    this.dialogManager.currentDialog.getChildById(137 + var1 * 5).getComponentData().renderer.initializeSprite(258,
                            false, (byte) -1);
                }

                if (this.dialogManager.currentDialog.getChildById(137 + var1 * 5).getComponentData().focusedRenderer == null) {
                    this.dialogManager.currentDialog.getChildById(137 + var1 * 5)
                            .getComponentData().focusedRenderer = new SpriteRenderer();
                    this.dialogManager.currentDialog.getChildById(137 + var1 * 5).getComponentData().focusedRenderer
                            .setSpriteIndex((int) 0);
                    this.dialogManager.currentDialog.getChildById(137 + var1 * 5)
                            .getComponentData().focusedRenderer.spriteType = 2;
                    this.dialogManager.currentDialog.getChildById(137 + var1 * 5).getComponentData().focusedRenderer
                            .initializeSprite(258, false, (byte) -1);
                }

                this.dialogManager.currentDialog.getChildById(137 + var1 * 5).getComponentData().renderer
                        .setSpriteIndex((int) ResourceManager.gameDatabase[5][var2[0]][1]);
                this.dialogManager.currentDialog.getChildById(137 + var1 * 5).getComponentData().focusedRenderer
                        .setSpriteIndex((int) ResourceManager.gameDatabase[5][var2[0]][1]);
                this.dialogManager.currentDialog.getChildById(138 + var1 * 5).getComponentData().text = GameEngineBase
                        .getLocalizedText((int) ResourceManager.gameDatabase[5][var2[0]][0]);
                switch (var2[0]) {
                    case 0:
                        if (this.player.isSkillActive(var2[0])) {
                            this.dialogManager.currentDialog.getChildById(163).getComponentData().text = GameEngineBase
                                    .getLocalizedText((int) ResourceManager.gameDatabase[5][var2[0]][2]);
                            if (WorldGameSession.getInstance().canProduceEgg()) {
                                this.dialogManager.currentDialog.getChildById(139 + var1 * 5).getComponentData().text = "Hoàn thành";
                            } else {
                                this.dialogManager.currentDialog.getChildById(139 + var1 * 5).getComponentData().text = "1 cái";
                            }
                        } else {
                            this.dialogManager.currentDialog.getChildById(163).getComponentData().text = GameEngineBase
                                    .getLocalizedText((int) 634);
                            this.dialogManager.currentDialog.getChildById(139 + var1 * 5).getComponentData().text = "0 cái";
                        }
                        break;
                    default:
                        this.dialogManager.currentDialog.getChildById(139 + var1 * 5).getComponentData().text = "";
                }
            } else {
                if (this.dialogManager.currentDialog.getChildById(137 + var1 * 5).getComponentData().renderer != null) {
                    this.dialogManager.currentDialog.getChildById(137 + var1 * 5).getComponentData().renderer.cleanup();
                }

                this.dialogManager.currentDialog.getChildById(138 + var1 * 5).getComponentData().text = "";
                this.dialogManager.currentDialog.getChildById(139 + var1 * 5).getComponentData().text = "";
            }
        }

        if (this.player.skills.size() > 0) {
            if ((var1 = ((int[]) this.player.skills.elementAt(this.listSelectedIndex))[0]) != 0) {
                this.dialogManager.currentDialog.getChildById(163).getComponentData().text = GameEngineBase
                        .getLocalizedText((int) ResourceManager.gameDatabase[5][var1][2]);
                this.dialogManager.currentDialog.getChildById(7).setVisible(true);
            }

            if (var1 == 0) {
                if (((int[]) this.player.skills.elementAt(this.listSelectedIndex))[1] == 1) {
                    this.dialogManager.currentDialog.getChildById(7).getComponentData().text = "Đóng cửa";
                } else {
                    this.dialogManager.currentDialog.getChildById(7).getComponentData().text = "Mở ra";
                }
            } else if (var1 <= 0 && var1 > 4) {
                if (var1 == 10) {
                    this.dialogManager.currentDialog.getChildById(7).getComponentData().text = "Gia tốc";
                } else {
                    this.dialogManager.currentDialog.getChildById(7).getComponentData().text = "Sử dụng";
                }
            } else if (this.player.currentVehicleType == var1 - 1) {
                this.dialogManager.currentDialog.getChildById(7).getComponentData().text = "Triệu hồi";
            } else {
                this.dialogManager.currentDialog.getChildById(7).getComponentData().text = "Triệu hoán";
            }
        } else {
            this.dialogManager.currentDialog.getChildById(163).getComponentData().text = "";
            this.dialogManager.currentDialog.getChildById(7).setVisible(false);
        }

        if (this.player.skills.size() > 0) {
            this.dialogManager.currentDialog.getChildById(162).setOffsetY(127 + this.listSelectedIndex * 72 / this.player.skills.size(),
                    this.dialogManager.currentDialog.getUIContainerComponent());
        } else {
            this.dialogManager.currentDialog.getChildById(162).setOffsetY(127,
                    this.dialogManager.currentDialog.getUIContainerComponent());
        }
    }

    public final void ac() {
        if (this.dialogState == 0 && this.gameEngine.isKeyPressed(4100)) {
            this.dialogManager.currentDialog.handleAction(0);
            this.updatePokemonDetailDisplay(this.subMenuIndex);
        } else if (this.dialogState == 0 && this.gameEngine.isKeyPressed(8448)) {
            this.dialogManager.currentDialog.handleAction(1);
            this.updatePokemonDetailDisplay(this.subMenuIndex);
        } else if (this.gameEngine.isKeyPressed(196640)) {
            this.bx();
        } else {
            if (this.dialogState == 0 && this.gameEngine.isKeyPressed(262144)) {
                this.gameEngine.changeState((byte) 8);
                this.dialogManager.removeDialog("/data/ui/petstate.ui");
            }

        }
    }

    public final void ad() {
        if (this.gameEngine.isKeyPressed(4100)) {
            this.dialogManager.currentDialog.handleAction(0);
            this.updatePokemonDetailDisplay(this.menuIndex);
        } else if (this.gameEngine.isKeyPressed(8448)) {
            this.dialogManager.currentDialog.handleAction(1);
            this.updatePokemonDetailDisplay(this.menuIndex);
        } else if (this.gameEngine.isKeyPressed(196640)) {
            this.player.assignSkillToPokemon(this.scrollOffset, this.menuIndex);
            this.gameEngine.changeState((byte) 8);
        } else {
            if (this.gameEngine.isKeyPressed(262144)) {
                this.gameEngine.changeState((byte) 8);
                this.dialogManager.removeDialog("/data/ui/petstate.ui");
            }

        }
    }

    public final void ae() {
        if (this.gameEngine.isKeyPressed(4100)) {
            this.dialogManager.currentDialog.handleAction(0);
            this.updatePokemonDetailDisplay(this.menuIndex);
        } else if (this.gameEngine.isKeyPressed(8448)) {
            this.dialogManager.currentDialog.handleAction(1);
            this.updatePokemonDetailDisplay(this.menuIndex);
        } else {
            if (this.gameEngine.isKeyPressed(196640)) {
                if (this.dialogState == 0) {
                    if (this.player.partyPokemon[this.menuIndex].getLevel() < 50) {
                        this.dialogManager.showDialog("/data/ui/msgwarm.ui", 257, this);
                        this.setWarningText("Chỉ có thể cho 50 cấp sủng vật sử dụng", "Nhấn nút 5 tiếp tục");
                        this.dialogState = 2;
                        return;
                    }

                    if (this.player.useSkillOnPokemon(this.scrollOffset, this.menuIndex)) {
                        this.dialogManager.showDialog("/data/ui/msgwarm.ui", 257, this);
                        this.setWarningText("Sử dụng thành công", "Nhấn nút 5 tiếp tục");
                        this.dialogState = 1;
                        return;
                    }
                } else {
                    if (this.dialogState == 1) {
                        this.dialogState = 0;
                        this.gameEngine.changeState((byte) 8);
                        this.dialogManager.removeDialog("/data/ui/msgwarm.ui");
                        this.dialogManager.removeDialog("/data/ui/petstate.ui");
                        return;
                    }

                    if (this.dialogState == 2) {
                        this.dialogState = 0;
                        this.dialogManager.removeDialog("/data/ui/msgwarm.ui");
                        return;
                    }
                }
            } else if (this.gameEngine.isKeyPressed(262144) && this.dialogState == 0) {
                this.gameEngine.changeState((byte) 8);
                this.dialogManager.removeDialog("/data/ui/petstate.ui");
            }

        }
    }

    public final void updateEvolutionMenu() {
        this.gameEngine.updateActionSequence();
        if (this.dialogState == 0 && this.gameEngine.isKeyPressed(16400) && !this.isPopupActive()
                && !GameEngineBase.isActionBlocked((int) this.menuIndex, (int) 1)) {
            this.dialogManager.currentDialog.handleAction(7);
            this.dialogManager.currentDialog.handleAction(2);
            this.dialogManager.currentDialog.handleAction(5);
            this.bq();
            this.gameEngine.handleActionResponse();
        } else if (this.dialogState == 0 && this.gameEngine.isKeyPressed(32832) && !this.isPopupActive()
                && !GameEngineBase.isActionBlocked((int) this.menuIndex, (int) 1)) {
            this.dialogManager.currentDialog.handleAction(7);
            this.dialogManager.currentDialog.handleAction(3);
            this.dialogManager.currentDialog.handleAction(5);
            this.bq();
            this.gameEngine.handleActionResponse();
        } else if (this.dialogState == 0 && this.gameEngine.isKeyPressed(4100) && !this.isPopupActive()
                && !GameEngineBase.isActionBlocked((int) this.listSelectedIndex, (int) 0)) {
            this.dialogManager.currentDialog.handleAction(0);
        } else if (this.dialogState == 0 && this.gameEngine.isKeyPressed(8448) && !this.isPopupActive()
                && !GameEngineBase.isActionBlocked((int) this.listSelectedIndex, (int) 0)) {
            this.dialogManager.currentDialog.handleAction(1);
        } else if (this.gameEngine.isKeyPressed(196640) && !this.isPopupActive() && GameEngineBase.isConfirmAllowed()) {
            int var5;
            if (this.dialogState == 0) {
                if (GameEngineBase.isActionActive() && !GameEngineBase.isActionBlocked((int) this.listSelectedIndex, (int) 0)) {
                    return;
                }

                int[] var1;
                label206:
                switch (this.menuIndex) {
                    case 0:
                        if (this.listSelectedIndex >= this.player.consumableInventory.size()) {
                            if (this.player.equipmentInventory.size() <= 0) {
                                return;
                            }

                            var1 = (int[]) this.player.equipmentInventory.elementAt(this.listSelectedIndex - this.player.consumableInventory.size());
                        } else {
                            var1 = (int[]) this.player.consumableInventory.elementAt(this.listSelectedIndex);
                        }

                        switch (var1[0]) {
                            case 0:
                            case 1:
                            case 2:
                            case 3:
                                if (this.dialogState == 0) {
                                    this.dialogManager.showDialog("/data/ui/msgwarm.ui", 257, this);
                                    this.setWarningText("Không thể sử dụng", "Nhấn nút 5 tiếp tục");
                                    this.dialogState = 1;
                                } else {
                                    this.dialogManager.removeDialog("/data/ui/msgwarm.ui");
                                    this.dialogState = 0;
                                }
                                break label206;
                            case 4:
                            case 5:
                            case 6:
                            case 7:
                            case 8:
                            case 9:
                            case 10:
                            case 11:
                            case 12:
                            default:
                                this.scrollOffset = var1[0];
                                this.gameEngine.changeState((byte) 17);
                                this.dialogManager.removeDialog("/data/ui/bag.ui");
                                break label206;
                            case 13:
                                if (this.dialogState == 0) {
                                    if (this.player.brightnessEffectDuration <= 0) {
                                        if (WorldGameSession.getInstance().currentRegionId == 3 && WorldGameSession.getInstance().currentAreaId == 7) {
                                            this.showWarningDialog();
                                            this.setWarningText("Nơi này không cách nào sử dụng tránh quái hoàn", "Nhấn nút 5 tiếp tục");
                                            this.dialogState = 1;
                                        } else if (this.player.hasItem((int) var1[0], (int) 1, (byte) 0)) {
                                            this.player.removeItem(var1[0], 1, (byte) 0);
                                            this.player.brightnessEffectDuration = ResourceManager.gameDatabase[4][var1[0]][6];
                                            this.player.effectDuration = 0;
                                            var5 = this.player.consumableInventory.size() + this.player.equipmentInventory.size();
                                            if (this.listSelectedIndex >= var5) {
                                                this.listSelectedIndex = var5 - 1;
                                                ((UIContainerComponent) this.dialogManager.currentDialog
                                                        .getChildById(8)).primaryListComponent.selectedIndex = this.listSelectedIndex;
                                            }

                                            if (this.listScrollOffset > 0 && this.listSelectedIndex - this.listScrollOffset < 4) {
                                                --this.listScrollOffset;
                                                ((UIContainerComponent) this.dialogManager.currentDialog
                                                        .getChildById(8)).primaryListComponent.scrollOffset = this.listScrollOffset;
                                            }

                                            this.bs();
                                            this.showWarningDialog();
                                            this.player.applyBrightnessEffect(1);
                                            this.setWarningText("Thành công sử dụng đạo cụ, cũng có thời gian ngắn tránh quái hiệu quả",
                                                    "Nhấn nút 5 tiếp tục");
                                            this.dialogState = 1;
                                        }
                                    } else {
                                        this.showWarningDialog();
                                        this.setWarningText("Đã có được thời gian ngắn tránh quái hiệu quả", "Nhấn nút 5 tiếp tục");
                                        this.dialogState = 1;
                                    }
                                }
                                break label206;
                            case 14:
                                if (this.dialogState == 0) {
                                    if (!this.player.isSkillActive(0) || (this.player.pokedexCount != 0 || WorldGameSession.totalBattleCount >= 10)
                                            && (this.player.pokedexCount <= 0 || WorldGameSession.totalBattleCount >= 30)) {
                                        this.dialogManager.showDialog("/data/ui/msgwarm.ui", 257, this);
                                        this.setWarningText("Không có trứng có thể ấp trứng", "Nhấn nút 5 tiếp tục");
                                        this.dialogState = 1;
                                    } else if (this.player.hasItem((int) var1[0], (int) 1, (byte) 0)) {
                                        if (this.player.pokedexCount == 0) {
                                            WorldGameSession.totalBattleCount = 10;
                                        } else {
                                            WorldGameSession.totalBattleCount = 30;
                                        }

                                        this.player.removeItem(var1[0], 1, (byte) 0);
                                        var5 = this.player.consumableInventory.size() + this.player.equipmentInventory.size();
                                        if (this.listSelectedIndex >= var5) {
                                            this.listSelectedIndex = var5 - 1;
                                            ((UIContainerComponent) this.dialogManager.currentDialog
                                                    .getChildById(8)).primaryListComponent.selectedIndex = this.listSelectedIndex;
                                        }

                                        if (this.listScrollOffset > 0 && this.listSelectedIndex - this.listScrollOffset < 4) {
                                            --this.listScrollOffset;
                                            ((UIContainerComponent) this.dialogManager.currentDialog
                                                    .getChildById(8)).primaryListComponent.scrollOffset = this.listScrollOffset;
                                        }

                                        this.bs();
                                        this.dialogManager.showDialog("/data/ui/msgwarm.ui", 257, this);
                                        this.setWarningText("Thành công sử dụng, tranh thủ thời gian đi ấp trứng trứng sủng vật a!",
                                                "Nhấn nút 5 tiếp tục");
                                        this.dialogState = 1;
                                    }
                                }
                                break label206;
                        }
                    case 3:
                        switch ((var1 = (int[]) this.player.skills.elementAt(this.listSelectedIndex))[0]) {
                            case 0:
                                if (this.player.isSkillActive(var1[0])) {
                                    if (WorldGameSession.getInstance().canProduceEgg()) {
                                        if (this.player.getStorageStatus() == 2) {
                                            this.showWarningDialog();
                                            this.setWarningText("Không gian không đủ, hãy thanh lý lại không gian ấp trứng",
                                                    "Nhấn nút 5 tiếp tục");
                                            this.dialogState = 1;
                                        } else {
                                            WorldGameSession.totalBattleCount = 0;
                                            if (WorldGameSession.getInstance().questManager.questStates[WorldGameSession.getAreaIndex(4, 5)] != null) {
                                                WorldGameSession.getInstance().questManager.questStates[WorldGameSession.getAreaIndex(4, 5)][15] = 4;
                                                if (WorldGameSession.getInstance().currentRegionId == 4 && WorldGameSession.getInstance().currentAreaId == 5) {
                                                    WorldGameSession.getInstance().questManager.eventScripts[15].setExecutionState((byte) 4);
                                                }
                                            }

                                            this.player.resetSkillUsage(var1[0]);
                                            this.bu();
                                            this.showWarningDialog();
                                            this.setWarningText("Ấp trứng thành công", "Nhấn nút 5 tiếp tục");
                                            this.dialogState = 2;
                                        }
                                    } else {
                                        this.showWarningDialog();
                                        this.setWarningText("Vẫn chưa thể ấp trứng", "Nhấn nút 5 tiếp tục");
                                        this.dialogState = 1;
                                    }
                                }
                            case 1:
                            case 2:
                            case 3:
                            case 4:
                            default:
                                break;
                            case 5:
                                this.gameEngine.changeState((byte) 11);
                                this.dialogManager.removeDialog("/data/ui/bag.ui");
                                break;
                            case 6:
                                this.gameEngine.changeState((byte) 12);
                                this.dialogManager.removeDialog("/data/ui/bag.ui");
                                break;
                            case 7:
                            case 8:
                            case 9:
                                this.scrollOffset = var1[0];
                                this.gameEngine.changeState((byte) 19);
                                this.dialogManager.removeDialog("/data/ui/bag.ui");
                                break;
                            case 10:
                                this.gameEngine.changeState((byte) 24);
                                this.dialogManager.removeDialog("/data/ui/bag.ui");
                        }
                }
            } else if (this.dialogState == 1 || this.dialogState == 2) {
                if (this.dialogState != 2) {
                    this.gameEngine.handleActionResponse();
                    this.dialogState = 0;
                } else {
                    if (this.player.pokedexCount == 0) {
                        byte var6 = this.h(58);
                        this.player.addToPokedex((short) 58);
                        if (var6 == 0) {
                            this.showTutorialHint("Ấp trứng tìm được #2"
                                    + GameEngineBase.getLocalizedText((int) ResourceManager.gameDatabase[0][58][0])
                                    + "#0 để vào ba lô");
                        } else if (var6 == 1) {
                            this.showTutorialHint("Ấp trứng tìm được #2"
                                    + GameEngineBase.getLocalizedText((int) ResourceManager.gameDatabase[0][58][0])
                                    + "#0 để vào ngân hàng");
                        } else {
                            this.showTutorialHint("Không có không gian, đã phóng sinh");
                        }
                    } else {
                        var5 = GameUtils.findFirstGreaterOrEqual(new int[]{76, 52, 28, 4, 0}, GameUtils.getRandomInt(100));
                        short[] var2 = new short[]{0, 56, 58, 95, 72};
                        byte var3 = this.h(var2[var5]);

                        int var4;
                        for (var4 = 0; var4 < this.player.pokedexCount && this.player.pokedexEntries[var4] != var2[var5]; ++var4) {
                        }

                        if (var4 >= this.player.pokedexCount) {
                            this.player.addToPokedex(var2[var5]);
                        }

                        if (var3 == 0) {
                            this.showTutorialHint("Ấp trứng tìm được #2"
                                    + GameEngineBase.getLocalizedText((int) ResourceManager.gameDatabase[0][var2[var5]][0])
                                    + "#0 để vào ba lô");
                        } else if (var3 == 1) {
                            this.showTutorialHint("Ấp trứng tìm được #2"
                                    + GameEngineBase.getLocalizedText((int) ResourceManager.gameDatabase[0][var2[var5]][0])
                                    + "#0 để vào ngân hàng");
                        } else {
                            this.showTutorialHint("Không có không gian, đã phóng sinh");
                        }
                    }

                    this.dialogState = 3;
                }

                this.hideWarningDialog();
            }
        } else if (this.dialogState == 0 && this.gameEngine.isKeyPressed(262144) && !this.isPopupActive()
                && GameEngineBase.isCancelAllowed()) {
            if (GameEngineBase.paymentActive) {
                this.menuIndex = 2;
            } else {
                this.menuIndex = 1;
            }

            this.gameEngine.changeState((byte) 6);
            this.dialogManager.removeDialog("/data/ui/bag.ui");
        }

        if (this.dialogState == 3 && !this.isPopupActive()) {
            this.gameEngine.handleActionResponse();
            this.updateInventoryDisplay();
            this.dialogState = 0;
        }

        this.handleOpenBoxDialog();
        this.needsRedraw = true;
    }

    private byte h(int var1) {
        int[][] var2 = new int[][]{{60, 20, 0}, {75, 50, 20, 0}};
        byte var3 = -1;
        byte var4 = 0;
        if (ResourceManager.gameDatabase[0][var1][4] == 5) {
            if (ResourceManager.gameDatabase[0][var1][3] == 2) {
                var3 = 1;
                var4 = 2;
            } else if (ResourceManager.gameDatabase[0][var1][3] == 3) {
                var3 = 0;
                var4 = 3;
            }
        }

        int var5 = ResourceManager.gameDatabase[0][var1][1] * 10;
        short var6 = ResourceManager.gameDatabase[1][var5][5];
        byte var7 = this.player.getStorageStatus();
        if (var3 == -1) {
            if (var7 == 0) {
                this.player.createPokemon(var1, 5, (short) -1, (byte) 2, (short) -1, (byte) -1, new int[]{1, var5, var6});
            } else if (var7 == 1) {
                int var8 = GameUtils.getRandomInRange(ResourceManager.gameDatabase[0][var1][3],
                        ResourceManager.gameDatabase[0][var1][3]);
                this.player.addPokemonToStorage(var1, 5, (short) -1, (byte) 2, (byte) var8, (byte) -1, PokemonEntity.calculateMaxHp(var1, 5, var8), 0, -1,
                        new int[]{1, var5, var6});
            }
        } else {
            byte var9 = (byte) (var4 + (byte) GameUtils.findFirstGreaterOrEqual(var2[var3], GameUtils.getRandomInt(100)));
            if (var7 == 0) {
                this.player.createPokemon(var1, 5, (short) -1, (byte) 2, (short) var9, (byte) -1, new int[]{1, var5, var6});
            } else if (var7 == 1) {
                this.player.addPokemonToStorage(var1, 5, (short) -1, (byte) 2, (short) var9, (byte) -1, PokemonEntity.calculateMaxHp(var1, 5, var9), 0,
                        -1, new int[]{1, var5, var6});
            }
        }

        return var7;
    }

    public final void showEvolutionMenu() {
        this.hideWorldUIElements();
        this.dialogManager.showDialog("/data/ui/ride.ui", 257, this);
        this.menuIndex = 0;
        this.updateVehicleMenu();
    }

    private void updateVehicleMenu() {
        for (int var1 = 0; var1 < 4; ++var1) {
            if (this.dialogManager.currentDialog.getChildById(var1 + 4).getComponentData().renderer == null) {
                this.dialogManager.currentDialog.getChildById(var1 + 4).getComponentData().renderer = new SpriteRenderer();
                this.dialogManager.currentDialog.getChildById(var1 + 4).getComponentData().renderer.setSpriteIndex((int) 0);
                this.dialogManager.currentDialog.getChildById(var1 + 4).getComponentData().renderer.spriteType = 3;
                this.dialogManager.currentDialog.getChildById(var1 + 4).getComponentData().renderer.initializeSprite(260, false,
                        (byte) -1);
            }

            if (this.dialogManager.currentDialog.getChildById(var1 + 16).getComponentData().renderer == null) {
                this.dialogManager.currentDialog.getChildById(var1 + 16).getComponentData().renderer = new SpriteRenderer();
                this.dialogManager.currentDialog.getChildById(var1 + 16).getComponentData().renderer.setSpriteIndex((int) 131);
                this.dialogManager.currentDialog.getChildById(var1 + 16).getComponentData().renderer.spriteType = 2;
                this.dialogManager.currentDialog.getChildById(var1 + 16).getComponentData().renderer.initializeSprite(257, false,
                        (byte) 0);
            }

            if (this.player.hasVehicle(var1)) {
                if (this.menuIndex == var1) {
                    this.dialogManager.currentDialog.getChildById(var1 + 4).getComponentData().renderer
                            .setAnimationFrame((byte) var1, (byte) -1);
                    if (this.menuIndex == 0) {
                        this.dialogManager.currentDialog.getChildById(var1 + 8).getComponentData().text = "Lục đi điểu";
                    } else if (this.menuIndex == 1) {
                        this.dialogManager.currentDialog.getChildById(var1 + 8).getComponentData().text = "Hư không hành giả";
                    } else if (this.menuIndex == 2) {
                        this.dialogManager.currentDialog.getChildById(var1 + 8).getComponentData().text = "Hải âu";
                    } else if (this.menuIndex == 3) {
                        this.dialogManager.currentDialog.getChildById(var1 + 8).getComponentData().text = "Nham sơn long";
                    }
                } else {
                    this.dialogManager.currentDialog.getChildById(var1 + 4).getComponentData().renderer
                            .setAnimationFrame((byte) (var1 + 8), (byte) -1);
                    this.dialogManager.currentDialog.getChildById(var1 + 8).getComponentData().text = "";
                }

                if (!this.player.isVehicleUsable(var1)) {
                    this.dialogManager.currentDialog.getChildById(var1 + 16).setVisible(true);
                } else {
                    this.dialogManager.currentDialog.getChildById(var1 + 16).setVisible(false);
                }
            } else {
                this.dialogManager.currentDialog.getChildById(var1 + 16).setVisible(false);
                this.dialogManager.currentDialog.getChildById(var1 + 4).getComponentData().renderer
                        .setAnimationFrame((byte) (var1 + 4), (byte) -1);
                this.dialogManager.currentDialog.getChildById(var1 + 8).getComponentData().text = "";
            }
        }

    }

    public final void updateEvolutionProcess() {
        if (!this.isPopupActive() && this.gameEngine.isKeyPressed(16400)) {
            this.dialogManager.currentDialog.handleAction(2);
        } else if (!this.isPopupActive() && this.gameEngine.isKeyPressed(32832)) {
            this.dialogManager.currentDialog.handleAction(3);
        } else if (!this.isPopupActive() && this.gameEngine.isKeyPressed(512)) {
            this.dialogManager.removeDialog("/data/ui/ride.ui");
            this.gameEngine.changeState((byte) 0);
        } else if (!this.isPopupActive() && this.gameEngine.isKeyPressed(196640)) {
            if (this.player.hasVehicle(this.menuIndex)) {
                if (this.player.isVehicleUsable(this.menuIndex)) {
                    this.player.activateVehicle(this.menuIndex);
                    this.dialogManager.removeDialog("/data/ui/ride.ui");
                    this.gameEngine.changeState((byte) 0);
                } else {
                    this.showQuickMessage("Nơi này không thể sử dụng sủng vật cưỡi");
                }
            } else {
                this.showQuickMessage("Chưa có sủng vật cưỡi này");
            }
        } else if (!this.isPopupActive() && this.gameEngine.isKeyPressed(262144)) {
            this.dialogManager.removeDialog("/data/ui/ride.ui");
            this.gameEngine.changeState((byte) 0);
        }

        this.handleOpenBoxDialog();
        this.needsRedraw = true;
    }

    public final void initBattleUI(PokemonEntity var1, PokemonEntity var2) {
        this.dialogManager.showDialog("/data/ui/battle.ui", 257, this);
        this.tabIndex = 0;
        this.battleMenuIndex = 0;
        this.a(var1, false);
        this.b(var2, false);
        this.dialogManager.currentDialog.getChildById(59).getComponentData().text = "100%";
        this.dialogManager.currentDialog.getChildById(58).getComponentData().text = "100%";
        ((BattleSystemManager) this.gameEngine).D();
        this.dialogManager.removeDialog("/data/ui/world.ui");
    }

    public final void updateTypeAdvantageDisplay(PokemonEntity var1, PokemonEntity var2) {
        if (var1.getTypeAdvantage(var2) == 0) {
            if (var1.getBattlePosition() == 0) {
                this.dialogManager.currentDialog.getChildById(59).getComponentData().text = "300%";
                this.dialogManager.currentDialog.getChildById(58).getComponentData().text = "60%";
            } else {
                this.dialogManager.currentDialog.getChildById(59).getComponentData().text = "60%";
                this.dialogManager.currentDialog.getChildById(58).getComponentData().text = "300%";
            }
        } else if (var1.getTypeAdvantage(var2) == 1) {
            if (var1.getBattlePosition() == 0) {
                this.dialogManager.currentDialog.getChildById(59).getComponentData().text = "60%";
                this.dialogManager.currentDialog.getChildById(58).getComponentData().text = "300%";
            } else {
                this.dialogManager.currentDialog.getChildById(59).getComponentData().text = "300%";
                this.dialogManager.currentDialog.getChildById(58).getComponentData().text = "60%";
            }
        } else {
            this.dialogManager.currentDialog.getChildById(59).getComponentData().text = "100%";
            this.dialogManager.currentDialog.getChildById(58).getComponentData().text = "100%";
        }
    }

    public final void updateBattleAdvantageProgress(PokemonEntity var1, PokemonEntity var2, PokemonEntity var3, int var4, int var5) {
        if (var1.getTypeAdvantage(var2) == 0) {
            if (var3.getBattlePosition() == 0) {
                if ((var4 *= 200 / var5) == var5 && var4 != 200) {
                    var4 = 200;
                }

                this.dialogManager.currentDialog.getChildById(59).getComponentData().text = var4 + 100 + "%";
                return;
            }

            if (var3.getBattlePosition() == 1) {
                if ((var4 *= 40 / var5) == var5 && var4 != 40) {
                    var4 = 40;
                }

                this.dialogManager.currentDialog.getChildById(58).getComponentData().text = 100 - var4 + "%";
                return;
            }
        } else if (var1.getTypeAdvantage(var2) == 1) {
            if (var3.getBattlePosition() == 0) {
                if ((var4 *= 40 / var5) == var5 && var4 != 40) {
                    var4 = 40;
                }

                this.dialogManager.currentDialog.getChildById(59).getComponentData().text = 100 - var4 + "%";
                return;
            }

            if (var3.getBattlePosition() == 1) {
                if ((var4 *= 200 / var5) == var5 && var4 != 200) {
                    var4 = 200;
                }

                this.dialogManager.currentDialog.getChildById(58).getComponentData().text = var4 + 100 + "%";
                return;
            }
        } else {
            this.dialogManager.currentDialog.getChildById(59).getComponentData().text = "100%";
            this.dialogManager.currentDialog.getChildById(58).getComponentData().text = "100%";
        }

    }

    public final void updateBattleAdvantagePercent(PokemonEntity var1, PokemonEntity var2, int var3, int var4) {
        this.inventoryPage = this.inventoryOffset = 0;
        if (var1.getTypeAdvantage(var2) == 0) {
            this.inventoryPage += var3 * (200 / var4);
            if (this.inventoryPage == var4 && this.inventoryPage != 200) {
                this.inventoryPage = 200;
            }

            this.dialogManager.currentDialog.getChildById(59).getComponentData().text = 100 + this.inventoryPage + "%";
            this.inventoryOffset += var3 * (40 / var4);
            if (this.inventoryOffset == var4 && this.inventoryOffset != 40) {
                this.inventoryOffset = 40;
            }

            this.dialogManager.currentDialog.getChildById(58).getComponentData().text = 100 - this.inventoryOffset + "%";
        } else if (var1.getTypeAdvantage(var2) == 1) {
            this.inventoryPage += var3 * (40 / var4);
            if (this.inventoryPage == var4 && this.inventoryPage != 40) {
                this.inventoryPage = 40;
            }

            this.dialogManager.currentDialog.getChildById(59).getComponentData().text = 100 - this.inventoryPage + "%";
            this.inventoryOffset += var3 * (200 / var4);
            if (this.inventoryOffset == var4 && this.inventoryOffset != 200) {
                this.inventoryOffset = 200;
            }

            this.dialogManager.currentDialog.getChildById(58).getComponentData().text = 100 + this.inventoryOffset + "%";
        } else {
            this.dialogManager.currentDialog.getChildById(59).getComponentData().text = "100%";
            this.dialogManager.currentDialog.getChildById(58).getComponentData().text = "100%";
        }
    }

    public final boolean a(PokemonEntity var1, boolean var2) {
        int var3 = 0;
        byte var7;
        if (this.inventorySelectedIndex == 0) {
            int var10000 = var1.getDisplayHp();
            var7 = 1;
            if ((var3 = Math.abs(var10000 - var1.secondaryStates[var7]) / 11) <= 1) {
                var3 = 1;
            }
        }

        int var4 = var1.getDisplayHp();
        var7 = 1;
        short var5 = var1.secondaryStates[var7];
        if (var4 != var5) {
            ++this.inventoryScrollPos;
            if (this.inventoryScrollPos < 4) {
                if (var2) {
                    this.dialogManager.currentDialog.getChildById(55).getComponentData().text = "#P" + var1.getHpPercent();
                    this.dialogManager.currentDialog.getChildById(11).getComponentData().text = "#P" + var1.getDisplayHpPercent();
                } else {
                    this.dialogManager.currentDialog.getChildById(55).getComponentData().text = "#P" + var1.getDisplayHpPercent();
                    this.dialogManager.currentDialog.getChildById(11).getComponentData().text = "#P" + var1.getHpPercent();
                }

                return false;
            }
        }

        this.inventorySelectedIndex += var3;
        if (var2) {
            if ((var4 += this.inventorySelectedIndex) >= var5) {
                var4 = var5;
            }

            var1.setDisplayHp(var4);
            this.dialogManager.currentDialog.getChildById(41).getComponentData().text = "#P" + var1.getHpPercent();
            this.dialogManager.currentDialog.getChildById(11).getComponentData().text = "#P" + var1.getDisplayHpPercent();
            this.dialogManager.currentDialog.getChildById(55).getComponentData().text = "#P" + var1.getDisplayHpPercent();
        } else {
            if ((var4 -= this.inventorySelectedIndex) <= var5) {
                var4 = var5;
            }

            var1.setDisplayHp(var4);
            this.dialogManager.currentDialog.getChildById(41).getComponentData().text = "#P" + var1.getDisplayHpPercent();
            this.dialogManager.currentDialog.getChildById(55).getComponentData().text = "#P" + var1.getHpPercent();
            this.dialogManager.currentDialog.getChildById(11).getComponentData().text = "#P" + var1.getHpPercent();
        }

        ComponentData var8 = this.dialogManager.currentDialog.getChildById(38).getComponentData();
        StringBuffer var10001 = (new StringBuffer()).append(var1.getDisplayHp()).append("/");
        var7 = 1;
        var8.text = var10001.append(var1.primaryStates[var7]).toString();
        this.dialogManager.currentDialog.getChildById(9).getComponentData().text = "#P" + var1.getExpPercent();
        this.dialogManager.currentDialog.getChildById(40).getComponentData().text = var1.getCurrentExp() + "/" + var1.getExpToNextLevel();
        this.dialogManager.currentDialog.getChildById(12).getComponentData().text = GameEngineBase
                .getLocalizedText(var1.getDatabaseValue((byte) 0));
        this.dialogManager.currentDialog.getChildById(13).getComponentData().text = "lv" + var1.getLevel();
        this.dialogManager.currentDialog.getChildById(17).getComponentData().renderer.setSpriteIndex(94 + var1.getDatabaseValue((byte) 1));
        if (var4 == var5) {
            this.inventorySelectedIndex = 0;
            this.inventoryScrollPos = 0;
            this.itemQuantity = 0;
            return true;
        } else {
            return false;
        }
    }

    public final void initPlayerPokemonUI(PokemonEntity var1) {
        int var2;
        for (var2 = 0; var2 < 6; ++var2) {
            if (this.dialogManager.currentDialog.getChildById(var2 + 26).getComponentData().renderer == null) {
                this.dialogManager.currentDialog.getChildById(var2 + 26).getComponentData().renderer = new SpriteRenderer();
                this.dialogManager.currentDialog.getChildById(var2 + 26).getComponentData().renderer.spriteType = 2;
                this.dialogManager.currentDialog.getChildById(var2 + 26).getComponentData().renderer.setSpriteIndex((int) 0);
                this.dialogManager.currentDialog.getChildById(var2 + 26).getComponentData().renderer.initializeSprite(325, false,
                        (byte) 0);
            }

            if (this.dialogManager.currentDialog.getChildById(var2 + 43).getComponentData().renderer == null) {
                this.dialogManager.currentDialog.getChildById(var2 + 43).getComponentData().renderer = new SpriteRenderer();
                this.dialogManager.currentDialog.getChildById(var2 + 43).getComponentData().renderer.spriteType = 2;
                this.dialogManager.currentDialog.getChildById(var2 + 43).getComponentData().renderer.setSpriteIndex((int) 145);
                this.dialogManager.currentDialog.getChildById(var2 + 43).getComponentData().renderer.initializeSprite(257, false,
                        (byte) 0);
            }

            this.dialogManager.currentDialog.getChildById(var2 + 43).getComponentData().renderer.setSpriteIndex((int) 145);
            this.dialogManager.currentDialog.getChildById(var2 + 26).getComponentData().renderer.setSpriteIndex((int) 0);
        }

        for (var2 = 0; var2 < 3; ++var2) {
            short[] var10002;
            if (var1.activeStatusEffects[0][var2] != -1 && var1.buffs[var1.activeStatusEffects[0][var2]][0] > 0) {
                var10002 = var1.buffs[var1.activeStatusEffects[0][var2]];
                this.dialogManager.currentDialog.getChildById(43 + this.itemQuantity).getComponentData().renderer
                        .setSpriteIndex(134 + var10002[0]);
                this.dialogManager.currentDialog.getChildById(26 + this.itemQuantity).getComponentData().renderer
                        .setSpriteIndex(var1.activeStatusEffects[0][var2] + 12);
                ++this.itemQuantity;
            }

            if (var1.activeStatusEffects[1][var2] != -1 && var1.debuffs[var1.activeStatusEffects[1][var2]][0] > 0) {
                var10002 = var1.debuffs[var1.activeStatusEffects[1][var2]];
                this.dialogManager.currentDialog.getChildById(43 + this.itemQuantity).getComponentData().renderer
                        .setSpriteIndex(134 + var10002[0]);
                this.dialogManager.currentDialog.getChildById(26 + this.itemQuantity).getComponentData().renderer
                        .setSpriteIndex(var1.activeStatusEffects[1][var2] + 1);
                ++this.itemQuantity;
            }
        }

    }

    private void updatePokemonExpBar(PokemonEntity var1) {
        this.dialogManager.currentDialog.getChildById(11).getComponentData().text = "#P" + var1.getHpPercent();
        ComponentData var10000 = this.dialogManager.currentDialog.getChildById(38).getComponentData();
        StringBuffer var10001 = (new StringBuffer()).append(var1.getDisplayHp()).append("/");
        byte var3 = 1;
        var10000.text = var10001.append(var1.primaryStates[var3]).toString();
        this.dialogManager.currentDialog.getChildById(16).getComponentData().text = "lv" + var1.getLevel();
    }

    public final boolean b(PokemonEntity var1, boolean var2) {
        int var3 = 0;
        byte var7;
        if (this.inventorySelectedIndex == 0) {
            int var10000 = var1.getDisplayHp();
            var7 = 1;
            if ((var3 = Math.abs(var10000 - var1.secondaryStates[var7]) / 11) <= 1) {
                var3 = 1;
            }
        }

        int var4 = var1.getDisplayHp();
        var7 = 1;
        short var5 = var1.secondaryStates[var7];
        if (var4 != var5) {
            ++this.inventoryScrollPos;
            if (this.inventoryScrollPos < 4) {
                if (var2) {
                    this.dialogManager.currentDialog.getChildById(56).getComponentData().text = "#P" + var1.getHpPercent();
                    this.dialogManager.currentDialog.getChildById(14).getComponentData().text = "#P" + var1.getDisplayHpPercent();
                } else {
                    this.dialogManager.currentDialog.getChildById(56).getComponentData().text = "#P" + var1.getDisplayHpPercent();
                    this.dialogManager.currentDialog.getChildById(14).getComponentData().text = "#P" + var1.getHpPercent();
                }

                return false;
            }
        }

        this.inventorySelectedIndex += var3;
        if (var2) {
            if ((var4 += this.inventorySelectedIndex) >= var5) {
                var4 = var5;
            }

            var1.setDisplayHp(var4);
            this.dialogManager.currentDialog.getChildById(42).getComponentData().text = "#P" + var1.getHpPercent();
            this.dialogManager.currentDialog.getChildById(14).getComponentData().text = "#P" + var1.getDisplayHpPercent();
            this.dialogManager.currentDialog.getChildById(56).getComponentData().text = "#P" + var1.getDisplayHpPercent();
        } else {
            if ((var4 -= this.inventorySelectedIndex) <= var5) {
                var4 = var5;
            }

            var1.setDisplayHp(var4);
            this.dialogManager.currentDialog.getChildById(42).getComponentData().text = "#P" + var1.getDisplayHpPercent();
            this.dialogManager.currentDialog.getChildById(14).getComponentData().text = "#P" + var1.getHpPercent();
            this.dialogManager.currentDialog.getChildById(56).getComponentData().text = "#P" + var1.getHpPercent();
        }

        ComponentData var8 = this.dialogManager.currentDialog.getChildById(39).getComponentData();
        StringBuffer var10001 = (new StringBuffer()).append(var1.getDisplayHp()).append("/");
        var7 = 1;
        var8.text = var10001.append(var1.primaryStates[var7]).toString();
        if (this.player.getCreatureStatus((byte) var1.getDatabaseValue((byte) 1), var1.getSpeciesId()) == 2) {
            this.dialogManager.currentDialog.getChildById(19).getComponentData().renderer.setSpriteIndex((int) 101);
        } else {
            this.dialogManager.currentDialog.getChildById(19).getComponentData().renderer.setSpriteIndex((int) 102);
        }

        this.dialogManager.currentDialog.getChildById(15).getComponentData().text = GameEngineBase
                .getLocalizedText(var1.getDatabaseValue((byte) 0));
        this.dialogManager.currentDialog.getChildById(16).getComponentData().text = "lv" + var1.getLevel();
        this.dialogManager.currentDialog.getChildById(18).getComponentData().renderer.setSpriteIndex(94 + var1.getDatabaseValue((byte) 1));
        if (var4 == var5) {
            this.inventorySelectedIndex = 0;
            this.inventoryScrollPos = 0;
            this.itemQuantity = 0;
            return true;
        } else {
            return false;
        }
    }

    public final void initEnemyPokemonUI(PokemonEntity var1) {
        int var2;
        for (var2 = 0; var2 < 6; ++var2) {
            if (this.dialogManager.currentDialog.getChildById(var2 + 32).getComponentData().renderer == null) {
                this.dialogManager.currentDialog.getChildById(var2 + 32).getComponentData().renderer = new SpriteRenderer();
                this.dialogManager.currentDialog.getChildById(var2 + 32).getComponentData().renderer.spriteType = 2;
                this.dialogManager.currentDialog.getChildById(var2 + 32).getComponentData().renderer.setSpriteIndex((int) 0);
                this.dialogManager.currentDialog.getChildById(var2 + 32).getComponentData().renderer.initializeSprite(325, false,
                        (byte) 0);
            }

            if (this.dialogManager.currentDialog.getChildById(var2 + 49).getComponentData().renderer == null) {
                this.dialogManager.currentDialog.getChildById(var2 + 49).getComponentData().renderer = new SpriteRenderer();
                this.dialogManager.currentDialog.getChildById(var2 + 49).getComponentData().renderer.spriteType = 2;
                this.dialogManager.currentDialog.getChildById(var2 + 49).getComponentData().renderer.setSpriteIndex((int) 145);
                this.dialogManager.currentDialog.getChildById(var2 + 49).getComponentData().renderer.initializeSprite(257, false,
                        (byte) 0);
            }

            this.dialogManager.currentDialog.getChildById(var2 + 49).getComponentData().renderer.setSpriteIndex((int) 145);
            this.dialogManager.currentDialog.getChildById(var2 + 32).getComponentData().renderer.setSpriteIndex((int) 0);
        }

        for (var2 = 0; var2 < 3; ++var2) {
            short[] var10002;
            if (var1.activeStatusEffects[0][var2] != -1 && var1.buffs[var1.activeStatusEffects[0][var2]][0] > 0) {
                var10002 = var1.buffs[var1.activeStatusEffects[0][var2]];
                this.dialogManager.currentDialog.getChildById(49 + this.itemQuantity).getComponentData().renderer
                        .setSpriteIndex(134 + var10002[0]);
                this.dialogManager.currentDialog.getChildById(32 + this.itemQuantity).getComponentData().renderer
                        .setSpriteIndex(var1.activeStatusEffects[0][var2] + 12);
                ++this.itemQuantity;
            }

            if (var1.activeStatusEffects[1][var2] != -1 && var1.debuffs[var1.activeStatusEffects[1][var2]][0] > 0) {
                var10002 = var1.debuffs[var1.activeStatusEffects[1][var2]];
                this.dialogManager.currentDialog.getChildById(49 + this.itemQuantity).getComponentData().renderer
                        .setSpriteIndex(134 + var10002[0]);
                this.dialogManager.currentDialog.getChildById(32 + this.itemQuantity).getComponentData().renderer
                        .setSpriteIndex(var1.activeStatusEffects[1][var2] + 1);
                ++this.itemQuantity;
            }
        }

    }

    public final void ai() {
        this.tabIndex = 0;
        this.tempData1 = null;
        this.dialogManager.removeDialog("/data/ui/battle.ui");
    }

    public final void aj() {
        ((UIContainerComponent) this.dialogManager.currentDialog
                .getChildById(0)).secondaryListComponent.selectedIndex = this.tabIndex;
        this.dialogManager.currentDialog.getChildById(20 + this.tabIndex).setVisible(true);
    }

    public final void showPokemonCaptureUI(PokemonEntity var1) {
        this.dialogState = 0;
        this.a(var1, false);
        this.aj();
    }

    public final void updatePokemonCaptureUI(PokemonEntity var1) {
        ((BattleSystemManager) this.gameEngine).q();
        if (!GameEngineBase.isActionBlocked((int) this.tabIndex, (int) 1) && this.dialogState == 0 && !this.isPopupActive()
                && this.gameEngine.isKeyPressed(16400)) {
            this.dialogManager.currentDialog.handleAction(2);
        } else if (!GameEngineBase.isActionBlocked((int) this.tabIndex, (int) 1) && this.dialogState == 0 && !this.isPopupActive()
                && this.gameEngine.isKeyPressed(32832)) {
            this.dialogManager.currentDialog.handleAction(3);
        } else if (!this.isPopupActive() && this.gameEngine.isKeyPressed(196640)) {
            switch (this.tabIndex) {
                case 0:
                    this.dialogManager.currentDialog.getChildById(20 + this.tabIndex).setVisible(false);
                    this.gameEngine.changeState((byte) 3);
                    break;
                case 1:
                    if (((BattleSystemManager) this.gameEngine).C29_f398 == 2) {
                        this.showQuickMessage("Trận chiến này không cho bắt sủng vật");
                    } else if (this.player.getStorageStatus() == 2) {
                        this.showQuickMessage("Không gian không đủ, không cách nào bắt được");
                    } else {
                        this.menuIndex = 0;
                        this.dialogManager.currentDialog.getChildById(20 + this.tabIndex).setVisible(false);
                        ((BattleSystemManager) this.gameEngine).r();
                        this.gameEngine.changeState((byte) 21);
                    }
                    break;
                case 2:
                    if (this.dialogState == 0) {
                        if (var1.hasDebuff(2)) {
                            this.dialogManager.showDialog("/data/ui/msgwarm.ui", 257, this);
                            this.setWarningText("Trạng thái bị quấn, không thể sử dụng đạo cụ", "Nhấn nút 5 tiếp tục");
                            this.dialogState = 1;
                        } else {
                            this.dialogManager.currentDialog.getChildById(20 + this.tabIndex).setVisible(false);
                            this.gameEngine.changeState((byte) 4);
                        }
                    } else {
                        this.dialogManager.removeDialog("/data/ui/msgwarm.ui");
                        this.dialogState = 0;
                    }
                    break;
                case 3:
                    if (this.dialogState == 0) {
                        if (var1.hasDebuff(2)) {
                            this.dialogManager.showDialog("/data/ui/msgwarm.ui", 257, this);
                            this.setWarningText("Trạng thái bị quấn, không thể đổi sủng vật", "Nhấn nút 5 tiếp tục");
                            this.dialogState = 1;
                        } else {
                            this.dialogManager.currentDialog.getChildById(20 + this.tabIndex).setVisible(false);
                            ((BattleSystemManager) this.gameEngine).C29_f407 = ((BattleSystemManager) this.gameEngine).C29_f404[((BattleSystemManager) this.gameEngine).C29_f410];
                            BattleSystemManager.B().C29_f415 = true;
                            this.gameEngine.changeState((byte) 5);
                        }
                    } else {
                        this.dialogManager.removeDialog("/data/ui/msgwarm.ui");
                        this.dialogState = 0;
                    }
                    break;
                case 4:
                    this.dialogManager.currentDialog.getChildById(20 + this.tabIndex).setVisible(false);
                    this.gameEngine.changeState((byte) 11);
                    break;
                case 5:
                    if (this.dialogState == 0) {
                        if (var1.hasDebuff(2)) {
                            this.dialogManager.showDialog("/data/ui/msgwarm.ui", 257, this);
                            this.setWarningText("Trạng thái bị quấn, không thể chạy trốn", "Nhấn nút 5 tiếp tục");
                            this.dialogState = 1;
                        } else if (((BattleSystemManager) this.gameEngine).C29_f398 <= 0 && QuestManager.isMapControl) {
                            boolean var3 = false;
                            if (((BattleSystemManager) this.gameEngine).C29_f408
                                    .getLevel() > ((BattleSystemManager) this.gameEngine).C29_f402[0].getLevel()) {
                                var3 = true;
                            } else if (((BattleSystemManager) this.gameEngine).C29_f408
                                    .getLevel() == ((BattleSystemManager) this.gameEngine).C29_f402[0].getLevel()) {
                                if (GameUtils.getRandomInt(100) <= 95) {
                                    var3 = true;
                                }
                            } else {
                                int var2 = ((BattleSystemManager) this.gameEngine).C29_f402[0].getLevel()
                                        - ((BattleSystemManager) this.gameEngine).C29_f408.getLevel();
                                if ((var2 = 95 - var2 * 10) <= 15) {
                                    var2 = 15;
                                }

                                if (GameUtils.getRandomInt(100) < var2) {
                                    var3 = true;
                                }
                            }

                            if (var3) {
                                this.dialogManager.currentDialog.getChildById(20 + this.tabIndex).setVisible(false);
                                GameScreenManager.getInstance().changeState((byte) 10);
                            } else {
                                this.dialogState = 2;
                                this.showQuickMessage("Chạy trốn thất bại");
                            }
                        } else {
                            this.dialogManager.currentDialog.getChildById(20 + this.tabIndex).setVisible(false);
                            this.dialogState = 3;
                            this.showQuickMessage("Trận chiến này không thể trốn chạy");
                        }
                    } else {
                        this.dialogManager.removeDialog("/data/ui/msgwarm.ui");
                        this.dialogState = 0;
                    }
            }
        }

        this.handleOpenBoxDialog();
        if (this.dialogState >= 2 && this.isMessageAnimationComplete()) {
            if (this.dialogState == 2) {
                ((BattleSystemManager) this.gameEngine).C29_f408.isInBattle = true;
                ++((BattleSystemManager) this.gameEngine).C29_f410;
                this.gameEngine.changeState((byte) 1);
            } else {
                this.dialogManager.currentDialog.getChildById(20 + this.tabIndex).setVisible(true);
            }

            this.dialogState = 0;
        }

    }

    public final void showPokemonStatusEffect(PokemonEntity var1) {
        this.dialogManager.showDialog("/data/ui/choiceskill.ui", 257, this);
        ((UIContainerComponent) this.dialogManager.currentDialog.getChildById(0)).primaryListComponent.totalItemCount = var1
                .getSkillCount();
        if (this.battleMenuIndex >= var1.getSkillCount()) {
            this.battleMenuIndex = var1.getSkillCount() - 1;
        }

        if (var1.getSkillCount() > 5) {
            ((UIContainerComponent) this.dialogManager.currentDialog.getChildById(0)).primaryListComponent.setDisplayMode(1);
        } else {
            ((UIContainerComponent) this.dialogManager.currentDialog.getChildById(0)).primaryListComponent.setDisplayMode(-1);
        }

        this.dialogManager.currentDialog.getChildById(5).getComponentData().text = "Sử dụng";
        ((UIContainerComponent) this.dialogManager.currentDialog
                .getChildById(0)).primaryListComponent.selectedIndex = this.battleMenuIndex;
        this.showLevelUpEffect(var1);
        this.dialogState = 0;
    }

    private void showLevelUpEffect(PokemonEntity var1) {
        this.listScrollOffset = ((UIContainerComponent) this.dialogManager.currentDialog
                .getChildById(0)).primaryListComponent.scrollOffset;
        this.listSelectedIndex = ((UIContainerComponent) this.dialogManager.currentDialog
                .getChildById(0)).primaryListComponent.selectedIndex;
        int var2 = var1.getSkillCount();

        for (int var3 = 0; var3 < 5; ++var3) {
            if (var3 >= var2) {
                this.dialogManager.currentDialog.getChildById(13 + var3 * 5).getComponentData().text = "";
                this.dialogManager.currentDialog.getChildById(14 + var3 * 5).getComponentData().text = "";
            } else {
                this.dialogManager.currentDialog.getChildById(13 + var3 * 5).getComponentData().text = GameEngineBase
                        .getLocalizedText((int) ResourceManager.gameDatabase[1][var1.getSkillAt(this.listScrollOffset + var3)][1]);
                this.dialogManager.currentDialog.getChildById(14 + var3 * 5)
                        .getComponentData().text = var1.skillPP[this.listScrollOffset + var3] + "/"
                        + ResourceManager.gameDatabase[1][var1.getSkillAt(this.listScrollOffset + var3)][5];
            }
        }

        this.playLevelUpSound(var1.skills[this.battleMenuIndex]);
        this.dialogManager.currentDialog.getChildById(51).setOffsetY(98 + this.listSelectedIndex * 80 / var2,
                this.dialogManager.currentDialog.getUIContainerComponent());
    }

    private void playLevelUpSound(int var1) {
        this.dialogManager.currentDialog.getChildById(53).getComponentData().text = GameEngineBase
                .getLocalizedText(ResourceManager.gameDatabase[1][var1][2]);
    }

    public final void updatePokemonStatusEffect(PokemonEntity var1) {
        if (this.canNavigateList()) {
            if (this.dialogState == 0 && this.gameEngine.isKeyPressed(4100)) {
                this.dialogManager.currentDialog.handleAction(0);
                this.showLevelUpEffect(var1);
            } else if (this.dialogState == 0 && this.gameEngine.isKeyPressed(8448)) {
                this.dialogManager.currentDialog.handleAction(1);
                this.showLevelUpEffect(var1);
            } else if (this.gameEngine.isKeyPressed(196640)) {
                if (this.dialogState == 0) {
                    if (var1.hasSkillPP(this.battleMenuIndex)) {
                        this.dialogManager.removeDialog("/data/ui/choiceskill.ui");
                        ((BattleSystemManager) this.gameEngine).d(var1.skills[this.battleMenuIndex]);
                        int var10000 = ((BattleSystemManager) this.gameEngine).C29_f397;
                        ((BattleSystemManager) this.gameEngine).getClass();
                        if (var10000 == 0) {
                            ((BattleSystemManager) this.gameEngine).G();
                        } else {
                            this.gameEngine.changeState((byte) 6);
                        }
                    } else {
                        this.dialogState = 1;
                        this.dialogManager.showDialog("/data/ui/msgwarm.ui", 257, this);
                        this.setWarningText("Kỹ năng giá trị chưa đủ", "Nhấn nút 5 tiếp tục");
                    }
                } else {
                    this.dialogState = 0;
                    this.dialogManager.removeDialog("/data/ui/msgwarm.ui");
                    if (var1.hasDebuff(2) && var1.getBattlePosition() == 0) {
                        boolean var2 = false;

                        for (int var3 = 0; var3 < var1.skillPP.length; ++var3) {
                            if (var1.skillPP[var3] != 0) {
                                var2 = true;
                            }
                        }

                        if (!var2) {
                            this.dialogManager.removeDialog("/data/ui/choiceskill.ui");
                            this.showTutorialHint("Không có kỹ năng giá trị, không cách nào chiến đấu");
                            ((BattleSystemManager) this.gameEngine).F();
                        }
                    }
                }
            } else if (this.gameEngine.isKeyPressed(262144) && this.dialogState == 0) {
                this.dialogManager.removeDialog("/data/ui/choiceskill.ui");
                this.gameEngine.changeState((byte) 20);
            }
        }

        this.handleTaskTipDialog();
    }

    public final void ak() {
        this.dialogState = 0;
        this.dialogManager.showDialog("/data/ui/choice.ui", 257, this);
        this.dialogManager.currentDialog.getChildById(8).getComponentData().text = "Pokemon ball";
        this.dialogManager.currentDialog.getChildById(9).getComponentData().text = "Tỉ lệ bắt";
        this.dialogManager.currentDialog.getChildById(5).getComponentData().text = "Sử dụng";
        ((UIContainerComponent) this.dialogManager.currentDialog
                .getChildById(0)).primaryListComponent.selectedIndex = this.menuIndex;
        ((UIContainerComponent) this.dialogManager.currentDialog.getChildById(0)).primaryListComponent.setDisplayMode(0);
        ((UIContainerComponent) this.dialogManager.currentDialog
                .getChildById(0)).primaryListComponent.totalItemCount = this.player.consumableInventory.size();

        for (int var1 = 0; var1 < this.player.consumableInventory.size(); ++var1) {
            int[] var2 = (int[]) this.player.consumableInventory.elementAt(var1);
            if (this.dialogManager.currentDialog.getChildById(var1 + 54).getComponentData().renderer == null) {
                this.dialogManager.currentDialog.getChildById(var1 + 54).getComponentData().renderer = new SpriteRenderer();
                this.dialogManager.currentDialog.getChildById(var1 + 54).getComponentData().renderer.setSpriteIndex((int) 0);
                this.dialogManager.currentDialog.getChildById(var1 + 54).getComponentData().renderer.spriteType = 2;
                this.dialogManager.currentDialog.getChildById(var1 + 54).getComponentData().renderer.initializeSprite(258, false,
                        (byte) -1);
            }

            this.dialogManager.currentDialog.getChildById(var1 + 54).getComponentData().renderer
                    .setSpriteIndex((int) ResourceManager.gameDatabase[4][var2[0]][1]);
            this.dialogManager.currentDialog.getChildById(13 + var1 * 5).getComponentData().text = GameEngineBase
                    .getLocalizedText((int) ResourceManager.gameDatabase[4][var2[0]][0]);
            this.dialogManager.currentDialog.getChildById(14 + var1 * 5)
                    .getComponentData().text = ((BattleSystemManager) this.gameEngine).m(var2[0]) + "%";
        }

        this.dialogManager.currentDialog.getChildById(59).setVisible(false);
        this.dialogManager.currentDialog.getChildById(60).setVisible(false);
        this.bw();
    }

    private void bw() {
        int[] var1 = (int[]) this.player.consumableInventory.elementAt(this.menuIndex);
        this.dialogManager.currentDialog.getChildById(53).getComponentData().text = "Số lượng: " + var1[1] + " cái ";
    }

    public final void al() {
        this.gameEngine.updateActionSequence();
        if (!GameEngineBase.isActionBlocked((int) this.menuIndex, (int) 0) && this.dialogState == 0
                && this.gameEngine.isKeyPressed(4100)
                && !this.isPopupActive()) {
            this.dialogManager.currentDialog.handleAction(0);
            this.bw();
        } else if (!GameEngineBase.isActionBlocked((int) this.menuIndex, (int) 0) && this.dialogState == 0
                && this.gameEngine.isKeyPressed(8448)
                && !this.isPopupActive()) {
            this.dialogManager.currentDialog.handleAction(1);
            this.bw();
        } else if (this.gameEngine.isKeyPressed(196640) && !this.isPopupActive() && GameEngineBase.isConfirmAllowed()) {
            if (GameEngineBase.isActionActive() && !GameEngineBase.isActionBlocked((int) this.menuIndex, (int) 0)) {
                return;
            }

            QuestManager.isSpecialState = true;
            if (this.dialogState == 0) {
                int[] var1 = (int[]) this.player.consumableInventory.elementAt(this.menuIndex);
                if (!this.player.hasItem((int) var1[0], (int) 1, (byte) 0)) {
                    this.dialogManager.showDialog("/data/ui/msgwarm.ui", 257, this);
                    this.setWarningText("Số lượng Pokemon ball không đủ", "Nhấn nút 5 tiếp tục");
                    this.dialogState = 1;
                } else {
                    this.dialogState = 0;
                    BattleSystemManager.C29_f444 = (byte) var1[0];
                    this.gameEngine.handleActionResponse();
                    this.player.removeItem(var1[0], 1, (byte) 0);
                    this.gameEngine.changeState((byte) 17);
                    this.dialogManager.removeDialog("/data/ui/choice.ui");
                }
            } else if (this.dialogState == 1) {
                if (GameEngineBase.paymentActive && ((int[]) this.player.consumableInventory.elementAt(this.menuIndex))[0] == 0) {
                    this.dialogManager.removeDialog("/data/ui/choice.ui");
                    this.gameEngine.changeState((byte) 101);
                }

                this.dialogState = 0;
                this.dialogManager.removeDialog("/data/ui/msgwarm.ui");
            }
        } else if (QuestManager.isCancelAllowed() && this.dialogState == 0 && this.gameEngine.isKeyPressed(262144)
                && !this.isPopupActive()) {
            this.dialogManager.removeDialog("/data/ui/choice.ui");
            this.gameEngine.changeState((byte) 20);
        }

        this.handleTaskTipDialog();
    }

    public final void am() {
        this.scrollOffset = 0;
        this.dialogState = 0;
        this.menuIndex = 0;
        this.dialogManager.showDialog("/data/ui/choice.ui", 257, this);
        this.dialogManager.currentDialog.getChildById(8).getComponentData().text = "Đạo cụ";
        this.dialogManager.currentDialog.getChildById(9).getComponentData().text = "Số lượng";
        this.dialogManager.currentDialog.getChildById(5).getComponentData().text = "Sử dụng";
        this.dialogManager.currentDialog.getChildById(59).setVisible(false);
        this.dialogManager.currentDialog.getChildById(60).setVisible(false);
        this.updateSkillDisplay();
    }

    public final void an() {
        if (this.dialogState == 0 && this.gameEngine.isKeyPressed(4100)) {
            this.dialogManager.currentDialog.handleAction(0);
        } else if (this.dialogState == 0 && this.gameEngine.isKeyPressed(8448)) {
            this.dialogManager.currentDialog.handleAction(1);
        } else {
            if (this.gameEngine.isKeyPressed(196640)) {
                if (this.player.equipmentInventory.size() <= 0) {
                    return;
                }

                this.scrollOffset = ((int[]) this.player.equipmentInventory.elementAt(this.listSelectedIndex))[0];
                if (this.dialogState == 0) {
                    switch (ResourceManager.gameDatabase[4][this.scrollOffset][5]) {
                        case 7:
                        case 8:
                        case 9:
                        case 10:
                            this.dialogManager.showDialog("/data/ui/msgwarm.ui", 257, this);
                            this.setWarningText("Trong chiến đấu không thể sử dụng", "Nhấn nút 5 tiếp tục");
                            this.dialogState = 1;
                            return;
                        default:
                            this.gameEngine.changeState((byte) 16);
                            this.dialogManager.removeDialog("/data/ui/choice.ui");
                            return;
                    }
                }

                if (this.dialogState == 1) {
                    this.dialogManager.removeDialog("/data/ui/msgwarm.ui");
                    this.dialogState = 0;
                    return;
                }
            } else if (this.dialogState == 0 && this.gameEngine.isKeyPressed(262144)) {
                this.dialogManager.removeDialog("/data/ui/choice.ui");
                this.gameEngine.changeState((byte) 20);
            }

        }
    }

    private void bx() {
        if (this.dialogState == 0) {
            this.dialogState = 1;
            int var1;
            if (this.gameEngine instanceof WorldGameSession) {
                var1 = this.player.partyPokemon[this.subMenuIndex].canUseItem(this.scrollOffset);
            } else {
                var1 = this.player.partyPokemon[((BattleSystemManager) this.gameEngine).C29_f405[this.subMenuIndex]].canUseItem(this.scrollOffset);
            }

            switch (var1) {
                case 0:
                    this.dialogManager.showDialog("/data/ui/msgwarm.ui", 257, this);
                    this.setWarningText("Sủng vật này đã tử vong, không thể sử dụng", "Nhấn nút 5 tiếp tục");
                    return;
                case 1:
                    this.dialogManager.showDialog("/data/ui/msgwarm.ui", 257, this);
                    this.setWarningText("Sủng vật này không có, không thể sử dụng", "Nhấn nút 5 tiếp tục");
                    return;
                case 2:
                    this.dialogManager.showDialog("/data/ui/msgwarm.ui", 257, this);
                    this.setWarningText("Máu đầy, không cần sử dụng", "Nhấn nút 5 tiếp tục");
                    return;
                case 3:
                    this.dialogManager.showDialog("/data/ui/msgwarm.ui", 257, this);
                    this.setWarningText("Kỹ năng giá trị đã đầy, không cần sử dụng", "Nhấn nút 5 tiếp tục");
                    return;
                case 4:
                    this.dialogManager.showDialog("/data/ui/msgwarm.ui", 257, this);
                    this.setWarningText("Trên người đều bị lợi hiệu quả", "Nhấn nút 5 tiếp tục");
                    return;
                case 5:
                    this.dialogManager.showDialog("/data/ui/msgwarm.ui", 257, this);
                    this.setWarningText("Trong hưng phấn, không thể dùng", "Nhấn nút 5 tiếp tục");
                    return;
                case 6:
                default:
                    if (this.player.hasItem((int) this.scrollOffset, (int) 1, (byte) 0)) {
                        if (this.gameEngine instanceof WorldGameSession) {
                            this.player.partyPokemon[this.subMenuIndex].useItem(this.scrollOffset);
                        } else {
                            ((BattleSystemManager) this.gameEngine).C29_f408.isInBattle = true;
                            this.player.partyPokemon[((BattleSystemManager) this.gameEngine).C29_f405[this.subMenuIndex]].useItem(this.scrollOffset);
                        }

                        this.showPokemonPartyDetail(this.subMenuIndex);
                        this.dialogState = 1;
                        this.isItemUsed = true;
                        this.dialogManager.showDialog("/data/ui/msgwarm.ui", 257, this);
                        this.setWarningText("Thành công sử dụng đạo cụ", "Nhấn nút 5 tiếp tục");
                        return;
                    }

                    this.dialogState = 2;
                    this.showWarningDialog();
                    this.setWarningText("Không có đạo này cụ, hãy mua sắm", "Nhấn nút 5 tiếp tục");
                    return;
                case 7:
                    this.dialogManager.showDialog("/data/ui/msgwarm.ui", 257, this);
                    this.setWarningText("Máu và tinh khí đều đã đầy, không cần sử dụng", "Nhấn nút 5 tiếp tục");
                    return;
                case 8:
                    this.dialogManager.showDialog("/data/ui/msgwarm.ui", 257, this);
                    this.setWarningText("Sủng vật đã chết, không thể sử dụng", "Nhấn nút 5 tiếp tục");
            }
        } else if (this.dialogState == 1) {
            this.dialogState = 0;
            this.dialogManager.removeDialog("/data/ui/msgwarm.ui");
        } else {
            if (this.dialogState == 2) {
                this.dialogState = 0;
                this.dialogManager.removeDialog("/data/ui/msgwarm.ui");
                this.dialogManager.removeDialog("/data/ui/petstate.ui");
                if (this.gameEngine instanceof WorldGameSession) {
                    this.gameEngine.changeState((byte) 8);
                    return;
                }

                if (BattleSystemManager.B().C29_f408.equals(((BattleSystemManager) this.gameEngine).n(this.subMenuIndex))) {
                    this.updatePokemonExpBar(((BattleSystemManager) this.gameEngine).o(this.subMenuIndex));
                }

                if (((BattleSystemManager) this.gameEngine).C29_f408.isInBattle) {
                    ++((BattleSystemManager) this.gameEngine).C29_f410;
                    ((BattleSystemManager) this.gameEngine).changeState((byte) 1);
                    return;
                }

                ((BattleSystemManager) this.gameEngine).changeState((byte) 4);
            }

        }
    }

    public final void ao() {
        if (this.dialogState == 0 && this.gameEngine.isKeyPressed(4100)) {
            this.dialogManager.currentDialog.handleAction(0);
        } else if (this.dialogState == 0 && this.gameEngine.isKeyPressed(8448)) {
            this.dialogManager.currentDialog.handleAction(1);
        } else if (this.gameEngine.isKeyPressed(196640)) {
            this.bx();
        } else {
            if (this.dialogState == 0 && this.gameEngine.isKeyPressed(262144)) {
                if (this.isItemUsed) {
                    if (BattleSystemManager.B().C29_f408.equals(((BattleSystemManager) this.gameEngine).n(this.subMenuIndex))) {
                        this.updatePokemonExpBar(((BattleSystemManager) this.gameEngine).o(this.subMenuIndex));
                    }

                    if (((BattleSystemManager) this.gameEngine).C29_f408.isInBattle) {
                        ++((BattleSystemManager) this.gameEngine).C29_f410;
                        ((BattleSystemManager) this.gameEngine).changeState((byte) 1);
                    } else {
                        ((BattleSystemManager) this.gameEngine).changeState((byte) 4);
                    }

                    this.dialogManager.removeDialog("/data/ui/petstate.ui");
                    return;
                }

                ((BattleSystemManager) this.gameEngine).changeState((byte) 4);
                this.dialogManager.removeDialog("/data/ui/petstate.ui");
            }

        }
    }

    public final void b(int var1, int var2) {
        if (this.detailIndex >= BattleSystemManager.C29_f413.size()) {
            this.detailIndex = 0;
            GameScreenManager.getInstance().changeState((byte) 10);
        } else {
            label21:
            while (true) {
                PokemonEntity var3 = (PokemonEntity) BattleSystemManager.C29_f413.elementAt(this.detailIndex);

                while (this.detailIndex < BattleSystemManager.C29_f413.size() && var3.isMaxLevel()) {
                    ++this.detailIndex;
                    if (this.detailIndex < BattleSystemManager.C29_f413.size()) {
                        continue label21;
                    }
                }

                this.pokemonSlot1 = var1;
                this.pokemonSlot2 = var2;
                var3.activate();
                var3.setWorldPosition(var1, var2);
                this.listItemCount = 0;
                return;
            }
        }
    }

    public final void ap() {
        if (this.detailIndex >= BattleSystemManager.C29_f413.size()) {
            this.detailIndex = 0;
            GameScreenManager.getInstance().changeState((byte) 10);
        } else {
            if (this.listItemCount <= 0) {
                this.skillPageIndex += 8;
            }

            PokemonEntity var1;
            int var2 = (var1 = (PokemonEntity) BattleSystemManager.C29_f413.elementAt(this.detailIndex)).getDamageReceived() + this.skillPageIndex;
            int var3 = var1.getExpToNextLevel();
            int var4 = var1.getCurrentExp();
            if (var2 >= var3) {
                var2 = var3;
            } else if (var2 >= var4) {
                var2 = var4;
            }

            if (this.gameEngine.isKeyPressed(196640)) {
                if (var4 >= var3) {
                    this.dialogManager.currentDialog.getChildById(40).getComponentData().text = var3 + "/" + var3;
                    this.dialogManager.currentDialog.getChildById(9).getComponentData().text = "#P" + var1.calculateExpPercent(var3);
                    var1.setDamageReceived((int) 0);
                    this.listItemCount = 0;
                    ((BattleSystemManager) this.gameEngine).changeState((byte) 22);
                } else if (var2 < var4) {
                    this.skillPageIndex = 0;
                    var1.setDamageReceived(var4);
                    this.dialogManager.currentDialog.getChildById(40).getComponentData().text = var4 + "/" + var1.getExpToNextLevel();
                    this.dialogManager.currentDialog.getChildById(9).getComponentData().text = "#P" + var1.calculateExpPercent(var4);
                } else {
                    this.dialogManager.currentDialog.getChildById(40).getComponentData().text = var4 + "/" + var1.getExpToNextLevel();
                    this.dialogManager.currentDialog.getChildById(9).getComponentData().text = "#P" + var1.calculateExpPercent(var4);
                    var1.setDamageReceived(var2);
                    ++this.detailIndex;

                    while (this.detailIndex < BattleSystemManager.C29_f413.size()
                            && ((PokemonEntity) BattleSystemManager.C29_f413.elementAt(this.detailIndex)).isMaxLevel()) {
                        ++this.detailIndex;
                    }

                    if (this.detailIndex >= BattleSystemManager.C29_f413.size()) {
                        this.detailIndex = 0;
                        GameScreenManager.getInstance().changeState((byte) 10);
                    } else {
                        ((PokemonEntity) BattleSystemManager.C29_f413.elementAt(this.detailIndex)).setWorldPosition(this.pokemonSlot1,
                                this.pokemonSlot2);
                    }

                    this.listItemCount = 0;
                    this.skillPageIndex = 0;
                }
            } else {
                this.dialogManager.currentDialog.getChildById(40).getComponentData().text = var2 + "/" + var1.getExpToNextLevel();
                this.dialogManager.currentDialog.getChildById(9).getComponentData().text = "#P" + var1.calculateExpPercent(var2);
                this.dialogManager.currentDialog.getChildById(12).getComponentData().text = GameEngineBase
                        .getLocalizedText(var1.getDatabaseValue((byte) 0));
                this.dialogManager.currentDialog.getChildById(13).getComponentData().text = "lv" + var1.getLevel();
                this.dialogManager.currentDialog.getChildById(17).getComponentData().renderer
                        .setSpriteIndex(94 + var1.getDatabaseValue((byte) 1));
                if (var2 >= var3) {
                    var1.setDamageReceived((int) 0);
                    ((BattleSystemManager) this.gameEngine).changeState((byte) 22);
                } else {
                    if (var2 < var4) {
                        return;
                    }

                    ++this.listItemCount;
                    var1.setDamageReceived(var2);
                    if (this.listItemCount >= 10) {
                        ++this.detailIndex;

                        while (this.detailIndex < BattleSystemManager.C29_f413.size()
                                && ((PokemonEntity) BattleSystemManager.C29_f413.elementAt(this.detailIndex)).isMaxLevel()) {
                            ++this.detailIndex;
                        }

                        if (this.detailIndex >= BattleSystemManager.C29_f413.size()) {
                            this.detailIndex = 0;
                            GameScreenManager.getInstance().changeState((byte) 10);
                        } else {
                            ((PokemonEntity) BattleSystemManager.C29_f413.elementAt(this.detailIndex))
                                    .setWorldPosition(this.pokemonSlot1, this.pokemonSlot2);
                        }

                        this.listItemCount = 0;
                    }
                }

                this.skillPageIndex = 0;
            }
        }
    }

    public final void aq() {
        PokemonEntity var1 = (PokemonEntity) BattleSystemManager.C29_f413.elementAt(this.detailIndex);
        String[] var2 = new String[4];

        int var3;
        byte var5;
        for (var3 = 0; var3 < 4; ++var3) {
            StringBuffer var10002 = new StringBuffer();
            var5 = (byte) (var3 + 1);
            var2[var3] = var10002.append(var1.primaryStates[var5]).toString();
        }

        var1.levelUp();
        this.updatePokemonExpBar(var1);
        this.dialogManager.showDialog("/data/ui/levelUp.ui", 257, this);

        for (var3 = 0; var3 < 4; ++var3) {
            this.dialogManager.currentDialog.getChildById(var3 + 19).getComponentData().text = var2[var3];
        }

        if (var1.getSkillCount() < 5 && var1.getSkillCount() < var1.getLevel() / 10 + 1) {
            this.tempData1 = var1.getLearnableSkills();
            this.dialogManager.currentDialog.getChildById(51).getComponentData().text = "Có thể học tập kỹ năng mới";
        } else {
            this.dialogManager.currentDialog.getChildById(51).getComponentData().text = "";
        }

        this.dialogManager.currentDialog.getChildById(38).getComponentData().text = GameEngineBase
                .getLocalizedText((int) ResourceManager.gameDatabase[0][var1.getSpeciesId()][0]);
        this.dialogManager.currentDialog.getChildById(40).getComponentData().text = "" + var1.getLevel();
        if (this.dialogManager.currentDialog.getChildById(10).getComponentData().renderer == null) {
            this.dialogManager.currentDialog.getChildById(10).getComponentData().renderer = new SpriteRenderer();
            this.dialogManager.currentDialog.getChildById(10).getComponentData().renderer.spriteType = 3;
            this.dialogManager.currentDialog.getChildById(10).getComponentData().renderer.setSpriteIndex((int) 0);
            this.dialogManager.currentDialog.getChildById(10).getComponentData().renderer.initializeSprite(var1.spriteId, false,
                    (byte) -1);
        }

        for (var3 = 0; var3 < 4; ++var3) {
            ComponentData var10000 = this.dialogManager.currentDialog.getChildById(var3 + 31).getComponentData();
            StringBuffer var10001 = new StringBuffer();
            var5 = (byte) (var3 + 1);
            var10000.text = var10001.append(var1.primaryStates[var5]).toString();
        }

    }

    public final void ar() {
        ++this.skillSelectedIndex;
        if (this.skillSelectedIndex > 40) {
            this.skillSelectedIndex = 0;
            if (this.tempData1 != null) {
                ((BattleSystemManager) this.gameEngine).changeState((byte) 23);
            } else if (this.detailIndex + 1 >= BattleSystemManager.C29_f413.size()) {
                if (((PokemonEntity) BattleSystemManager.C29_f413.elementAt(this.detailIndex)).getCurrentExp() > 0) {
                    this.gameEngine.changeState((byte) 8);
                } else {
                    this.detailIndex = 0;
                    GameScreenManager.getInstance().changeState((byte) 10);
                }

                this.dialogManager.removeDialog("/data/ui/levelUp.ui");
            } else {
                this.gameEngine.changeState((byte) 8);
                this.dialogManager.removeDialog("/data/ui/levelUp.ui");
            }
        }

        if (this.gameEngine.isKeyPressed(196640)) {
            this.skillSelectedIndex = 0;
            if (this.tempData1 != null) {
                this.gameEngine.changeState((byte) 23);
                return;
            }

            if (this.detailIndex + 1 >= BattleSystemManager.C29_f413.size()) {
                if (((PokemonEntity) BattleSystemManager.C29_f413.elementAt(this.detailIndex)).getCurrentExp() > 0) {
                    this.gameEngine.changeState((byte) 8);
                } else {
                    this.detailIndex = 0;
                    GameScreenManager.getInstance().changeState((byte) 10);
                }

                this.dialogManager.removeDialog("/data/ui/levelUp.ui");
                return;
            }

            this.gameEngine.changeState((byte) 8);
            this.dialogManager.removeDialog("/data/ui/levelUp.ui");
        }

    }

    public final void as() {
        this.dialogManager.showDialog("/data/ui/choiceskill.ui", 257, this);
        this.dialogManager.removeDialog("/data/ui/levelUp.ui");
        this.menuIndex = 0;
        this.dialogState = 0;
        ((UIContainerComponent) this.dialogManager.currentDialog
                .getChildById(0)).primaryListComponent.totalItemCount = this.tempData1.length;
        if (this.tempData1.length > 5) {
            ((UIContainerComponent) this.dialogManager.currentDialog.getChildById(0)).primaryListComponent.setDisplayMode(1);
        } else {
            ((UIContainerComponent) this.dialogManager.currentDialog.getChildById(0)).primaryListComponent.setDisplayMode(-1);
        }

        if (this.dialogManager.currentDialog.getChildById(5).getComponentData().renderer == null) {
            this.dialogManager.currentDialog.getChildById(5).getComponentData().renderer = new SpriteRenderer();
            this.dialogManager.currentDialog.getChildById(5).getComponentData().renderer.spriteType = 3;
            this.dialogManager.currentDialog.getChildById(5).getComponentData().renderer.setSpriteIndex((int) 0);
            this.dialogManager.currentDialog.getChildById(5).getComponentData().renderer.initializeSprite(257, false, (byte) -1);
        }

        this.dialogManager.currentDialog.getChildById(5).getComponentData().renderer.setAnimationFrame((byte) 11, (byte) -1);
        this.dialogManager.currentDialog.getChildById(6).setVisible(false);
        this.by();
        if (!WorldGameSession.hasPurchasedSms) {
            this.showQuickMessage("Có thể nhấn #1nút mềm trái#0 để học tập kỹ năng");
            WorldGameSession.hasPurchasedSms = true;
        }

    }

    private void by() {
        this.listScrollOffset = ((UIContainerComponent) this.dialogManager.currentDialog
                .getChildById(0)).primaryListComponent.scrollOffset;
        this.listSelectedIndex = ((UIContainerComponent) this.dialogManager.currentDialog
                .getChildById(0)).primaryListComponent.selectedIndex;

        for (int var1 = 0; var1 < 5; ++var1) {
            if (var1 >= this.tempData1.length) {
                this.dialogManager.currentDialog.getChildById(13 + var1 * 5).getComponentData().text = "";
                this.dialogManager.currentDialog.getChildById(14 + var1 * 5).getComponentData().text = "";
            } else {
                this.dialogManager.currentDialog.getChildById(13 + var1 * 5).getComponentData().text = GameEngineBase
                        .getLocalizedText((int) ResourceManager.gameDatabase[1][this.tempData1[this.listScrollOffset + var1]][1]);
                this.dialogManager.currentDialog.getChildById(14 + var1 * 5).getComponentData().text = ""
                        + ResourceManager.gameDatabase[1][this.tempData1[this.listScrollOffset + var1]][5];
            }
        }

        this.playLevelUpSound(this.tempData1[this.listSelectedIndex]);
        this.dialogManager.currentDialog.getChildById(51).setOffsetY(98 + this.listSelectedIndex * 62 / this.tempData1.length,
                this.dialogManager.currentDialog.getUIContainerComponent());
    }

    public final void at() {
        if (!this.isPopupActive() && this.gameEngine.isKeyPressed(4100) && this.dialogState == 0) {
            this.dialogManager.currentDialog.handleAction(0);
            this.by();
        } else if (!this.isPopupActive() && this.gameEngine.isKeyPressed(8448) && this.dialogState == 0) {
            this.dialogManager.currentDialog.handleAction(1);
            this.by();
        } else if (!this.isPopupActive() && this.dialogState == 0
                && (this.gameEngine.isKeyPressed(131072) || this.gameEngine.isPointerClickInBounds(40, 228, 45, 20))
                || this.dialogState == 1 && this.gameEngine.isKeyPressed(196640)) {
            if (this.dialogState == 0) {
                this.dialogState = 1;
                this.dialogManager.showDialog("/data/ui/msgwarm.ui", 257, this);
                this.setWarningText(
                        "Học tập" + GameEngineBase
                                .getLocalizedText((int) ResourceManager.gameDatabase[1][this.tempData1[this.menuIndex]][1]),
                        "Nhấn nút 5 tiếp tục");
            } else if (this.dialogState == 1) {
                PokemonEntity var1;
                (var1 = (PokemonEntity) BattleSystemManager.C29_f413.elementAt(this.detailIndex))
                        .learnSkill((byte) this.tempData1[this.listSelectedIndex]);
                this.tempData1 = null;
                if (this.detailIndex + 1 >= BattleSystemManager.C29_f413.size() && var1.getCurrentExp() <= 0) {
                    this.detailIndex = 0;
                    GameScreenManager.getInstance().changeState((byte) 10);
                } else {
                    this.gameEngine.changeState((byte) 8);
                }

                this.dialogManager.removeDialog("/data/ui/msgwarm.ui");
                this.dialogManager.removeDialog("/data/ui/choiceskill.ui");
            }
        }

        this.handleOpenBoxDialog();
    }

    public final void au() {
        this.dialogState = 0;
        this.showQuickMessage("Ba lô sủng vật đều thăng 5 cấp");
    }

    public final void av() {
        if (this.dialogState == 0) {
            if (this.isMessageAnimationComplete()) {
                this.dialogState = 1;
                if (WorldGameSession.battlePartyBackup.size() <= 0) {
                    this.gameEngine.changeState((byte) 14);
                }
            }
        } else if (this.dialogState == 1) {
            this.dialogManager.removeDialog("/data/ui/bodyShop.ui");
            this.bz();
            this.needsRedraw = true;
        } else if (this.dialogState >= 3) {
            label93:
            {
                if (this.dialogState == 5) {
                    this.dialogState = 6;
                    this.showTipDialog();
                    this.setTipText("Đang lưu...");
                    this.hideConfirmButtons();
                } else if (this.dialogState == 6) {
                    WorldGameSession.tutorialStep = 2;
                    GameEngineBase var10000 = this.gameEngine;
                    WorldGameSession.saveGameFlags();
                    if (((WorldGameSession) this.gameEngine).savePartyPokemon()) {
                        this.setTipText("Lưu thành công");
                        this.dialogState = 7;
                    }
                } else if (this.dialogState == 7) {
                    this.dialogManager.removeDialog("/data/ui/msgtip.ui");
                    this.dialogState = 0;
                    if (this.gameEngine.previousState == 14) {
                        this.gameEngine.changeState((byte) 14);
                    } else {
                        this.gameEngine.changeState((byte) 0);
                    }
                    break label93;
                }

                if (!this.isPopupActive() && this.gameEngine.isKeyPressed(4100) && this.dialogState == 3) {
                    this.dialogManager.currentDialog.handleAction(0);
                    this.by();
                } else if (!this.isPopupActive() && this.gameEngine.isKeyPressed(8448) && this.dialogState == 3) {
                    this.dialogManager.currentDialog.handleAction(1);
                    this.by();
                } else if (!this.isPopupActive() && this.dialogState == 3
                        && (this.gameEngine.isKeyPressed(131072) || this.gameEngine.isPointerClickInBounds(40, 228, 45, 20))
                        || this.dialogState == 4 && this.gameEngine.isKeyPressed(196640)) {
                    if (this.dialogState == 3) {
                        this.dialogState = 4;
                        this.dialogManager.showDialog("/data/ui/msgwarm.ui", 257, this);
                        this.setWarningText(
                                "Học tập" + GameEngineBase
                                        .getLocalizedText((int) ResourceManager.gameDatabase[1][this.tempData1[this.listSelectedIndex]][1]),
                                "Nhấn nút 5 tiếp tục");
                    } else if (this.dialogState == 4) {
                        ((PokemonEntity) WorldGameSession.battlePartyBackup.elementAt(this.detailIndex))
                                .learnSkill((byte) this.tempData1[this.listSelectedIndex]);
                        this.tempData1 = null;
                        ++this.detailIndex;
                        if (this.detailIndex >= WorldGameSession.battlePartyBackup.size()) {
                            this.detailIndex = 0;
                            this.dialogState = 5;
                        } else {
                            this.bz();
                        }

                        this.dialogManager.removeDialog("/data/ui/msgwarm.ui");
                        this.dialogManager.removeDialog("/data/ui/choiceskill.ui");
                    }
                }
            }
        } else if (this.gameEngine.isKeyPressed(196640)) {
            this.dialogManager.showDialog("/data/ui/choiceskill.ui", 257, this);
            this.dialogManager.removeDialog("/data/ui/levelUp.ui");
            this.menuIndex = 0;
            this.dialogState = 3;
            ((UIContainerComponent) this.dialogManager.currentDialog
                    .getChildById(0)).primaryListComponent.totalItemCount = this.tempData1.length;
            if (this.tempData1.length > 5) {
                ((UIContainerComponent) this.dialogManager.currentDialog.getChildById(0)).primaryListComponent.setDisplayMode(1);
            } else {
                ((UIContainerComponent) this.dialogManager.currentDialog.getChildById(0)).primaryListComponent.setDisplayMode(-1);
            }

            if (this.dialogManager.currentDialog.getChildById(5).getComponentData().renderer == null) {
                this.dialogManager.currentDialog.getChildById(5).getComponentData().renderer = new SpriteRenderer();
                this.dialogManager.currentDialog.getChildById(5).getComponentData().renderer.spriteType = 3;
                this.dialogManager.currentDialog.getChildById(5).getComponentData().renderer.setSpriteIndex((int) 0);
                this.dialogManager.currentDialog.getChildById(5).getComponentData().renderer.initializeSprite(257, false,
                        (byte) -1);
            }

            this.dialogManager.currentDialog.getChildById(5).getComponentData().renderer.setAnimationFrame((byte) 11, (byte) -1);
            this.dialogManager.currentDialog.getChildById(6).setVisible(false);
            this.by();
            this.needsRedraw = true;
        }

        this.handleOpenBoxDialog();
    }

    private void bz() {
        this.dialogState = 2;
        PokemonEntity var1 = (PokemonEntity) WorldGameSession.battlePartyBackup.elementAt(this.detailIndex);
        this.dialogManager.showDialog("/data/ui/levelUp.ui", 257, this);

        int var2;
        for (var2 = 0; var2 < 4; ++var2) {
            this.dialogManager.currentDialog.getChildById(var2 + 19).getComponentData().text = ""
                    + var1.getCachedStat((int) ((byte) (var2 + 1 - 1)));
        }

        if (var1.getSkillCount() < 5 && var1.getSkillCount() < var1.getLevel() / 10 + 1) {
            this.tempData1 = var1.getLearnableSkills();
            this.dialogManager.currentDialog.getChildById(51).getComponentData().text = "Nhấn nút 5 học tập kỹ năng mới";
        } else {
            this.dialogManager.currentDialog.getChildById(51).getComponentData().text = "";
        }

        this.dialogManager.currentDialog.getChildById(38).getComponentData().text = GameEngineBase
                .getLocalizedText((int) ResourceManager.gameDatabase[0][var1.getSpeciesId()][0]);
        this.dialogManager.currentDialog.getChildById(40).getComponentData().text = "" + var1.getLevel();
        if (this.dialogManager.currentDialog.getChildById(10).getComponentData().renderer == null) {
            this.dialogManager.currentDialog.getChildById(10).getComponentData().renderer = new SpriteRenderer();
            this.dialogManager.currentDialog.getChildById(10).getComponentData().renderer.spriteType = 3;
            this.dialogManager.currentDialog.getChildById(10).getComponentData().renderer.setSpriteIndex((int) 0);
            this.dialogManager.currentDialog.getChildById(10).getComponentData().renderer.initializeSprite(var1.spriteId, false,
                    (byte) -1);
        }

        for (var2 = 0; var2 < 4; ++var2) {
            ComponentData var10000 = this.dialogManager.currentDialog.getChildById(var2 + 31).getComponentData();
            StringBuffer var10001 = new StringBuffer();
            byte var4 = (byte) (var2 + 1);
            var10000.text = var10001.append(var1.primaryStates[var4]).toString();
        }

    }

    public final void aw() {
        this.dialogManager.showDialog("/data/ui/npcEnemy.ui", 296, this);
        if (this.dialogManager.currentDialog.getChildById(1).getComponentData().renderer == null) {
            this.dialogManager.currentDialog.getChildById(1).getComponentData().renderer = new SpriteRenderer();
            this.dialogManager.currentDialog.getChildById(1).getComponentData().renderer.spriteType = 2;
            this.dialogManager.currentDialog.getChildById(1).getComponentData().renderer.initializeSprite(296, false, (byte) 0);
            this.dialogManager.currentDialog.getChildById(1).getComponentData().renderer.setSpriteIndex((int) 0);
        }

        this.dialogManager.currentDialog.getChildById(36).setVisible(false);
    }

    private void a(int var1, int var2, int var3) {
        if (var3 != -1 && this.dialogManager.currentDialog.getChildById(var3).getComponentData().renderer != null) {
            this.dialogManager.currentDialog.getChildById(var3).setVisible(false);
        }

        if (this.dialogManager.currentDialog.getChildById(var1).getComponentData().renderer == null) {
            this.dialogManager.currentDialog.getChildById(var1).getComponentData().renderer = new SpriteRenderer();
            this.dialogManager.currentDialog.getChildById(var1).getComponentData().renderer.spriteType = 2;
            this.dialogManager.currentDialog.getChildById(var1).getComponentData().renderer.initializeSprite(296, false,
                    (byte) 0);
            this.dialogManager.currentDialog.getChildById(var1).getComponentData().renderer.setSpriteIndex((int) 0);
        }

        this.dialogManager.currentDialog.getChildById(var1).getComponentData().renderer.setSpriteIndex(var2);
    }

    public final void updateBattleNPCScript(int var1, int var2) {
        switch (var1) {
            case 0:
                this.dialogManager.currentDialog.getChildById(1).getComponentData().renderer.setSpriteIndex(var2);
                return;
            case 1:
                for (var1 = 2; var1 < 4; ++var1) {
                    if (this.dialogManager.currentDialog.getChildById(var1).getComponentData().renderer == null) {
                        this.dialogManager.currentDialog.getChildById(var1).getComponentData().renderer = new SpriteRenderer();
                        this.dialogManager.currentDialog.getChildById(var1).getComponentData().renderer.spriteType = 2;
                        this.dialogManager.currentDialog.getChildById(var1).getComponentData().renderer.setSpriteIndex((int) 0);
                    }

                    if (var1 % 2 == 1) {
                        this.dialogManager.currentDialog.getChildById(var1).getComponentData().renderer.initializeSprite(0, false,
                                (byte) -1);
                    } else if (WorldGameSession.currentInteractNpcId == -1) {
                        if (WorldGameSession.previousInteractNpcId == -1) {
                            this.dialogManager.currentDialog.getChildById(var1).getComponentData().renderer
                                    .initializeSprite(WorldGameSession.getInstance().NPCs[8].sprite.spriteSetId, false, (byte) -1);
                        } else {
                            this.dialogManager.currentDialog.getChildById(var1).getComponentData().renderer.initializeSprite(
                                    WorldGameSession.getInstance().NPCs[WorldGameSession.previousInteractNpcId].sprite.spriteSetId, false,
                                    (byte) -1);
                        }
                    } else {
                        this.dialogManager.currentDialog.getChildById(var1).getComponentData().renderer.initializeSprite(
                                WorldGameSession.getInstance().NPCs[WorldGameSession.currentInteractNpcId].sprite.spriteSetId, false, (byte) -1);
                    }

                    this.dialogManager.currentDialog.getChildById(var1).getComponentData().renderer.setSpriteIndex((int) 1);
                }

                this.dialogManager.currentDialog.getChildById(1).getComponentData().renderer.setSpriteIndex(var2);
                return;
            case 2:
                for (var1 = 2; var1 < 4; ++var1) {
                    if (this.dialogManager.currentDialog.getChildById(var1).getComponentData().renderer != null) {
                        this.dialogManager.currentDialog.getChildById(var1).setVisible(false);
                    }

                    if (this.dialogManager.currentDialog.getChildById(var1 + 32).getComponentData().renderer == null) {
                        this.dialogManager.currentDialog.getChildById(var1 + 32).getComponentData().renderer = new SpriteRenderer();
                        this.dialogManager.currentDialog.getChildById(var1 + 32).getComponentData().renderer.spriteType = 2;
                        this.dialogManager.currentDialog.getChildById(var1 + 32).getComponentData().renderer
                                .setSpriteIndex((int) 0);
                    }

                    if (var1 % 2 == 1) {
                        this.dialogManager.currentDialog.getChildById(var1 + 32).getComponentData().renderer.initializeSprite(0,
                                false, (byte) -1);
                    } else if (WorldGameSession.currentInteractNpcId == -1) {
                        if (WorldGameSession.previousInteractNpcId == -1) {
                            this.dialogManager.currentDialog.getChildById(var1 + 32).getComponentData().renderer
                                    .initializeSprite(WorldGameSession.getInstance().NPCs[8].sprite.spriteSetId, false, (byte) -1);
                        } else {
                            this.dialogManager.currentDialog.getChildById(var1 + 32).getComponentData().renderer.initializeSprite(
                                    WorldGameSession.getInstance().NPCs[WorldGameSession.previousInteractNpcId].sprite.spriteSetId, false,
                                    (byte) -1);
                        }
                    } else {
                        this.dialogManager.currentDialog.getChildById(var1 + 32).getComponentData().renderer.initializeSprite(
                                WorldGameSession.getInstance().NPCs[WorldGameSession.currentInteractNpcId].sprite.spriteSetId, false, (byte) -1);
                    }

                    this.dialogManager.currentDialog.getChildById(var1 + 32).getComponentData().renderer.setSpriteIndex((int) 1);
                }

                this.dialogManager.currentDialog.getChildById(1).getComponentData().renderer.setSpriteIndex(var2);
                return;
            case 3:
                for (var1 = 2; var1 < 4; ++var1) {
                    if (this.dialogManager.currentDialog.getChildById(var1 + 32).getComponentData().renderer != null) {
                        this.dialogManager.currentDialog.getChildById(var1 + 32).setVisible(false);
                    }

                    if (this.dialogManager.currentDialog.getChildById(var1 + 2).getComponentData().renderer == null) {
                        this.dialogManager.currentDialog.getChildById(var1 + 2).getComponentData().renderer = new SpriteRenderer();
                        this.dialogManager.currentDialog.getChildById(var1 + 2).getComponentData().renderer.spriteType = 2;
                        this.dialogManager.currentDialog.getChildById(var1 + 2).getComponentData().renderer.setSpriteIndex((int) 0);
                    }

                    if (var1 % 2 == 1) {
                        this.dialogManager.currentDialog.getChildById(var1 + 2).getComponentData().renderer.initializeSprite(0,
                                false, (byte) -1);
                    } else if (WorldGameSession.currentInteractNpcId == -1) {
                        if (WorldGameSession.previousInteractNpcId == -1) {
                            this.dialogManager.currentDialog.getChildById(var1 + 2).getComponentData().renderer
                                    .initializeSprite(WorldGameSession.getInstance().NPCs[8].sprite.spriteSetId, false, (byte) -1);
                        } else {
                            this.dialogManager.currentDialog.getChildById(var1 + 2).getComponentData().renderer.initializeSprite(
                                    WorldGameSession.getInstance().NPCs[WorldGameSession.previousInteractNpcId].sprite.spriteSetId, false,
                                    (byte) -1);
                        }
                    } else {
                        this.dialogManager.currentDialog.getChildById(var1 + 2).getComponentData().renderer.initializeSprite(
                                WorldGameSession.getInstance().NPCs[WorldGameSession.currentInteractNpcId].sprite.spriteSetId, false, (byte) -1);
                    }

                    this.dialogManager.currentDialog.getChildById(var1 + 2).getComponentData().renderer.setSpriteIndex((int) 1);
                }

                this.dialogManager.currentDialog.getChildById(1).getComponentData().renderer.setSpriteIndex(var2);
                this.skillOffset = BattleSystemManager.B().H();
                this.skillCount = this.player.partySize;
                if (var2 - 3 < this.skillOffset) {
                    this.a((int) 6, 6, -1);
                }

                if (var2 - 3 < this.skillCount) {
                    this.a((int) 18, 6, -1);
                    return;
                }
                break;
            case 4:
                if (var2 - 3 < this.skillOffset) {
                    this.a((int) (6 + (var2 - 3 << 1)), 6, 6 + (var2 - 4 << 1));
                } else {
                    this.a((int) (6 + (var2 - 3 << 1)), 5, 6 + (var2 - 4 << 1));
                }

                if (var2 - 4 < this.skillOffset) {
                    this.a((int) (7 + (var2 - 4 << 1)), 6, 6 + (var2 - 4 << 1));
                } else {
                    this.a((int) (7 + (var2 - 4 << 1)), 5, 6 + (var2 - 4 << 1));
                }

                if (var2 - 4 < this.skillCount) {
                    this.a((int) (19 + (var2 - 4 << 1)), 6, 18 + (var2 - 4 << 1));
                } else {
                    this.a((int) (19 + (var2 - 4 << 1)), 5, 18 + (var2 - 4 << 1));
                }

                if (var2 - 3 < this.skillCount) {
                    this.a((int) (18 + (var2 - 3 << 1)), 6, 18 + (var2 - 4 << 1));
                    return;
                }

                this.a((int) (18 + (var2 - 3 << 1)), 5, 18 + (var2 - 4 << 1));
                return;
            case 5:
                if (var2 - 4 < this.skillOffset) {
                    this.a((int) (7 + (var2 - 4 << 1)), 6, 6 + (var2 - 4 << 1));
                } else {
                    this.a((int) (7 + (var2 - 4 << 1)), 5, 6 + (var2 - 4 << 1));
                }

                if (var2 - 4 < this.skillCount) {
                    this.a((int) (19 + (var2 - 4 << 1)), 6, 18 + (var2 - 4 << 1));
                    return;
                }

                this.a((int) (19 + (var2 - 4 << 1)), 5, 18 + (var2 - 4 << 1));
                return;
            case 6:
                this.a((int) 30, 8, -1);
                this.a((int) 31, 7, -1);
                return;
            case 7:
                this.a((int) 32, 8, 30);
                this.a((int) 33, 7, 31);
                return;
            case 8:
                this.dialogManager.currentDialog.getChildById(36).setVisible(true);
                return;
            case 9:
                this.dialogManager.currentDialog.getChildById(36).setVisible(false);
                return;
            case 10:
                this.a((int) 1, 4, 32);
                this.a((int) 1, 4, 33);

                for (var1 = 4; var1 < 6; ++var1) {
                    this.dialogManager.currentDialog.getChildById(var1).setVisible(false);
                }

                for (var1 = 7; var1 < 19; var1 += 2) {
                    this.dialogManager.currentDialog.getChildById(var1).setOffsetX(172 + 17 * (var1 - 7) / 2,
                            this.dialogManager.currentDialog.getUIContainerComponent());
                    this.dialogManager.currentDialog.getChildById(var1 + 12).setOffsetX(-30 + 17 * (var1 - 7) / 2,
                            this.dialogManager.currentDialog.getUIContainerComponent());
                }

                return;
            case 11:
                for (var1 = 4; var1 < 6; ++var1) {
                    this.dialogManager.currentDialog.getChildById(var1).setVisible(false);
                }

                for (var1 = 7; var1 < 19; var1 += 2) {
                    this.dialogManager.currentDialog.getChildById(var1).setVisible(false);
                    this.dialogManager.currentDialog.getChildById(var1 + 12).setVisible(false);
                }

                this.a((int) 1, 0, -1);
        }

    }

    private void showQuickNotification(String var1) {
        if (this.dialogManager.currentDialog.getChildById(1).getComponentData().renderer == null) {
            this.dialogManager.currentDialog.getChildById(1).getComponentData().renderer = new SpriteRenderer();
            this.dialogManager.currentDialog.getChildById(1).getComponentData().renderer.setSpriteIndex((int) 0);
            this.dialogManager.currentDialog.getChildById(1).getComponentData().renderer.spriteType = 3;
            this.dialogManager.currentDialog.getChildById(1).getComponentData().renderer.initializeSprite(257, false, (byte) -2);
        }

        this.dialogManager.currentDialog.getChildById(1).getComponentData().renderer.setAnimationFrame((byte) 9, (byte) -2);
        this.dialogManager.currentDialog.getChildById(2).getComponentData().text = var1;
        this.popupState = 0;
    }

    public final void ax() {
        this.dialogManager.showDialog("/data/ui/openbox.ui", 257, this);
        this.showQuickNotification("Không có cái chìa khóa, có thể đến tài liệu cửa hàng mua sắm");
    }

    public final void ay() {
        this.dialogManager.showDialog("/data/ui/openbox.ui", 257, this);
        this.showQuickNotification("Đạo cụ đã đủ");
    }

    public final void a(String var1, int var2) {
        this.dialogManager.showDialog("/data/ui/openbox.ui", 257, this);
        this.showQuickNotification(var1 + " x " + var2);
    }

    public final void showQuickMessage(String var1) {
        this.dialogManager.showDialog("/data/ui/openbox.ui", 257, this);
        this.showQuickNotification(var1);
    }

    public final void processChestReward() {
        if (this.dialogManager.isTopDialog("/data/ui/openbox.ui")) {
            this.dialogManager.removeDialog("/data/ui/openbox.ui");
        }

    }

    public final boolean isMessageAnimationComplete() {
        return !this.dialogManager.isTopDialog("/data/ui/openbox.ui");
    }

    public final void showTutorialHint(String var1) {
        this.dialogManager.showDialog("/data/ui/taskTip.ui", 257, this);
        if (this.dialogManager.currentDialog.getChildById(1).getComponentData().renderer == null) {
            this.dialogManager.currentDialog.getChildById(1).getComponentData().renderer = new SpriteRenderer();
            this.dialogManager.currentDialog.getChildById(1).getComponentData().renderer.setSpriteIndex((int) 0);
            this.dialogManager.currentDialog.getChildById(1).getComponentData().renderer.spriteType = 3;
            this.dialogManager.currentDialog.getChildById(1).getComponentData().renderer.initializeSprite(257, false, (byte) -2);
        }

        this.dialogManager.currentDialog.getChildById(1).getComponentData().renderer.setAnimationFrame((byte) 10, (byte) -2);
        this.dialogManager.currentDialog.getChildById(2).getComponentData().text = var1;
        this.popupState = 0;
    }

    public final boolean canNavigateList() {
        return !this.dialogManager.isTopDialog("/data/ui/taskTip.ui");
    }

    public final void aC() {
        this.subMenuIndex = 0;
        this.dialogState = 0;
        this.dialogManager.showDialog("/data/ui/bodyShop.ui", 257, this);
        this.bA();
    }

    private void bA() {
        String var1 = "";
        int[] var2;
        switch (this.subMenuIndex) {
            case 0:
                var1 = "Tùy thời mua sắm các loại đạo cụ, già trẻ không gạt.";
                break;
            case 1:
                var2 = new int[]{2, 1, 2};
                var1 = GameEngineBase.getLocalizedText((int) 602) + GameEngineBase.formatString(604, (int[]) var2);
                break;
            case 2:
                var2 = new int[]{2, 1, 2};
                var1 = GameEngineBase.getLocalizedText((int) 603) + GameEngineBase.formatString(604, (int[]) var2);
                break;
            case 3:
                var2 = new int[]{2, 1, 2};
                var1 = GameEngineBase.getLocalizedText((int) 601) + GameEngineBase.formatString(604, (int[]) var2);
        }

        this.dialogManager.currentDialog.getChildById(11).getComponentData().text = var1;
        if (this.subMenuIndex > 0) {
            this.gameEngine.setPaymentState((byte) 0);
            this.bB();
        }

    }

    private void bB() {
        switch (this.subMenuIndex) {
            case 1:
                this.gameEngine.initPayment((byte) 3);
                return;
            case 2:
                this.gameEngine.initPayment((byte) 4);
                return;
            case 3:
                this.gameEngine.initPayment((byte) 2);
            default:
        }
    }

    public final void aD() {
        switch (this.subMenuIndex) {
            case 0:
                if (this.gameEngine.isKeyPressed(4100) && this.dialogState == 0) {
                    this.dialogManager.currentDialog.handleAction(0);
                    this.bA();
                    return;
                }

                if (this.gameEngine.isKeyPressed(8448) && this.dialogState == 0) {
                    this.dialogManager.currentDialog.handleAction(1);
                    this.bA();
                    return;
                }

                if (this.gameEngine.isKeyPressed(196640)) {
                    this.gameEngine.changeState((byte) 26);
                    this.dialogManager.removeDialog("/data/ui/bodyShop.ui");
                    return;
                }

                if (this.gameEngine.isKeyPressed(786432)) {
                    this.menuIndex = 0;
                    this.gameEngine.changeState((byte) 6);
                    this.dialogManager.removeDialog("/data/ui/bodyShop.ui");
                    return;
                }
                break;
            default:
                switch (this.gameEngine.getPaymentType()) {
                    case 0:
                        if (this.gameEngine.isKeyPressed(4100) && this.dialogState == 0) {
                            this.dialogManager.currentDialog.handleAction(0);
                            this.bA();
                            return;
                        }

                        if (this.gameEngine.isKeyPressed(8448) && this.dialogState == 0) {
                            this.dialogManager.currentDialog.handleAction(1);
                            this.bA();
                            return;
                        }

                        if ((this.dialogState != 0 || !this.gameEngine.isKeyPressed(131072))
                                && (this.dialogState != 1 || !this.gameEngine.isKeyPressed(65568))) {
                            if (this.gameEngine.isKeyPressed(786432) && this.dialogState == 0) {
                                this.menuIndex = 0;
                                this.gameEngine.changeState((byte) 6);
                                this.dialogManager.removeDialog("/data/ui/bodyShop.ui");
                                return;
                            }
                        } else {
                            if (this.dialogState != 0) {
                                this.dialogState = 0;
                                this.dialogManager.removeDialog("/data/ui/msgwarm.ui");
                                return;
                            }

                            this.bB();
                            if (this.gameEngine.getCurrentPaymentIndex() == 3) {
                                if (WorldGameSession.pendingEvolutions != null) {
                                    WorldGameSession.pendingEvolutions.removeAllElements();
                                }

                                int var1;
                                for (var1 = 0; var1 < PlayerCharacter.getInstance().partySize
                                        && PlayerCharacter.getInstance().partyPokemon[var1].getLevel() >= 50; ++var1) {
                                }

                                if (var1 >= PlayerCharacter.getInstance().partySize) {
                                    this.dialogState = 1;
                                    this.dialogManager.showDialog("/data/ui/msgwarm.ui", 257, this);
                                    this.setWarningText("Trong ba lô sủng vật đều đã max level", "Nhấn nút 5 tiếp tục");
                                    return;
                                }
                            }

                            if (this.gameEngine.getTotalPaymentCount() > 1) {
                                this.gameEngine.setPaymentState((byte) 1);
                                return;
                            }

                            if (this.dialogManager.currentDialog.getChildById(11).getComponentData().isScrollingComplete()) {
                                this.gameEngine.handlePaymentAction(1);
                                return;
                            }
                        }
                        break;
                    case 1:
                        if (this.gameEngine.isKeyPressed(131072)) {
                            this.gameEngine.handlePaymentDialogResponse(1);
                            return;
                        }

                        if (this.gameEngine.isKeyPressed(262144)) {
                            this.gameEngine.handlePaymentDialogResponse(2);
                            return;
                        }
                        break;
                    case 2:
                        if (this.bC() && this.gameEngine.isKeyPressed(917504)) {
                            if (this.gameEngine.isPaymentComplete()) {
                                if (this.gameEngine.getCurrentPaymentIndex() == 3) {
                                    this.gameEngine.changeState((byte) 25);
                                }

                                this.gameEngine.setPaymentState((byte) 5);
                            } else {
                                this.gameEngine.setPaymentState((byte) 1);
                            }

                            this.dialogState = 0;
                            return;
                        }
                        break;
                    case 3:
                        if (this.gameEngine.isKeyPressed(393216)) {
                            this.gameEngine.handlePaymentDialogResponse(1);
                        }
                }
        }

    }

    private boolean bC() {
        if (this.dialogState == 0) {
            this.dialogState = 1;
            this.showTipDialog();
            this.setTipText("Đang lưu...");
            this.hideConfirmButtons();
        } else if (this.dialogState == 1) {
            if (this.gameEngine.getCurrentPaymentIndex() == 3) {
                if (WorldGameSession.getInstance().saveAllGameData()) {
                    this.setTipText("Lưu thành công");
                    this.dialogState = 2;
                }
            } else if (WorldGameSession.getInstance().saveEssentialData()) {
                this.setTipText("Lưu thành công");
                this.dialogState = 2;
            }
        } else if (this.dialogState == 2) {
            this.dialogManager.removeDialog("/data/ui/msgtip.ui");
            this.dialogState = 3;
        } else if (this.dialogState == 3) {
            return true;
        }

        return false;
    }

    public final void aE() {
        this.dialogManager.showDialog("/data/ui/dialog.ui", 257, this);
        this.dialogManager.currentDialog.getChildById(12).setVisible(false);
        this.dialogManager.currentDialog.getChildById(13).setVisible(false);
    }

    public final void a(String var1, String var2, int var3, int var4) {
        this.dialogManager.showDialog("/data/ui/dialog.ui", 257, this);
        GameUtils.a(var2, GameEngineBase.getFontHeight(), this.dialogManager.currentDialog.getChildById(14).getWidth(),
                GameEngineBase.getDefaultFont(), true, (byte) -1, this.gameEngine.dialogManager.textRenderer);
        GameUtils.d(this.dialogManager.currentDialog.getChildById(14).getHeight());
        this.dialogManager.currentDialog.getChildById(14).getComponentData().text = GameUtils.e(1);
        WorldGameSession.dialogTextId = (byte) var3;
        WorldGameSession.dialogPortraitId = (byte) var4;
        this.dialogManager.currentDialog.getChildById(8).setVisible(false);
        this.dialogManager.currentDialog.getChildById(11).setVisible(false);
        this.dialogManager.currentDialog.getChildById(12).setVisible(true);
        this.dialogManager.currentDialog.getChildById(13).setVisible(true);
        if (var3 == -1) {
            this.dialogManager.currentDialog.getChildById(12).setVisible(false);
            this.dialogManager.currentDialog.getChildById(13).setVisible(false);
        }

        switch (var3) {
            case 0:
                if (var4 != -1) {
                    if (this.dialogManager.currentDialog.getChildById(11).getComponentData().renderer == null) {
                        this.dialogManager.currentDialog.getChildById(11).getComponentData().renderer = new SpriteRenderer();
                        this.dialogManager.currentDialog.getChildById(11).getComponentData().renderer.spriteType = 3;
                        this.dialogManager.currentDialog.getChildById(11).getComponentData().renderer.setSpriteIndex((int) 0);
                        this.dialogManager.currentDialog.getChildById(11).getComponentData().renderer.initializeSprite(323, false,
                                (byte) -2);
                    }

                    this.dialogManager.currentDialog.getChildById(11).setVisible(true);
                    this.dialogManager.currentDialog.getChildById(11).getComponentData().renderer
                            .setAnimationFrame((byte) (var3 + (var4 << 1)), (byte) -2);
                }

                this.dialogManager.currentDialog.getChildById(13).setVisible(false);
                this.dialogManager.currentDialog.getChildById(12).getComponentData().text = var1;
                return;
            case 1:
                if (var4 != -1) {
                    if (this.dialogManager.currentDialog.getChildById(8).getComponentData().renderer == null) {
                        this.dialogManager.currentDialog.getChildById(8).getComponentData().renderer = new SpriteRenderer();
                        this.dialogManager.currentDialog.getChildById(8).getComponentData().renderer.spriteType = 3;
                        this.dialogManager.currentDialog.getChildById(8).getComponentData().renderer.setSpriteIndex((int) 0);
                        this.dialogManager.currentDialog.getChildById(8).getComponentData().renderer.initializeSprite(323, false,
                                (byte) -2);
                    }

                    this.dialogManager.currentDialog.getChildById(8).setVisible(true);
                    this.dialogManager.currentDialog.getChildById(8).getComponentData().renderer
                            .setAnimationFrame((byte) (var3 + (var4 << 1)), (byte) -2);
                }

                this.dialogManager.currentDialog.getChildById(12).setVisible(false);
                this.dialogManager.currentDialog.getChildById(13).getComponentData().text = var1;
            default:
        }
    }

    public final void updateBattleActionMenu(int var1) {
        this.dialogManager.currentDialog.getChildById(14).getComponentData().text = GameUtils.e(var1);
    }

    public final void aF() {
        this.dialogManager.removeDialog("/data/ui/dialog.ui");
    }

    public final boolean d(int var1, int var2) {
        if (var2 == -1) {
            return true;
        } else {
            switch (var1) {
                case 0:
                    if (this.dialogManager.currentDialog.getChildById(11).getComponentData().renderer.getSpriteManager()
                            .isAtLastFrame()) {
                        return true;
                    }
                    break;
                case 1:
                    if (this.dialogManager.currentDialog.getChildById(8).getComponentData().renderer.getSpriteManager()
                            .isAtLastFrame()) {
                        return true;
                    }
            }

            this.needsRedraw = true;
            return false;
        }
    }

    public final void showMultiOptionDialog(int var1, int var2, String[] var3, String var4) {
        this.menuIndex = 0;
        this.dialogManager.showDialog(this.DIALOG_PATHS[var1], 257, this);
        ((UIContainerComponent) this.dialogManager.currentDialog.getChildById(0)).primaryListComponent.totalItemCount = var2;
        switch (var1) {
            case 0:
                for (var1 = 0; var1 < var3.length; ++var1) {
                    this.dialogManager.currentDialog.getChildById(var1 + 12).getComponentData().text = var3[var1];
                }

                return;
            case 1:
                this.dialogManager.currentDialog.getChildById(5).getComponentData().text = var4;

                for (var1 = 0; var1 < var3.length; ++var1) {
                    this.dialogManager.currentDialog.getChildById(9 + (var1 << 2)).getComponentData().text = var3[var1];
                }

                return;
            case 2:
                this.dialogManager.currentDialog.getChildById(10).setVisible(false);
                this.dialogManager.currentDialog.getChildById(8).getComponentData().text = "Trò chơi";
                this.dialogManager.currentDialog.getChildById(9).getComponentData().text = "Xác nhận";

                for (var1 = 0; var1 < var3.length; ++var1) {
                    this.dialogManager.currentDialog.getChildById(var1 + 5).getComponentData().text = var3[var1];
                }
            default:
        }
    }

    public final int c(int var1) {
        if (this.gameEngine.isKeyPressed(4100)) {
            this.dialogManager.currentDialog.handleAction(0);
            this.menuIndex = this.navigationData[0];
        } else if (this.gameEngine.isKeyPressed(8448)) {
            this.dialogManager.currentDialog.handleAction(1);
            this.menuIndex = this.navigationData[0];
        } else if (this.gameEngine.isKeyPressed(196640)) {
            this.dialogManager.removeDialog(this.DIALOG_PATHS[var1]);
            return this.menuIndex;
        }

        return -1;
    }

    public final void showRewardDialog(int[] var1, int[] var2, String[] var3, String[] var4) {
        this.menuIndex = 0;
        this.dialogManager.showDialog("/data/ui/taskOption.ui", 257, this);

        int var5;
        for (var5 = 0; var5 < var4.length; ++var5) {
            this.dialogManager.currentDialog.getChildById(var5 + 17).getComponentData().text = var4[var5];
        }

        for (var5 = 0; var5 < var1.length; ++var5) {
            if (this.dialogManager.currentDialog.getChildById((var5 << 1) + 13).getComponentData().renderer == null) {
                this.dialogManager.currentDialog.getChildById((var5 << 1) + 13)
                        .getComponentData().renderer = new SpriteRenderer();
                this.dialogManager.currentDialog.getChildById((var5 << 1) + 13).getComponentData().renderer.spriteType = 2;
                if (var1[var5] >= 3 && var1[var5] < 5) {
                    this.dialogManager.currentDialog.getChildById((var5 << 1) + 13).getComponentData().renderer
                            .setSpriteIndex((int) -1);
                    this.dialogManager.currentDialog.getChildById((var5 << 1) + 13).getComponentData().renderer
                            .initializeSprite(257, false, (byte) 0);
                } else {
                    this.dialogManager.currentDialog.getChildById((var5 << 1) + 13).getComponentData().renderer
                            .setSpriteIndex((int) 0);
                    this.dialogManager.currentDialog.getChildById((var5 << 1) + 13).getComponentData().renderer
                            .initializeSprite(258, false, (byte) 0);
                }
            }

            switch (var1[var5]) {
                case 0:
                    this.dialogManager.currentDialog.getChildById((var5 << 1) + 13).getComponentData().renderer
                            .setSpriteIndex((int) ResourceManager.gameDatabase[4][var2[var5]][1]);
                    this.dialogManager.currentDialog.getChildById((var5 << 1) + 14).getComponentData().text = var3[var5];
                    break;
                case 1:
                    this.dialogManager.currentDialog.getChildById((var5 << 1) + 13).getComponentData().renderer
                            .setSpriteIndex((int) ResourceManager.gameDatabase[3][var2[var5]][1]);
                    this.dialogManager.currentDialog.getChildById((var5 << 1) + 14).getComponentData().text = var3[var5];
                    break;
                case 2:
                    this.dialogManager.currentDialog.getChildById((var5 << 1) + 13).getComponentData().renderer
                            .setSpriteIndex((int) ResourceManager.gameDatabase[5][var2[var5]][1]);
                    this.dialogManager.currentDialog.getChildById((var5 << 1) + 14).getComponentData().text = var3[var5];
                    break;
                case 3:
                    this.dialogManager.currentDialog.getChildById((var5 << 1) + 13).getComponentData().renderer
                            .setSpriteIndex((int) 84);
                    this.dialogManager.currentDialog.getChildById((var5 << 1) + 14).getComponentData().text = var3[var5];
                    break;
                case 4:
                    this.dialogManager.currentDialog.getChildById((var5 << 1) + 13).getComponentData().renderer
                            .setSpriteIndex((int) 83);
                    this.dialogManager.currentDialog.getChildById((var5 << 1) + 14).getComponentData().text = var3[var5];
                case 5:
                default:
                    break;
                case 6:
                    this.dialogManager.currentDialog.getChildById(21).getComponentData().text = "#2"
                            + GameEngineBase.getLocalizedText(
                            (int) ResourceManager.getDatabaseValue((byte) 0, (short) var2[var5], (byte) 0))
                            + " #0" + var3[var5];
            }
        }

    }

    public final int aG() {
        if (this.gameEngine.isKeyPressed(4100)) {
            this.dialogManager.currentDialog.handleAction(0);
            this.menuIndex = this.navigationData[0];
        } else if (this.gameEngine.isKeyPressed(8448)) {
            this.dialogManager.currentDialog.handleAction(1);
            this.menuIndex = this.navigationData[0];
        } else {
            if (this.gameEngine.isKeyPressed(196640)) {
                this.dialogManager.removeDialog("/data/ui/taskOption.ui");
                return this.menuIndex;
            }

            if (this.gameEngine.isKeyPressed(262144)) {
                this.dialogManager.removeDialog("/data/ui/taskOption.ui");
                return 1;
            }
        }

        return -1;
    }

    public final void aH() {
        this.bE();
        this.setConfirmDialogText("Có dùng 10000 kim tiền để khôi phục trạng thái của tất cả sủng vật trong ba lô không?",
                "Tại chỗ sống lại");
    }

    private void bD() {
        byte var1 = -1;
        if (WorldGameSession.getInstance().currentRegionId == 9) {
            var1 = (byte) WorldGameSession.getInstance().currentAreaId;
        }

        if (var1 == -1) {
            WorldGameSession.getInstance();
            if (WorldGameSession.loadGameFlags()) {
                WorldGameSession.getInstance().cleanupCurrentScreen();
                this.player.isInitialized = false;
                GameScreenManager.getInstance().changeState((byte) 9);
            } else {
                GameScreenManager.getInstance().changeState((byte) 7);
            }
        } else {
            for (int var2 = 0; var2 < this.player.partySize; ++var2) {
                this.player.partyPokemon[var2].heal(1);
                this.player.partyPokemon[var2].setDisplayHp(1);
                this.player.partyPokemon[var2].activate();
            }

            WorldGameSession.currentInteractNpcId = -1;
            if (WorldGameSession.getInstance().currentAreaId == 0) {
                short[] var5 = new short[]{15, 194, 433, 16, 142, 357, 17, 97, 268, 18, 183, 224};

                for (int var3 = 0; var3 < WorldGameSession.getInstance().NPCs.length; ++var3) {
                    for (int var4 = 0; var4 < var5.length / 3; ++var4) {
                        if (WorldGameSession.getInstance().NPCs[var3].npcId == var5[var4 * 3]) {
                            WorldGameSession.getInstance().NPCs[var3].setWorldPosition(var5[var4 * 3 + 1], var5[var4 * 3 + 2]);
                        }
                    }
                }
            }

            WorldGameSession.getInstance().currentRegionId = this.REGION_SPAWN_DATA[var1 << 2];
            WorldGameSession.getInstance().currentAreaId = this.REGION_SPAWN_DATA[(var1 << 2) + 1];
            PlayerCharacter.getInstance().setWorldPosition(this.REGION_SPAWN_DATA[(var1 << 2) + 2], this.REGION_SPAWN_DATA[(var1 << 2) + 3]);
            short var10001 = this.REGION_SPAWN_DATA[(var1 << 2) + 2];
            short var10002 = this.REGION_SPAWN_DATA[(var1 << 2) + 3];
            PlayerCharacter.getInstance().attachedObject.setWorldPosition(var10001, var10002);
            PlayerCharacter var10000 = PlayerCharacter.getInstance();
            byte var6 = 2;
            var10000.currentDirection = var6;
            GameScreenManager.getInstance().changeState((byte) 10);
        }
    }

    public final void aI() {
        if (!this.gameEngine.isKeyPressed(196640)) {
            if (this.dialogState == 0 && this.gameEngine.isKeyPressed(786432)) {
                this.bD();
                this.bF();
            }

        } else {
            int var1;
            if (this.dialogState == 0) {
                if (!this.player.hasEnoughGold(10000)) {
                    this.showWarningDialog();
                    this.setWarningText("Kim tiền chưa đủ", "Nhấn nút 5 tiếp tục");
                    this.dialogState = 1;
                } else {
                    this.player.addGold(-10000);

                    for (var1 = 0; var1 < this.player.partySize; ++var1) {
                        this.player.partyPokemon[var1].fullRestore();
                        PokemonEntity var10000 = this.player.partyPokemon[var1];
                        PokemonEntity var10001 = this.player.partyPokemon[var1];
                        byte var3 = 1;
                        var10000.setDisplayHp(var10001.secondaryStates[var3]);
                    }

                    BattleSystemManager.B().C();
                    this.gameEngine.changeState((byte) 0);
                    this.bF();
                }
            } else {
                for (var1 = 0; var1 < this.player.partySize; ++var1) {
                    this.player.partyPokemon[var1].heal(1);
                    this.player.partyPokemon[var1].setDisplayHp(1);
                    this.player.partyPokemon[var1].activate();
                }

                if (GameEngineBase.paymentActive) {
                    this.gameEngine.changeState((byte) 102);
                } else {
                    this.bD();
                }

                this.hideWarningDialog();
            }
        }
    }

    public final void updateDialogSelection(int var1) {
        this.gameEngine.initPayment((byte) 0);
        this.gameEngine.setPaymentState((byte) 0);
        this.bE();
        int[] var2 = new int[]{4, 1, 4};
        String var3 = GameEngineBase.getLocalizedText((int) 599) + GameEngineBase.formatString(604, (int[]) var2);
        this.setConfirmDialogText(var3, "Kích hoạt trò chơi");
    }

    public final void aJ() {
        this.gameEngine.initPayment((byte) 1);
        this.gameEngine.setPaymentState((byte) 0);
        this.bE();
        int[] var1 = new int[]{2, 1, 2};
        String var2 = GameEngineBase.getLocalizedText((int) 600) + GameEngineBase.formatString(604, (int[]) var1);
        this.setConfirmDialogText(var2, "Mua sắm tất trúng cầu");
    }

    public final void aK() {
        this.dialogState = 0;
        this.gameEngine.initPayment((byte) 4);
        this.gameEngine.setPaymentState((byte) 0);
        this.bE();
        int[] var1 = new int[]{2, 1, 2};
        String var2 = GameEngineBase.getLocalizedText((int) 603) + GameEngineBase.formatString(604, (int[]) var1);
        this.setConfirmDialogText(var2, "Mua huy hiệu");
    }

    public final void aL() {
        this.dialogState = 0;
        this.gameEngine.initPayment((byte) 2);
        this.gameEngine.setPaymentState((byte) 0);
        this.bE();
        int[] var1 = new int[]{2, 1, 2};
        String var2 = GameEngineBase.getLocalizedText((int) 601) + GameEngineBase.formatString(604, (int[]) var1);
        this.setConfirmDialogText(var2, "Mua kim tiền");
    }

    private void bE() {
        this.dialogManager.showDialog("/data/ui/smsInfo.ui", 257, this);
        if (this.gameEngine instanceof WorldGameSession) {
            this.dialogManager.currentDialog.getChildById(6).setVisible(true);
            this.dialogManager.currentDialog.getChildById(7).setVisible(true);
            this.dialogManager.currentDialog.getChildById(10).setVisible(false);
            this.dialogManager.currentDialog.getChildById(11).setVisible(false);
        } else {
            this.dialogManager.currentDialog.getChildById(6).setVisible(false);
            this.dialogManager.currentDialog.getChildById(7).setVisible(false);
            this.dialogManager.currentDialog.getChildById(10).setVisible(true);
            this.dialogManager.currentDialog.getChildById(11).setVisible(true);
            this.dialogManager.currentDialog.getChildById(10).getComponentData().text = "Xác nhận";
            this.dialogManager.currentDialog.getChildById(11).getComponentData().text = "Quay lại";
        }
    }

    private void setConfirmDialogText(String var1, String var2) {
        this.dialogManager.currentDialog.getChildById(8).getComponentData().text = var1;
        this.dialogManager.currentDialog.getChildById(5).getComponentData().text = var2;
    }

    private void bF() {
        this.dialogManager.removeDialog("/data/ui/smsInfo.ui");
    }

    public final void aM() {
        if (!this.dialogManager.containsDialog("/data/ui/smsTip.ui")) {
            this.dialogManager.showDialog("/data/ui/smsTip.ui", 257, this);
        }

        for (int var1 = 0; var1 < 3; ++var1) {
            this.dialogManager.currentDialog.getChildById(var1 + 6).setVisible(false);
        }

        this.needsRedraw = true;
    }

    public final void showTutorialMessage(String var1) {
        this.needsRedraw = true;
        this.dialogManager.currentDialog.getChildById(5).getComponentData().text = var1;
    }

    public final void aN() {
        this.dialogManager.removeDialog("/data/ui/smsTip.ui");
    }

    public final void aO() {
        switch (this.gameEngine.getPaymentType()) {
            case 0:
                if (!this.gameEngine.isKeyPressed(16400) && !this.gameEngine.isKeyPressed(32832)) {
                    if (this.gameEngine.isKeyPressed(131072)) {
                        if (this.gameEngine.getTotalPaymentCount() > 1) {
                            this.gameEngine.setPaymentState((byte) 1);
                            return;
                        }

                        this.gameEngine.handlePaymentAction(1);
                        return;
                    }

                    if (this.gameEngine.isKeyPressed(786432)) {
                        this.bF();
                        this.gameEngine.setPaymentState((byte) 5);
                        this.gameEngine.changeState(this.gameEngine.previousState);
                        return;
                    }
                }
                break;
            case 1:
                if (this.gameEngine.isKeyPressed(131072)) {
                    this.gameEngine.handlePaymentDialogResponse(1);
                    return;
                }

                if (this.gameEngine.isKeyPressed(262144)) {
                    this.gameEngine.handlePaymentDialogResponse(2);
                    return;
                }
                break;
            case 2:
                boolean var1 = false;
                if (this.gameEngine.currentState != 100) {
                    var1 = true;
                } else {
                    if (this.listItemCount >= this.REWARD_DESCRIPTIONS.length && this.isMessageAnimationComplete()) {
                        var1 = true;
                    } else if (this.isMessageAnimationComplete()) {
                        this.showQuickMessage(this.REWARD_DESCRIPTIONS[this.listItemCount]);
                        ++this.listItemCount;
                    }

                    this.handleOpenBoxDialog();
                }

                if (var1 && this.bC() && this.gameEngine.isKeyPressed(917504)) {
                    this.listItemCount = 0;
                    if (this.gameEngine.isPaymentComplete()) {
                        this.bF();
                        this.aN();
                        this.gameEngine.changeState(this.gameEngine.previousState);
                    } else {
                        this.gameEngine.setPaymentState((byte) 5);
                    }

                    this.dialogState = 0;
                    return;
                }
                break;
            case 3:
                if (this.gameEngine.isKeyPressed(393216)) {
                    this.gameEngine.handlePaymentDialogResponse(1);
                }
        }

    }

    public final void showNPCDialogWithResponse(byte var1, int var2, int var3) {
        this.subMenuIndex = 0;
        this.questPageIndex = var1;
        this.questSelectedIndex = (byte) var2;
        label23:
        switch (var2) {
            case 0:
                this.dialogManager.showDialog("/data/ui/wharf1.ui", 257, this);
                this.dialogManager.currentDialog.getChildById(8).getComponentData().text = GameEngineBase.getLocalizedText(var3);
                var2 = 0;

                while (true) {
                    if (var2 >= this.REWARD_TEXT_IDS[var1].length) {
                        break label23;
                    }

                    this.dialogManager.currentDialog.getChildById(var2 + 5).getComponentData().text = GameEngineBase
                            .getLocalizedText((int) this.REWARD_TEXT_IDS[var1][var2]);
                    ++var2;
                }
            case 1:
                this.dialogManager.showDialog("/data/ui/wharf2.ui", 257, this);
                this.dialogManager.currentDialog.getChildById(10).getComponentData().text = GameEngineBase.getLocalizedText(var3);

                for (var2 = 0; var2 < this.REWARD_TEXT_IDS[var1].length; ++var2) {
                    this.dialogManager.currentDialog.getChildById(var2 + 5).getComponentData().text = GameEngineBase
                            .getLocalizedText((int) this.REWARD_TEXT_IDS[var1][var2]);
                }
        }

        this.dialogManager.currentDialog.getChildById(5 + this.REWARD_TEXT_IDS[var1].length).getComponentData().text = "Không ra hàng";
    }

    public final void aP() {
        if (this.gameEngine.isKeyPressed(4100) && !this.isPopupActive()) {
            this.dialogManager.currentDialog.handleAction(0);
        } else if (this.gameEngine.isKeyPressed(8448) && !this.isPopupActive()) {
            this.dialogManager.currentDialog.handleAction(1);
        } else if (this.gameEngine.isKeyPressed(196640) && !this.isPopupActive()) {
            if (this.subMenuIndex == this.QUEST_LOCATION_DATA[this.questPageIndex].length / 9) {
                switch (this.questSelectedIndex) {
                    case 0:
                        this.dialogManager.removeDialog("/data/ui/wharf1.ui");
                        break;
                    case 1:
                        this.dialogManager.removeDialog("/data/ui/wharf2.ui");
                }

                if (WorldGameSession.currentInteractNpcId != -1 && WorldGameSession.getInstance().NPCs[WorldGameSession.currentInteractNpcId].getInteractionState() == 0) {
                    WorldGameSession.getInstance().createInteractionMarker((byte) 13, WorldGameSession.getInstance().NPCs[WorldGameSession.currentInteractNpcId].worldX,
                            WorldGameSession.getInstance().NPCs[WorldGameSession.currentInteractNpcId].worldY - 40,
                            WorldGameSession.getInstance().NPCs[WorldGameSession.currentInteractNpcId]);
                }

                this.gameEngine.changeState((byte) 0);
            } else {
                label59:
                {
                    short var10001 = this.QUEST_LOCATION_DATA[this.questPageIndex][this.subMenuIndex * 9 + 6];
                    short[] var10002 = this.QUEST_LOCATION_DATA[this.questPageIndex];
                    if (WorldGameSession.getInstance().questManager.questStates[WorldGameSession.getAreaIndex(var10001,
                            var10002[this.subMenuIndex * 9 + 7])] != null) {
                        var10001 = this.QUEST_LOCATION_DATA[this.questPageIndex][this.subMenuIndex * 9 + 6];
                        var10002 = this.QUEST_LOCATION_DATA[this.questPageIndex];
                        if (WorldGameSession.getInstance().questManager.questStates[WorldGameSession.getAreaIndex(var10001,
                                var10002[this.subMenuIndex * 9 + 7])][this.QUEST_LOCATION_DATA[this.questPageIndex][this.subMenuIndex * 9 + 8]] == 3) {
                            WorldGameSession.getInstance().currentRegionId = this.QUEST_LOCATION_DATA[this.questPageIndex][this.subMenuIndex * 9];
                            WorldGameSession.getInstance().currentAreaId = this.QUEST_LOCATION_DATA[this.questPageIndex][this.subMenuIndex * 9 + 1];
                            WorldGameSession.getInstance().spawnPositionX = this.QUEST_LOCATION_DATA[this.questPageIndex][this.subMenuIndex * 9 + 2];
                            WorldGameSession.getInstance().spawnPositionY = this.QUEST_LOCATION_DATA[this.questPageIndex][this.subMenuIndex * 9 + 3];
                            WorldGameSession.playerDirection = (byte) this.QUEST_LOCATION_DATA[this.questPageIndex][this.subMenuIndex * 9 + 4];
                            WorldGameSession.getInstance().lastInteractedNpcId = -1;
                            TransitionScreen.getInstance()
                                    .setTransitionDirection((byte) this.QUEST_LOCATION_DATA[this.questPageIndex][this.subMenuIndex * 9 + 5]);
                            this.gameEngine.changeState((byte) 29);
                            switch (this.questSelectedIndex) {
                                case 0:
                                    this.dialogManager.removeDialog("/data/ui/wharf1.ui");
                                    break label59;
                                case 1:
                                    this.dialogManager.removeDialog("/data/ui/wharf2.ui");
                                default:
                                    break label59;
                            }
                        }
                    }

                    this.showQuickMessage("Đường thủy chưa mở");
                }
            }
        } else if (this.gameEngine.isKeyPressed(262144) && !this.isPopupActive()) {
            if (WorldGameSession.currentInteractNpcId != -1 && WorldGameSession.getInstance().NPCs[WorldGameSession.currentInteractNpcId].getInteractionState() == 0) {
                WorldGameSession.getInstance().createInteractionMarker((byte) 13, WorldGameSession.getInstance().NPCs[WorldGameSession.currentInteractNpcId].worldX,
                        WorldGameSession.getInstance().NPCs[WorldGameSession.currentInteractNpcId].worldY - 40,
                        WorldGameSession.getInstance().NPCs[WorldGameSession.currentInteractNpcId]);
            }

            switch (this.questSelectedIndex) {
                case 0:
                    this.dialogManager.removeDialog("/data/ui/wharf1.ui");
                    break;
                case 1:
                    this.dialogManager.removeDialog("/data/ui/wharf2.ui");
            }

            this.gameEngine.changeState((byte) 0);
        }

        this.handleOpenBoxDialog();
    }

    public final void aQ() {
        this.menuIndex = 0;
        this.dialogManager.showDialog("/data/ui/shopbuy.ui", 257, this);
        this.menuIndex = 0;
        this.dialogState = 0;
        ((UIContainerComponent) this.dialogManager.currentDialog.getChildById(0)).primaryListComponent.totalItemCount = 1;
        ((UIContainerComponent) this.dialogManager.currentDialog.getChildById(0)).primaryListComponent.setDisplayMode(0);
        this.dialogManager.currentDialog.getChildById(41).setVisible(false);
        this.dialogManager.currentDialog.getChildById(43).setVisible(false);
        this.dialogManager.currentDialog.getChildById(5).getComponentData().text = "Mua";
        this.dialogManager.currentDialog.getChildById(57).setVisible(true);
        this.dialogManager.currentDialog.getChildById(58).setVisible(true);
        this.dialogManager.currentDialog.getChildById(57).getComponentData().text = "Mua sắm";
        this.dialogManager.currentDialog.getChildById(58).getComponentData().text = "Quay lại";
        this.dialogManager.currentDialog.getChildById(39).setVisible(false);
        this.dialogManager.currentDialog.getChildById(40).setVisible(false);
        this.listScrollOffset = ((UIContainerComponent) this.dialogManager.currentDialog
                .getChildById(0)).primaryListComponent.scrollOffset;
        this.listSelectedIndex = ((UIContainerComponent) this.dialogManager.currentDialog
                .getChildById(0)).primaryListComponent.selectedIndex;
        if (this.dialogManager.currentDialog.getChildById(51).getComponentData().renderer == null) {
            this.dialogManager.currentDialog.getChildById(51).getComponentData().renderer = new SpriteRenderer();
            this.dialogManager.currentDialog.getChildById(51).getComponentData().renderer.setSpriteIndex((int) 0);
            this.dialogManager.currentDialog.getChildById(51).getComponentData().renderer.spriteType = 2;
            this.dialogManager.currentDialog.getChildById(51).getComponentData().renderer.initializeSprite(258, false,
                    (byte) -1);
        }

        this.dialogManager.currentDialog.getChildById(51).getComponentData().renderer
                .setSpriteIndex((int) ResourceManager.gameDatabase[5][0][1]);
        this.dialogManager.currentDialog.getChildById(14).getComponentData().text = GameEngineBase
                .getLocalizedText((int) ResourceManager.gameDatabase[5][0][0]);
        this.dialogManager.currentDialog.getChildById(15).getComponentData().text = "5000";
        this.dialogManager.currentDialog.getChildById(45).getComponentData().renderer.setSpriteIndex((int) 84);
        this.dialogManager.currentDialog.getChildById(56).getComponentData().text = "Ấp trứng ra sủng vật";
        this.dialogManager.currentDialog.getChildById(44).getComponentData().text = "" + this.player.getGold();
        this.dialogManager.currentDialog.getChildById(38).setOffsetY(
                102 + this.listSelectedIndex * 84 / ResourceManager.gameDatabase[5].length,
                this.dialogManager.currentDialog.getUIContainerComponent());
    }

    private void bG() {
        this.dialogManager.removeDialog("/data/ui/shopbuy.ui");
    }

    public final int aR() {
        if (this.gameEngine.isKeyPressed(196640)) {
            if (this.dialogState == 0) {
                if (this.player.hasEnoughGold(5000)) {
                    if (this.player.isSkillActive(0)) {
                        this.showWarningDialog();
                        this.setWarningText("Đã có trứng sủng vật, không cần mua sắm", "Nhấn nút 5 tiếp tục");
                        this.dialogState = 2;
                    } else {
                        this.player.useSkillOnPokemon(0, -1);
                        this.showWarningDialog();
                        this.setWarningText("Đã thành công mua sắm #2 trứng sủng vật", "Nhấn nút 5 tiếp tục");
                        this.dialogState = 2;
                    }
                } else {
                    this.showWarningDialog();
                    this.setWarningText("Kim tiền chưa đủ", "Nhấn nút 5 tiếp tục");
                    this.dialogState = 1;
                }
            } else if (this.dialogState > 0) {
                this.hideWarningDialog();
                if (this.dialogState == 1) {
                    this.gameEngine.changeState((byte) 102);
                } else if (this.dialogState == 2) {
                    QuestManager.questOptionIndex = 0;
                    this.bG();
                    this.gameEngine.changeState((byte) 0);
                }
            }
        } else if (this.gameEngine.isKeyPressed(262144) && this.dialogState == 0) {
            QuestManager.questOptionIndex = 1;
            this.bG();
            this.gameEngine.changeState((byte) 0);
        }

        return -1;
    }

    public final void aS() {
        this.dialogManager.showDialog("/data/ui/wharf2.ui", 257, this);
        ((UIContainerComponent) this.dialogManager.currentDialog
                .getChildById(0)).primaryListComponent.selectedIndex = this.npcDialogIndex;
        this.dialogState = 0;
        this.dialogManager.currentDialog.getChildById(10).getComponentData().text = "Tiện lợi điếm";
        this.dialogManager.currentDialog.getChildById(12).getComponentData().text = "Tiến vào";

        for (int var1 = 0; var1 < this.GUIDE_MENU_ITEMS.length; ++var1) {
            this.dialogManager.currentDialog.getChildById(var1 + 5).getComponentData().text = this.GUIDE_MENU_ITEMS[var1];
        }

    }

    public final void aT() {
        if (this.gameEngine.isKeyPressed(4100) && !this.isPopupActive()) {
            this.dialogManager.currentDialog.handleAction(0);
        } else if (this.gameEngine.isKeyPressed(8448) && !this.isPopupActive()) {
            this.dialogManager.currentDialog.handleAction(1);
        } else if (this.gameEngine.isKeyPressed(196640) && !this.isPopupActive()) {
            if (this.dialogState == 0) {
                switch (this.npcDialogIndex) {
                    case 0:
                        this.dialogManager.removeDialog("/data/ui/wharf2.ui");
                        this.gameEngine.changeState((byte) 31);
                        return;
                    case 1:
                    case 2:
                        WorldGameSession.getInstance();
                        if (WorldGameSession.isTutorialActive) {
                            this.dialogManager.removeDialog("/data/ui/wharf2.ui");
                            this.subMenuIndex = 0;
                            this.gameEngine.changeState((byte) 7);
                            return;
                        }

                        this.showWarningDialog();
                        this.dialogState = 1;
                        this.setWarningText("Công năng theo đạo học sau mở ra", "Nhấn nút 5 tiếp tục");
                        return;
                    case 3:
                        this.dialogManager.removeDialog("/data/ui/wharf2.ui");
                        this.gameEngine.changeState((byte) 32);
                        return;
                    case 4:
                        this.dialogManager.removeDialog("/data/ui/wharf2.ui");
                        this.gameEngine.changeState((byte) 0);
                    default:
                }
            } else {
                this.dialogState = 0;
                this.hideWarningDialog();
                this.needsRedraw = true;
            }
        } else {
            if (this.gameEngine.isKeyPressed(262144) && this.dialogState == 0 && !this.isPopupActive()) {
                this.dialogManager.removeDialog("/data/ui/wharf2.ui");
                this.gameEngine.changeState((byte) 0);
            }

        }
    }

    public final void action(int[] navigationData, int[] selectionData) {
        this.navigationData = navigationData;
        if (this.gameEngine instanceof WorldGameSession) {
            switch (((WorldGameSession) this.gameEngine).currentState) {
                case 0:
                    return;
                case 1:
                    this.menuIndex = navigationData[0];
                    return;
                case 2:
                case 26:
                case 32:
                    this.handleShopAction(navigationData);
                    return;
                case 3:
                    if (this.dialogState != 0) {
                        this.optionIndex = navigationData[0];
                        return;
                    }

                    this.menuIndex = navigationData[0];
                    this.updateShopItemDisplay();
                    break;
                case 4:
                    return;
                case 5:
                    this.menuIndex = navigationData[1];
                    this.updateVehicleMenu();
                    return;
                case 6:
                    this.menuIndex = navigationData[0];
                    if (!GameEngineBase.paymentActive) {
                        this.dialogManager.currentDialog.getChildById(14).getComponentData().text = GameEngineBase
                                .getLocalizedText(606 + this.menuIndex);
                        return;
                    }

                    this.dialogManager.currentDialog.getChildById(14).getComponentData().text = GameEngineBase
                            .getLocalizedText(605 + this.menuIndex);
                    break;
                case 7:
                    this.handlePokemonAction(navigationData);
                    return;
                case 8:
                    if (navigationData[0] >= 0) {
                        this.subMenuIndex = navigationData[0];
                    }

                    if (navigationData[1] >= 0) {
                        this.menuIndex = navigationData[1];
                    }

                    this.updateInventoryDisplay();
                    return;
                case 9:
                    this.subMenuIndex = navigationData[1];
                    return;
                case 10:
                    this.menuIndex = navigationData[1];
                    switch (this.menuIndex) {
                        case 0:
                            this.subMenuIndex = navigationData[0];
                            return;
                        case 1:
                            this.optionIndex = navigationData[0];
                        default:
                            return;
                    }
                case 11:
                    this.subMenuIndex = navigationData[0];
                    this.menuIndex = navigationData[1];
                    return;
                case 12:
                    this.menuIndex = navigationData[1];
                    return;
                case 13:
                    if (this.dialogState == 0) {
                        this.menuIndex = navigationData[0];
                        return;
                    }

                    this.subMenuIndex = navigationData[0];
                    return;
                case 14:
                    this.subMenuIndex = navigationData[0];
                    return;
                case 15:
                    this.subMenuIndex = navigationData[0];
                    return;
                case 16:
                    this.menuIndex = navigationData[0];
                    return;
                case 17:
                case 18:
                case 19:
                    this.subMenuIndex = navigationData[0];
                    return;
                case 20:
                    this.subMenuIndex = navigationData[1];
                    return;
                case 24:
                    this.subMenuIndex = navigationData[0];
                    return;
                case 27:
                    this.npcDialogIndex = navigationData[0];
                case 21:
                case 22:
                case 23:
                case 25:
                case 29:
                case 30:
                case 31:
                default:
                    return;
                case 28:
                    this.subMenuIndex = navigationData[0];
                    return;
            }
        } else if (this.gameEngine instanceof BattleSystemManager) {
            switch (((BattleSystemManager) this.gameEngine).currentState) {
                case 2:
                    return;
                case 3:
                    this.battleMenuIndex = navigationData[0];
                    return;
                case 4:
                    this.menuIndex = navigationData[0];
                    this.updateSkillDisplay();
                    return;
                case 5:
                    this.handlePokemonAction(navigationData);
                    return;
                case 6:
                    return;
                case 7:
                    return;
                case 8:
                    return;
                case 9:
                    return;
                case 10:
                    return;
                case 11:
                    this.handleShopAction(navigationData);
                    return;
                case 12:
                    return;
                case 13:
                    return;
                case 14:
                    return;
                case 15:
                    return;
                case 16:
                    this.subMenuIndex = navigationData[0];
                    this.updatePokemonDetailDisplay(this.subMenuIndex);
                    return;
                case 17:
                    return;
                case 18:
                    return;
                case 19:
                    return;
                case 20:
                    this.tabIndex = navigationData[1];
                    this.dialogManager.currentDialog.getChildById(20 + this.tabIndex).setVisible(true);
                    return;
                case 21:
                    this.menuIndex = navigationData[0];
                case 23:
                    this.menuIndex = navigationData[0];
                case 22:
            }
        }

    }

    private void handlePokemonAction(int[] var1) {
        if (this.dialogState == 0) {
            this.menuIndex = var1[0];
            this.updatePokemonDetailDisplay(this.menuIndex);
        } else if (this.dialogState == 1) {
            this.subMenuIndex = var1[0];
        } else {
            if (this.dialogState == 2) {
                this.optionIndex = var1[0];
                switch (this.subMenuIndex) {
                    case 0:
                        this.updateSkillDisplay();
                }
            }

        }
    }

    private void handleShopAction(int[] var1) {
        if (this.dialogState == 0) {
            this.menuIndex = var1[0];
        } else {
            this.optionIndex = var1[0];
        }
    }
}
