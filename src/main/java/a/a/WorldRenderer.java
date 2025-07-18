package a.a;

import a.GameEngineBase;
import a.b.CameraController;
import a.b.GameEntity;
import a.b.ResourceManager;
import a.b.TileMapRenderer;
import game.C18;
import game.C25;
import game.C53;
import java.util.Vector;
import javax.microedition.lcdui.Graphics;

public final class WorldRenderer {
   private TileMapRenderer tileMapRenderer;
   private Vector backgroundEntities = new Vector();
   private Vector middleLayerEntities = new Vector();
   private Vector foregroundEntities = new Vector();
   private CameraController cameraController;

   public final void setTileMapRenderer(TileMapRenderer renderer) {
      this.tileMapRenderer = renderer;
   }

   public final void addEntity(GameEntity var1) {
      switch(var1.layer) {
      case 0:
         this.backgroundEntities.addElement(var1);
         return;
      case 1:
         this.middleLayerEntities.addElement(var1);
         return;
      case 2:
         this.foregroundEntities.addElement(var1);
      default:
      }
   }

   public final void removeEntity(GameEntity entity) {
      switch(entity.layer) {
      case 0:
         this.backgroundEntities.removeElement(entity);
         return;
      case 1:
         this.middleLayerEntities.removeElement(entity);
         return;
      case 2:
         this.foregroundEntities.removeElement(entity);
      default:
      }
   }

   public final void moveEntityToForeground(GameEntity entity, int var2) {
      this.removeEntity(entity);
      this.foregroundEntities.addElement(entity);
   }

   public final void setCameraController(CameraController cameraController) {
      this.cameraController = cameraController;
   }

   public final void clearWorld() {
      this.tileMapRenderer = null;
      this.cameraController = null;
      this.middleLayerEntities.removeAllElements();
      this.backgroundEntities.removeAllElements();
      this.foregroundEntities.removeAllElements();
   }

   public final void updateWorld() {
      this.cameraController.updateCamera();
      this.tileMapRenderer.setCameraPosition(this.cameraController.worldX, this.cameraController.worldY);
      this.tileMapRenderer.updateViewport();

      int var1;
      int var2;
      Object var3;
      for(var1 = 0; var1 < this.backgroundEntities.size(); ++var1) {
         for(var2 = 0; var2 < this.backgroundEntities.size() - var1 - 1; ++var2) {
            if (((GameEntity)this.backgroundEntities.elementAt(var2)).worldY > ((GameEntity)this.backgroundEntities.elementAt(var2 + 1)).worldY) {
               var3 = this.backgroundEntities.elementAt(var2);
               this.backgroundEntities.setElementAt(this.backgroundEntities.elementAt(var2 + 1), var2);
               this.backgroundEntities.setElementAt(var3, var2 + 1);
            }
         }
      }

      for(var1 = 0; var1 < this.backgroundEntities.size(); ++var1) {
         ((GameObject)this.backgroundEntities.elementAt(var1)).updateAnimation();
      }

      for(var1 = 0; var1 < this.middleLayerEntities.size(); ++var1) {
         for(var2 = 0; var2 < this.middleLayerEntities.size() - var1 - 1; ++var2) {
            if (((GameEntity)this.middleLayerEntities.elementAt(var2)).worldY > ((GameEntity)this.middleLayerEntities.elementAt(var2 + 1)).worldY) {
               var3 = this.middleLayerEntities.elementAt(var2);
               this.middleLayerEntities.setElementAt(this.middleLayerEntities.elementAt(var2 + 1), var2);
               this.middleLayerEntities.setElementAt(var3, var2 + 1);
            }
         }
      }

      for(var1 = 0; var1 < this.middleLayerEntities.size(); ++var1) {
         ((GameObject)this.middleLayerEntities.elementAt(var1)).updateAnimation();
         if (this.middleLayerEntities.elementAt(var1) instanceof C18) {
            if (((C18)this.middleLayerEntities.elementAt(var1)).C18_f246 != null && ((C18)this.middleLayerEntities.elementAt(var1)).C18_f246.isActive()) {
               ((C18)this.middleLayerEntities.elementAt(var1)).C18_f246.updateAnimation();
            } else if (((C18)this.middleLayerEntities.elementAt(var1)).C18_f247 != null && ((C18)this.middleLayerEntities.elementAt(var1)).C18_f247.isActive()) {
               ((C18)this.middleLayerEntities.elementAt(var1)).C18_f247.updateAnimation();
            }
         }
      }

      for(var1 = 0; var1 < this.foregroundEntities.size(); ++var1) {
         for(var2 = 0; var2 < this.foregroundEntities.size() - var1 - 1; ++var2) {
            if (((GameEntity)this.foregroundEntities.elementAt(var2)).worldY > ((GameEntity)this.foregroundEntities.elementAt(var2 + 1)).worldY) {
               var3 = this.foregroundEntities.elementAt(var2);
               this.foregroundEntities.setElementAt(this.foregroundEntities.elementAt(var2 + 1), var2);
               this.foregroundEntities.setElementAt(var3, var2 + 1);
            }
         }
      }

      for(var1 = 0; var1 < this.foregroundEntities.size(); ++var1) {
         ((GameObject)this.foregroundEntities.elementAt(var1)).updateAnimation();
      }

   }

