package layout;

import a.GameUtils;

import javax.microedition.lcdui.Graphics;

public final class Dialog {
    private IComponent[] childComponents = new IComponent[200];
    private int childCount = 0;
    private int currentIndex = -1;
    private RootComponent rootComponent;
    private int[] allowedActions = new int[]{0, 1, 2, 3, 5, 6, 7, 14, 15, 16, 17, 18, 19, 20, 21, 22, 23, 24, 25};
    private int[] selectedIndices = null;
    private int[] activeIndices = null;
    private DialogConfig config;
    private DialogHandler handler;

    public Dialog(DialogHandler var1) {
        this.handler = var1;
        this.resetDialog();
    }

    private void resetDialog() {
        this.currentIndex = -1;
        this.rootComponent = new RootComponent();
        this.rootComponent.setSelectedComponentId(0);
        this.rootComponent.setActiveComponentId(-1);
        this.selectedIndices = GameUtils.initializeArrayWithMinusOne(100);
        this.initializeActiveIndices();
    }

    public final RootComponent getRootComponent() {
        return this.rootComponent;
    }

    public final void setConfig(DialogConfig var1) {
        this.config = var1;
    }

    public final void loadDialog(String dialogId, int var2, boolean var3) {
        this.resetDialog();
        byte[] data = new byte[20000];
        GameUtils.readBytesFromStream(data, dialogId, 0);
        int[] readIndex = new int[]{0};
        this.currentIndex = GameUtils.readShortFromBytes(data, readIndex);
        this.currentIndex = 0;
        GameUtils.readShortFromBytes(data, readIndex);
        byte var4 = GameUtils.readByte(data, readIndex);
        this.rootComponent.setZIndex(var4);
        short var7 = GameUtils.readShortFromBytes(data, readIndex);
        this.rootComponent.setSelectedComponentId(var7);
        var7 = GameUtils.readShortFromBytes(data, readIndex);
        this.rootComponent.setOffsetX(var7, this.rootComponent);
        var7 = GameUtils.readShortFromBytes(data, readIndex);
        this.rootComponent.setOffsetY(var7, this.rootComponent);
        var7 = GameUtils.readShortFromBytes(data, readIndex);
        this.rootComponent.setWidth(var7, this.rootComponent);
        var7 = GameUtils.readShortFromBytes(data, readIndex);
        this.rootComponent.setHeight(var7, this.rootComponent);
        this.childComponents[this.childCount] = this.rootComponent;
        this.childCount = 1;
        this.parseDialogData(data, readIndex, this.rootComponent, var2, false);
        this.selectedIndices = GameUtils.initializeArrayWithMinusOne(50);
        this.activateComponent(this.rootComponent, -1);
    }

    private void parseDialogData(byte[] data, int[] var2, RootComponent var3, int var4, boolean var5) {
        byte var6 = GameUtils.readByte(data, var2);
        int var8;
        if (var6 > 0) {
            byte[][] var7 = new byte[var6][4];

            for (var8 = 0; var8 < var6; ++var8) {
                var7[var8][0] = GameUtils.readByte(data, var2);
                var7[var8][1] = GameUtils.readByte(data, var2);
                var7[var8][2] = GameUtils.readByte(data, var2);
                var7[var8][3] = GameUtils.readByte(data, var2);
            }

            var3.setDialogData(var7);
        }

        byte var19 = GameUtils.readByte(data, var2);

        short var11;
        short var12;
        int var14;
        int var15;
        int var26;
        short var32;
        for (var8 = 0; var8 < var19; ++var8) {
            var6 = GameUtils.readByte(data, var2);
            Component var9 = new Component(var6);
            var9.C38_f581 = GameUtils.readByte(data, var2) != 0;
            var9.C38_f577 = GameUtils.readShortFromBytes(data, var2);
            var9.C38_f574 = GameUtils.readShortFromBytes(data, var2);
            var9.C38_f582 = GameUtils.readByte(data, var2);
            var9.C38_f583 = GameUtils.readByte(data, var2);

            int var10;
            for (var10 = 0; var10 < var9.C38_f574; ++var10) {
                var11 = GameUtils.readShortFromBytes(data, var2);
                var9.C38_f575[var10] = var11;
                byte[] var13 = new byte[var12 = GameUtils.readShortFromBytes(data, var2)];

                for (var14 = 0; var14 < var12; ++var14) {
                    var13[var14] = GameUtils.readByte(data, var2);
                }

                var9.C38_f592.addElement(GameUtils.a(var13));
            }

            var9.C38_f576 = new int[var9.C38_f577 + this.allowedActions.length][][];

            for (var10 = 0; var10 < var9.C38_f576.length; ++var10) {
                var9.C38_f576[var10] = new int[0][];
            }

            short var25 = GameUtils.readShortFromBytes(data, var2);

            for (var26 = 0; var26 < var25; ++var26) {
                var12 = GameUtils.readShortFromBytes(data, var2);
                int[][] var36 = new int[var32 = GameUtils.readShortFromBytes(data, var2)][];

                for (var15 = 0; var15 < var32; ++var15) {
                    var36[var15] = new int[5];
                    var36[var15][0] = GameUtils.readShortFromBytes(data, var2);
                    var36[var15][1] = GameUtils.readShortFromBytes(data, var2);
                    var36[var15][2] = GameUtils.readShortFromBytes(data, var2);
                    var36[var15][3] = GameUtils.readShortFromBytes(data, var2);
                    var36[var15][4] = GameUtils.readShortFromBytes(data, var2);
                }

                var9.C38_f576[var12] = var36;
            }

            if (var6 == 0) {
                var3.otherChildComponent = var9;
            } else {
                var3.additionalChildComponent = var9;
            }
        }

        short var21 = GameUtils.readShortFromBytes(data, var2);

        for (int var18 = 0; var18 < var21; ++var18) {
            byte var22;
            if ((var22 = GameUtils.readByte(data, var2)) == 0) {
                RootComponent var29;
                (var29 = new RootComponent()).setZIndex(var22);
                var29.setSelectedComponentId(GameUtils.readShortFromBytes(data, var2));
                var29.setOffsetX(GameUtils.readShortFromBytes(data, var2), this.getRootComponent());
                var29.setOffsetY(GameUtils.readShortFromBytes(data, var2), this.getRootComponent());
                var29.setWidth(GameUtils.readShortFromBytes(data, var2), this.getRootComponent());
                var29.setHeight(GameUtils.readShortFromBytes(data, var2), this.getRootComponent());
                var29.setActiveComponentId(var3.getSelectedComponentId());
                if (var3.otherChildComponent != null) {
                    for (var26 = 0; var26 < var3.otherChildComponent.C38_f575.length; ++var26) {
                        if (var3.otherChildComponent.C38_f575[var26] == var29.getSelectedComponentId()) {
                            var29.setChildComponent(var3.otherChildComponent);
                            break;
                        }
                    }
                }

                if (var3.additionalChildComponent != null) {
                    for (var26 = 0; var26 < var3.additionalChildComponent.C38_f575.length; ++var26) {
                        if (var3.additionalChildComponent.C38_f575[var26] == var29.getSelectedComponentId()) {
                            var29.setChildComponent(var3.additionalChildComponent);
                            break;
                        }
                    }
                }

                this.childComponents[this.childCount] = var29;
                ++this.childCount;
                var3.getComponents()[var18] = var29;
                this.parseDialogData(data, var2, (RootComponent) var3.getComponents()[var18], var4, var5);
            } else if (var22 == 1) {
                DialogComponent var28;
                (var28 = new DialogComponent()).setZIndex(var22);
                var28.setSelectedComponentId(GameUtils.readShortFromBytes(data, var2));
                var28.setOffsetX(GameUtils.readShortFromBytes(data, var2), this.getRootComponent());
                var28.setOffsetY(GameUtils.readShortFromBytes(data, var2), this.getRootComponent());
                var28.setWidth(GameUtils.readShortFromBytes(data, var2), this.getRootComponent());
                var28.setHeight(GameUtils.readShortFromBytes(data, var2), this.getRootComponent());
                var28.initializeDialog();
                var28.dialogConfig = this.config;
                byte[] var33 = new byte[var11 = GameUtils.readShortFromBytes(data, var2)];

                for (int var35 = 0; var35 < var11; ++var35) {
                    var33[var35] = GameUtils.readByte(data, var2);
                }

                var28.getComponentData().C12_f179 = GameUtils.a(var33);
                var28.getComponentData().C12_f182 = GameUtils.readByte(data, var2);
                var28.getComponentData().C12_f183 = GameUtils.readByte(data, var2);
                var28.getComponentData().C12_f184 = GameUtils.readByte(data, var2) != 0;
                var28.getComponentData().C12_f185 = GameUtils.e(data, var2);
                var28.getComponentData().C12_f186 = GameUtils.e(data, var2);
                var28.getComponentData().C12_f187 = GameUtils.e(data, var2);
                var32 = GameUtils.readShortFromBytes(data, var2);
                byte var39 = GameUtils.readByte(data, var2);
                if (var32 < 0) {
                    var28.getComponentData().C12_f191 = null;
                } else {
                    var28.getComponentData().C12_f191 = new SpriteRenderer();
                    var28.getComponentData().C12_f191.spriteType = var39;
                    var28.getComponentData().C12_f191.setSpriteIndex((int) var32);
                }

                var28.getComponentData().C12_f192 = GameUtils.e(data, var2);
                var28.getComponentData().C12_f193 = GameUtils.e(data, var2);
                var28.getComponentData().C12_f194 = GameUtils.e(data, var2);
                short var38 = GameUtils.readShortFromBytes(data, var2);
                var19 = GameUtils.readByte(data, var2);
                if (var38 < 0) {
                    var28.getComponentData().C12_f195 = null;
                } else {
                    var28.getComponentData().C12_f195 = new SpriteRenderer();
                    var28.getComponentData().C12_f195.setSpriteIndex((int) var38);
                    var28.getComponentData().C12_f195.spriteType = var19;
                }

                var28.getComponentData().C12_f190 = GameUtils.readByte(data, var2);
                if (var28.getComponentData().C12_f191 != null) {
                    var28.getComponentData().C12_f191.initializeSprite(var4, var5, var28.getComponentData().C12_f190);
                }

                if (var28.getComponentData().C12_f195 != null) {
                    var28.getComponentData().C12_f195.initializeSprite(var4, var5, var28.getComponentData().C12_f190);
                }

                var28.setActiveComponentId(var3.getSelectedComponentId());
                int var24;
                if (var3.otherChildComponent != null) {
                    for (var24 = 0; var24 < var3.otherChildComponent.C38_f575.length; ++var24) {
                        if (var3.otherChildComponent.C38_f575[var24] == var28.getSelectedComponentId()) {
                            var28.setChildComponent(var3.otherChildComponent);
                            break;
                        }
                    }
                }

                if (var3.additionalChildComponent != null) {
                    for (var24 = 0; var24 < var3.additionalChildComponent.C38_f575.length; ++var24) {
                        if (var3.additionalChildComponent.C38_f575[var24] == var28.getSelectedComponentId()) {
                            var28.setChildComponent(var3.additionalChildComponent);
                            break;
                        }
                    }
                }

                this.childComponents[this.childCount] = var28;
                ++this.childCount;
                var3.getComponents()[var18] = var28;
                var28.focusedState = GameUtils.readByte(data, var2);
                var28.enabledState = GameUtils.readByte(data, var2);
            } else if (var22 == 2) {
                GridComponent var27 = new GridComponent();
                var27.setSelectedComponentId(GameUtils.readShortFromBytes(data, var2));
                var27.setOffsetX(GameUtils.readShortFromBytes(data, var2), this.getRootComponent());
                var27.setOffsetY(GameUtils.readShortFromBytes(data, var2), this.getRootComponent());
                var27.setCellWidth((int) GameUtils.readByte(data, var2));
                var27.setCellHeight(GameUtils.readByte(data, var2));
                var27.setCellSpacingX(GameUtils.readByte(data, var2));
                var27.setCellSpacingY(GameUtils.readByte(data, var2));
                var27.setTotalColumns(GameUtils.readByte(data, var2));
                var27.setTotalRows(GameUtils.readByte(data, var2));
                var27.setVisibleColumns(GameUtils.readByte(data, var2));
                var27.setVisibleRows(GameUtils.readByte(data, var2));
                var27.setCursorOffsetX(GameUtils.readByte(data, var2));
                var27.setCursorOffsetY(GameUtils.readByte(data, var2));
                var27.setHorizontalWrapMode(GameUtils.readByte(data, var2));
                var27.setHorizontalScrollMode(GameUtils.readByte(data, var2));
                var27.setVerticalWrapMode(GameUtils.readByte(data, var2));
                var27.setVerticalScrollMode(GameUtils.readByte(data, var2));
                var27.setScrollOffsetX(GameUtils.readByte(data, var2));
                var27.setScrollOffsetY(GameUtils.readByte(data, var2));
                var27.backgroundColor = GameUtils.e(data, var2);
                var11 = GameUtils.readShortFromBytes(data, var2);
                byte var30 = GameUtils.readByte(data, var2);
                if (var11 < 0) {
                    var27.backgroundRenderer = null;
                } else {
                    var27.backgroundRenderer = new SpriteRenderer();
                    var27.backgroundRenderer.setSpriteIndex((int) var11);
                    var27.backgroundRenderer.spriteType = var30;
                    var27.backgroundRenderer.initializeSprite(var4, var5, var30);
                }

                var11 = GameUtils.readShortFromBytes(data, var2);
                var30 = GameUtils.readByte(data, var2);
                if (var11 < 0) {
                    var27.selectionRenderer = null;
                } else {
                    var27.selectionRenderer = new SpriteRenderer();
                    var27.selectionRenderer.setSpriteIndex((int) var11);
                    var27.selectionRenderer.spriteType = var30;
                    var27.selectionRenderer.initializeSprite(var4, var5, var30);
                }

                var27.setSelectedCellIndex(GameUtils.readShortFromBytes(data, var2));
                byte var34;
                if ((var34 = GameUtils.readByte(data, var2)) == 0) {
                    var27.gridCells = var27.createNavigationGrid();
                } else if (var34 == 1) {
                    var27.gridCells = GridComponent.createEmptyGrid(var27.getTotalColumns(), var27.getTotalRows());
                    short var37 = GameUtils.readShortFromBytes(data, var2);

                    for (var15 = 0; var15 < var37; ++var15) {
                        short var20 = GameUtils.readShortFromBytes(data, var2);
                        short var23 = GameUtils.readShortFromBytes(data, var2);
                        byte var31 = GameUtils.readByte(data, var2);
                        var12 = GameUtils.readShortFromBytes(data, var2);
                        var32 = GameUtils.readShortFromBytes(data, var2);
                        short var16 = GameUtils.readShortFromBytes(data, var2);
                        short var17 = GameUtils.readShortFromBytes(data, var2);
                        var27.gridCells[var20] = new GridCell(var23, var31, var12, var32, var16, var17);
                    }
                }

                var27.setActiveComponentId(var3.getSelectedComponentId());
                Component var10000;
                if (var3.otherChildComponent != null) {
                    for (var14 = 0; var14 < var3.otherChildComponent.C38_f575.length; ++var14) {
                        if (var3.otherChildComponent.C38_f575[var14] == var27.getSelectedComponentId()) {
                            var10000 = var3.otherChildComponent;
                            break;
                        }
                    }
                }

                if (var3.additionalChildComponent != null) {
                    for (var14 = 0; var14 < var3.additionalChildComponent.C38_f575.length; ++var14) {
                        if (var3.additionalChildComponent.C38_f575[var14] == var27.getSelectedComponentId()) {
                            var10000 = var3.additionalChildComponent;
                            break;
                        }
                    }
                }

                this.childComponents[this.childCount] = var27;
                ++this.childCount;
                var3.getComponents()[var18] = var27;
            }
        }

    }