   public final void renderWorld(Graphics graphics) {
      int var2;
      for(var2 = 0; var2 < this.middleLayerEntities.size(); ++var2) {
         ((GameObject)this.middleLayerEntities.elementAt(var2)).renderAlternative(graphics, this.tileMapRenderer.cameraX, this.tileMapRenderer.cameraY);
      }

      this.tileMapRenderer.renderLayer(graphics, 1);
      this.tileMapRenderer.renderLayer(graphics, 2);

      for(var2 = 0; var2 < this.foregroundEntities.size(); ++var2) {
         if (((GameObject)this.foregroundEntities.elementAt(var2)).isInteractable()) {
            ((GameObject)this.foregroundEntities.elementAt(var2)).render(graphics, this.tileMapRenderer.cameraX, this.tileMapRenderer.cameraY);
         }
      }

      if (C25.B().C25_f286.C53_f793[2] == 2) {
         for(var2 = 0; var2 < this.middleLayerEntities.size(); ++var2) {
            if (((GameObject)this.middleLayerEntities.elementAt(var2)).isInteractable()) {
               if (this.middleLayerEntities.elementAt(var2) instanceof C53) {
                  continue;
               }

               ((GameObject)this.middleLayerEntities.elementAt(var2)).render(graphics, this.tileMapRenderer.cameraX, this.tileMapRenderer.cameraY);
            }

            if (this.middleLayerEntities.elementAt(var2) instanceof C18 && ((C18)this.middleLayerEntities.elementAt(var2)).C18_f225 == 14) {
               ((C18)this.middleLayerEntities.elementAt(var2)).c(graphics, this.tileMapRenderer.cameraX, this.tileMapRenderer.cameraY);
            }

            if (this.middleLayerEntities.elementAt(var2) instanceof C18) {
               if (((C18)this.middleLayerEntities.elementAt(var2)).C18_f246 != null && ((C18)this.middleLayerEntities.elementAt(var2)).C18_f246.isVisible()) {
                  ((C18)this.middleLayerEntities.elementAt(var2)).C18_f246.render(graphics, this.tileMapRenderer.cameraX, this.tileMapRenderer.cameraY);
               } else if (((C18)this.middleLayerEntities.elementAt(var2)).C18_f247 != null && ((C18)this.middleLayerEntities.elementAt(var2)).C18_f247.isVisible()) {
                  ((C18)this.middleLayerEntities.elementAt(var2)).C18_f247.render(graphics, this.tileMapRenderer.cameraX, this.tileMapRenderer.cameraY);
               }
            }
         }

         if (TileMapRenderer.getInstance().getTileAt(0, C53.p().worldX, C53.p().worldY) != 1 && C25.B().C25_f286.attachedObject != null && C25.B().C25_f286.isVisible()) {
            C25.B().C25_f286.attachedObject.render(graphics, this.tileMapRenderer.cameraX, this.tileMapRenderer.cameraY);
         }

         C25.B().C25_f286.render(graphics, this.tileMapRenderer.cameraX, this.tileMapRenderer.cameraY);
      } else {
         for(var2 = 0; var2 < this.middleLayerEntities.size(); ++var2) {
            if (((GameObject)this.middleLayerEntities.elementAt(var2)).isInteractable()) {
               if (((GameObject)this.middleLayerEntities.elementAt(var2)).attachedObject != null && ((GameObject)this.middleLayerEntities.elementAt(var2)).isVisible()) {
                  ((GameObject)this.middleLayerEntities.elementAt(var2)).attachedObject.render(graphics, this.tileMapRenderer.cameraX, this.tileMapRenderer.cameraY);
               }

               ((GameObject)this.middleLayerEntities.elementAt(var2)).render(graphics, this.tileMapRenderer.cameraX, this.tileMapRenderer.cameraY);
            }

            if (this.middleLayerEntities.elementAt(var2) instanceof C18 && ((C18)this.middleLayerEntities.elementAt(var2)).C18_f225 == 14) {
               ((C18)this.middleLayerEntities.elementAt(var2)).c(graphics, this.tileMapRenderer.cameraX, this.tileMapRenderer.cameraY);
            }

            if (this.middleLayerEntities.elementAt(var2) instanceof C18) {
               if (((C18)this.middleLayerEntities.elementAt(var2)).C18_f246 != null && ((C18)this.middleLayerEntities.elementAt(var2)).C18_f246.isVisible()) {
                  ((C18)this.middleLayerEntities.elementAt(var2)).C18_f246.render(graphics, this.tileMapRenderer.cameraX, this.tileMapRenderer.cameraY);
               } else if (((C18)this.middleLayerEntities.elementAt(var2)).C18_f247 != null && ((C18)this.middleLayerEntities.elementAt(var2)).C18_f247.isVisible()) {
                  ((C18)this.middleLayerEntities.elementAt(var2)).C18_f247.render(graphics, this.tileMapRenderer.cameraX, this.tileMapRenderer.cameraY);
               }
            }
         }
      }

      this.tileMapRenderer.renderLayer(graphics, 3);

      for(var2 = 0; var2 < this.backgroundEntities.size(); ++var2) {
         if (((GameObject)this.backgroundEntities.elementAt(var2)).isInteractable()) {
            ((GameObject)this.backgroundEntities.elementAt(var2)).render(graphics, this.tileMapRenderer.cameraX, this.tileMapRenderer.cameraY);
         }
      }

   }