    private Rectangle calculateBounds(IComponent var1) {
        if (var1.getZIndex() != 0) {
            return new Rectangle(var1.getOffsetX(), var1.getOffsetY(), var1.getWidth(), var1.getHeight());
        } else {
            RootComponent var2;
            if ((var2 = (RootComponent) var1).getComponents() != null && var2.getComponents()[0] != null) {
                int var8 = this.calculateBounds(var2.getComponents()[0]).x;
                int var3 = this.calculateBounds(var2.getComponents()[0]).x + this.calculateBounds(var2.getComponents()[0]).width;
                int var4 = this.calculateBounds(var2.getComponents()[0]).y;
                int var5 = this.calculateBounds(var2.getComponents()[0]).y + this.calculateBounds(var2.getComponents()[0]).height;

                for (int var6 = 0; var6 < var2.getComponents().length && var2.getComponents()[var6] != null; ++var6) {
                    Rectangle var7 = this.calculateBounds(var2.getComponents()[var6]);
                    if (var8 > var7.x) {
                        var8 = var7.x;
                    }

                    if (var3 < var7.x + var7.width) {
                        var3 = var7.x + var7.width;
                    }

                    if (var4 > var7.y) {
                        var4 = var7.y;
                    }

                    if (var5 < var7.y + var7.height) {
                        var5 = var7.y + var7.height;
                    }
                }

                return new Rectangle(var8, var4, var3 - var8, var5 - var4);
            } else {
                return new Rectangle(var1.getOffsetX(), var1.getOffsetY(), var1.getWidth(), var1.getHeight());
            }
        }
    }

    public final void render(Graphics var1) {
        Graphics var2;
        RootComponent var3;
        Dialog var15;
        Component var10000;
        label96:
        {
            var3 = this.rootComponent;
            var2 = var1;
            var15 = this;
            if (var3.otherChildComponent != null || var3.additionalChildComponent != null) {
                if (var3.otherChildComponent == null && var3.additionalChildComponent != null) {
                    var10000 = var3.additionalChildComponent;
                    break label96;
                }

                if (var3.otherChildComponent != null && var3.additionalChildComponent == null) {
                    var10000 = var3.otherChildComponent;
                    break label96;
                }
            }

            var10000 = null;
        }

        Component var6 = var10000;
        if (var10000 != null && var6.C38_f577 < var6.C38_f574) {
            int var20 = var6.C38_f587;
            Dialog var8;
            Dialog var21 = var8 = this;
            Component var4 = var6;
            Dialog var7 = var21;
            int var9 = 0;
            int var10 = 0;
            int var11 = 0;
            int var12 = 0;
            if (var6.C38_f575[0] != -1) {
                Rectangle var13;
                var9 = (var13 = var7.calculateBounds(var8.getChildById(var6.C38_f575[0]))).x;
                var10 = var13.x + var13.width;
                var11 = var13.y;
                var12 = var13.y + var13.height;

                for (int var14 = 1; var14 != var4.C38_f577; ++var14) {
                    var13 = var7.calculateBounds(var8.getChildById(var4.C38_f575[var14]));
                    if (var9 > var13.x) {
                        var9 = var13.x;
                    }

                    if (var10 < var13.x + var13.width) {
                        var10 = var13.x + var13.width;
                    }

                    if (var11 > var13.y) {
                        var11 = var13.y;
                    }

                    if (var12 < var13.y + var13.height) {
                        var12 = var13.y + var13.height;
                    }
                }
            }

            new Rectangle(var9, var11, var10 - var9, var12 - var11);
            Rectangle var16 = new Rectangle();
            Rectangle var19 = new Rectangle();
            if (var6.C38_f584 != 0) {
                var20 = var6.C38_f584;
            }

            var1.setColor(255, 255, 255);
            var1.fillRect(var16.x, var16.y, var16.width, var16.height);
            var1.setColor(245, 222, 179);
            var1.drawRect(var16.x, var16.y, var16.width, var16.height);
            var1.setColor(95, 158, 160);
            var1.fillRect(var19.x, var19.y, var19.width, var19.height);
        }

        for (int var17 = 0; var17 < var3.getComponents().length && var3.getComponents()[var17] != null; ++var17) {
            if (var3.getComponents()[var17].getChildComponent() != null) {
                boolean var5 = false;
                int var18;
                if ((var18 = GameUtils.findFirstAvailableSlot(var15.activeIndices)) > 0 && var15.activeIndices[var18 - 1] == var3.getSelectedComponentId()) {
                    var5 = true;
                }

                var3.getComponents()[var17].getChildComponent().a(var2, var3.getComponents()[var17].getSelectedComponentId(), var5, var15.activeIndices, true, var15.rootComponent);
            } else {
                var3.getComponents()[var17].render(var2, false, true, var15.rootComponent, var15.activeIndices);
            }
        }

    }