   public final void renderCutsceneMode(Graphics graphics) {
      C25.B();
      C25.b(graphics);
      this.tileMapRenderer.renderLayer(graphics, 1);
      this.tileMapRenderer.renderLayer(graphics, 2);

      int var2;
      for(var2 = 0; var2 < this.foregroundEntities.size(); ++var2) {
         if (((GameObject)this.foregroundEntities.elementAt(var2)).isInteractable() && this.foregroundEntities.elementAt(var2) instanceof C18 && ((C18)this.foregroundEntities.elementAt(var2)).C18_f225 == 0) {
            ((GameObject)this.foregroundEntities.elementAt(var2)).render(graphics, this.tileMapRenderer.cameraX, this.tileMapRenderer.cameraY);
         }
      }

      for(var2 = 0; var2 < this.middleLayerEntities.size(); ++var2) {
         if (((GameObject)this.middleLayerEntities.elementAt(var2)).isInteractable() && this.middleLayerEntities.elementAt(var2) instanceof C18 && ((C18)this.middleLayerEntities.elementAt(var2)).C18_f225 == 0) {
            ((GameObject)this.middleLayerEntities.elementAt(var2)).render(graphics, this.tileMapRenderer.cameraX, this.tileMapRenderer.cameraY);
         }
      }

      this.tileMapRenderer.renderLayer(graphics, 3);

      for(var2 = 0; var2 < this.backgroundEntities.size(); ++var2) {
         if (((GameObject)this.backgroundEntities.elementAt(var2)).isInteractable() && this.backgroundEntities.elementAt(var2) instanceof C18 && ((C18)this.backgroundEntities.elementAt(var2)).C18_f225 == 0) {
            ((GameObject)this.backgroundEntities.elementAt(var2)).render(graphics, this.tileMapRenderer.cameraX, this.tileMapRenderer.cameraY);
         }
      }

      for(var2 = 0; var2 < GameEngineBase.getScreenWidth() / ResourceManager.backgroundImage.getWidth(); ++var2) {
         for(int var3 = 0; var3 < GameEngineBase.getScreenHeight() / ResourceManager.backgroundImage.getHeight(); ++var3) {
            graphics.drawImage(ResourceManager.backgroundImage, var2 * ResourceManager.backgroundImage.getWidth(), var3 * ResourceManager.backgroundImage.getHeight(), 20);
         }
      }

   }
}