    public final void closeDialog() {
        int var10003 = this.currentIndex;
        boolean var1 = true;
        RootComponent var2 = this.rootComponent;
        Dialog var4 = this;

        for (int var3 = 0; var3 < var2.getComponents().length && var2.getComponents()[var3] != null; ++var3) {
            if (var2.getComponents()[var3].getChildComponent() != null) {
                if (GameUtils.findFirstAvailableSlot(var4.activeIndices) > 0) {
                    int[] var10000 = var4.activeIndices;
                    var2.getSelectedComponentId();
                }

                var2.getComponents()[var3].getChildComponent().update(var2.getComponents()[var3].getSelectedComponentId(), var4.activeIndices, true, var4.rootComponent);
            } else {
                var2.getComponents()[var3].update(false, true, var4.rootComponent, var4.activeIndices);
            }
        }

    }

    public final IComponent getChildById(int var1) {
        return GameUtils.findChildById(this.rootComponent, var1);
    }

    public final boolean handleAction(int var1) {
        IComponent var2 = this.a(this.rootComponent, this.selectedIndices, 0);
        boolean var4;
        boolean var9;
        if (var2.getZIndex() == 2) {
            GridComponent var3 = (GridComponent) var2;
            var4 = false;
            switch (var1) {
                case 0:
                    var4 = var3.navigate((byte) 0);
                    this.handler.a(new int[]{-1, -1, 0}, new int[]{-1, -1, -1, -1});
                    break;
                case 1:
                    var4 = var3.navigate((byte) 1);
                    this.handler.a(new int[]{-1, -1, 1}, new int[]{-1, -1, -1, -1});
                    break;
                case 2:
                    var4 = var3.navigate((byte) 2);
                    this.handler.a(new int[]{-1, -1, 2}, new int[]{-1, -1, -1, -1});
                    break;
                case 3:
                    var4 = var3.navigate((byte) 3);
                    this.handler.a(new int[]{-1, -1, 3}, new int[]{-1, -1, -1, -1});
                case 4:
                case 6:
                default:
                    break;
                case 5:
                    this.handler.a(new int[]{-1, -1, 4}, new int[]{-1, -1, -1, -1});
                    var4 = true;
                    break;
                case 7:
                    if (var4 = this.updateIndices()) {
                        var3.showSelection = false;
                        this.handler.a(new int[]{-1, -1, 7}, new int[]{-1, -1, -1, -1});
                    } else {
                        this.handler.a(new int[]{-1, -1, 5}, new int[]{-1, -1, -1, -1});
                    }
            }

            var9 = var4;
        } else {
            RootComponent var12 = (RootComponent) var2;
            int var11 = var1;
            Dialog var10 = this;
            var4 = false;
            boolean var5 = false;
            byte[][] var6;
            if (var12.getDialogData() != null) {
                var6 = var12.getDialogData();
            } else {
                var6 = new byte[][]{{0, 0, 1, -1}, {1, 1, 1, -1}, {2, 2, 1, -1}, {3, 3, 1, -1}, {5, 4, -1, -1}, {7, 5, -1, -1}};
            }

            int[] var7 = new int[3];
            if (var12.additionalChildComponent != null) {
                var7[1] = var12.additionalChildComponent.C38_f580;
            } else {
                var7[1] = -1;
            }

            if (var12.otherChildComponent != null) {
                var7[0] = var12.otherChildComponent.C38_f580;
            } else {
                var7[0] = -1;
            }

            for (int var8 = 0; var8 < var6.length; ++var8) {
                if (var6[var8][0] == var11) {
                    var5 = true;
                    switch (var6[var8][1]) {
                        case 0:
                            var7[2] = 0;
                            if (var12.otherChildComponent != null) {
                                if (var6[var8][3] != -1 && var12.otherChildComponent.C38_f580 % (var6[var8][3] + 1) == 0) {
                                    var12.otherChildComponent.a(var6[var8][3], var10.rootComponent);
                                } else {
                                    var12.otherChildComponent.b(var6[var8][2], var10.rootComponent);
                                }

                                var4 = true;
                                var7[0] = var12.otherChildComponent.C38_f580;
                                var10.handler.a(var7, new int[]{-1, -1, -1, -1});
                            } else {
                                var10.handler.a(var7, new int[]{-1, -1, -1, -1});
                            }
                            break;
                        case 1:
                            var7[2] = 1;
                            if (var12.otherChildComponent != null) {
                                if (var6[var8][3] != -1 && (var12.otherChildComponent.C38_f580 + 1) % (var6[var8][3] + 1) == 0) {
                                    var12.otherChildComponent.b(var6[var8][3], var10.rootComponent);
                                } else {
                                    var12.otherChildComponent.a(var6[var8][2], var10.rootComponent);
                                }

                                var4 = true;
                                var7[0] = var12.otherChildComponent.C38_f580;
                                var10.handler.a(var7, new int[]{-1, -1, -1, -1});
                            } else {
                                var10.handler.a(var7, new int[]{-1, -1, -1, -1});
                            }
                            break;
                        case 2:
                            var7[2] = 2;
                            if (var12.additionalChildComponent != null) {
                                if (var6[var8][3] != -1 && var12.additionalChildComponent.C38_f580 % (var6[var8][3] + 1) == 0) {
                                    var12.additionalChildComponent.a(var6[var8][3], var10.rootComponent);
                                } else {
                                    var12.additionalChildComponent.b(var6[var8][2], var10.rootComponent);
                                }

                                var4 = true;
                                var7[1] = var12.additionalChildComponent.C38_f580;
                                var10.handler.a(var7, new int[]{-1, -1, -1, -1});
                            } else {
                                var10.handler.a(var7, new int[]{-1, -1, -1, -1});
                            }
                            break;
                        case 3:
                            var7[2] = 3;
                            if (var12.additionalChildComponent != null) {
                                if (var6[var8][3] != -1 && (var12.additionalChildComponent.C38_f580 + 1) % (var6[var8][3] + 1) == 0) {
                                    var12.additionalChildComponent.b(var6[var8][3], var10.rootComponent);
                                } else {
                                    var12.additionalChildComponent.a(var6[var8][2], var10.rootComponent);
                                }

                                var4 = true;
                                var7[1] = var12.additionalChildComponent.C38_f580;
                                var10.handler.a(var7, new int[]{-1, -1, -1, -1});
                            } else {
                                var10.handler.a(var7, new int[]{-1, -1, -1, -1});
                            }
                            break;
                        case 4:
                            if (var12.additionalChildComponent != null) {
                                if (var12.additionalChildComponent.C38_f580 >= GameUtils.findFirstAvailableSlot(var12.additionalChildComponent.C38_f575)) {
                                    var4 = var10.activateComponent(var12, var12.additionalChildComponent.C38_f575[var12.additionalChildComponent.C38_f580 - var12.additionalChildComponent.C38_f579]);
                                } else {
                                    var4 = var10.activateComponent(var12, var12.additionalChildComponent.C38_f575[var12.additionalChildComponent.C38_f580]);
                                }
                            }

                            if (!var4 && var12.otherChildComponent != null) {
                                if (var12.otherChildComponent.C38_f580 >= GameUtils.findFirstAvailableSlot(var12.otherChildComponent.C38_f575)) {
                                    var4 = var10.activateComponent(var12, var12.otherChildComponent.C38_f575[var12.otherChildComponent.C38_f580 - var12.otherChildComponent.C38_f579]);
                                } else {
                                    var4 = var10.activateComponent(var12, var12.otherChildComponent.C38_f575[var12.otherChildComponent.C38_f580]);
                                }
                            }

                            if (var4) {
                                var7[2] = 6;
                                var10.handler.a(var7, new int[]{-1, -1, -1, -1});
                            } else {
                                var7[2] = 4;
                                var10.handler.a(var7, new int[]{-1, -1, -1, -1});
                            }
                            break;
                        case 5:
                            if (var4 = var10.updateIndices()) {
                                var7[2] = 7;
                                var10.handler.a(var7, new int[]{-1, -1, -1, -1});
                            } else {
                                var7[2] = 5;
                                var10.handler.a(var7, new int[]{-1, -1, -1, -1});
                            }
                    }

                    if (var4) {
                        break;
                    }
                }
            }

            if (!var5) {
                switch (var11) {
                    case 14:
                    case 15:
                    case 16:
                    case 17:
                    case 18:
                    case 19:
                    case 20:
                    case 21:
                    case 22:
                    case 23:
                        var7[2] = var11 - 6;
                        var10.handler.a(var7, new int[]{-1, -1, -1, -1});
                }
            }

            var9 = var4;
        }

        return var9;
    }

    private boolean updateIndices() {
        boolean var1 = false;
        int var2 = -1;

        for (int var3 = GameUtils.findFirstAvailableSlot(this.activeIndices) - 2; var3 >= 0; --var3) {
            IComponent var4;
            if (((RootComponent) (var4 = GameUtils.findChildById((IComponent) this.rootComponent, (int) this.activeIndices[var3]))).otherChildComponent != null || ((RootComponent) var4).additionalChildComponent != null) {
                var1 = true;
                var2 = var4.getSelectedComponentId();
                break;
            }
        }

        if (var1) {
            this.selectedIndices = this.traverseComponents(var2);
            if (this.selectedIndices == null) {
                int[] var10001 = this.selectedIndices;
                this.selectedIndices = GameUtils.initializeArrayWithMinusOne(50);
            }

            this.initializeActiveIndices();
        }

        return var1;
    }

    private boolean activateComponent(IComponent var1, int var2) {
        if (var1.getZIndex() == 1) {
            return false;
        } else {
            int var3;
            if ((var3 = this.getSelectedIndices(var1, var2, true)) == -1) {
                return false;
            } else {
                this.selectedIndices = this.traverseComponents(var3);
                if (this.selectedIndices == null) {
                    int[] var10001 = this.selectedIndices;
                    this.selectedIndices = GameUtils.initializeArrayWithMinusOne(50);
                }

                this.initializeActiveIndices();
                return true;
            }
        }
    }

    private void initializeActiveIndices() {
        int[] var10001 = this.activeIndices;
        this.activeIndices = GameUtils.initializeArrayWithMinusOne(50);
        Object var1 = this.rootComponent;
        byte var2 = 0;
        int var4 = var2 + 1;
        this.activeIndices[0] = ((IComponent) var1).getSelectedComponentId();

        for (int var3 = 0; var3 < this.selectedIndices.length && this.selectedIndices[var3] != -1; ++var3) {
            var1 = ((IComponent) var1).getComponents()[this.selectedIndices[var3]];
            this.activeIndices[var4++] = ((IComponent) var1).getSelectedComponentId();
        }

    }

    private int getSelectedIndices(IComponent var1, int var2, boolean var3) {
        if (var1.getZIndex() == 2 && var2 == -1) {
            if (var3) {
                ((GridComponent) var1).showSelection = true;
            }

            return var1.getSelectedComponentId();
        } else if ((((RootComponent) var1).otherChildComponent != null || ((RootComponent) var1).additionalChildComponent != null) && var2 == -1) {
            return var1.getSelectedComponentId();
        } else {
            for (int var4 = 0; var4 < var1.getComponents().length && var1.getComponents()[var4] != null; ++var4) {
                int var5;
                if (var1.getComponents()[var4].getZIndex() != 1 && (var2 == -1 || var1.getComponents()[var4].getSelectedComponentId() == var2) && (var5 = this.getSelectedIndices((IComponent) var1.getComponents()[var4], -1, var3)) != -1) {
                    return var5;
                }
            }

            return -1;
        }
    }

    private int[] traverseComponents(int var1) {
        int[] var2 = GameUtils.initializeArrayWithMinusOne(50);

        for (IComponent var5 = GameUtils.findChildById((IComponent) this.rootComponent, (int) var1); var5.getActiveComponentId() != -1; var5 = GameUtils.findChildById((IComponent) this.rootComponent, (int) var5.getActiveComponentId())) {
            IComponent var3 = GameUtils.findChildById((IComponent) this.rootComponent, (int) var5.getActiveComponentId());

            for (int var4 = 0; var4 < var3.getComponents().length && var3.getComponents()[var4] != null; ++var4) {
                if (var3.getComponents()[var4].getSelectedComponentId() == var5.getSelectedComponentId()) {
                    GameUtils.insertAtStart(var2, 0, var4);
                    break;
                }
            }
        }

        return var2;
    }

    private IComponent a(IComponent var1, int[] var2, int var3) {
        int var4;
        while ((var4 = GameUtils.findFirstAvailableSlot(var2)) != 0) {
            if (var3 == var4 - 1) {
                return var1.getComponents()[var2[var3]];
            }

            IComponent var10000 = var1.getComponents()[var2[var3]];
            ++var3;
            var2 = var2;
            var1 = var10000;
        }

        return var1;
    }

    public final void clean() {
        this.rootComponent.cleanUp();
        this.allowedActions = null;
        this.selectedIndices = null;
        this.activeIndices = null;
        this.handler = null;
        this.childComponents = null;
    }
}
