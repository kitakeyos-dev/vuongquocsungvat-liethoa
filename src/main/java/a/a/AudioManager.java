package a.a;

import me.kitakeyos.ManagedInputStream;

import javax.microedition.media.Manager;
import javax.microedition.media.Player;
import javax.microedition.media.PlayerListener;
import javax.microedition.media.control.VolumeControl;
import java.io.InputStream;

public final class AudioManager {
    private byte[] volumeLevels = new byte[]{0, 20, 40, 60, 80, 100};
    private String audioBasePath = "";
    private byte[][] audioSettings;
    private int currentMusicTrack;
    private int previousMusicTrack;
    private Player[] musicPlayers;
    private short maxMusicPlayers;
    private Player[] soundEffectPlayers;
    private static final String[] SUPPORTED_FORMATS = new String[]{"audio/x-tone-seq", "audio/midi", "audio/x-wav", "audio/amr"};
    private VolumeControl volumeController;
    private byte listenerMode;
    private PlayerListener musicListener;
    private PlayerListener effectListener;

    public AudioManager(int var1, int var2, byte var3, String var4) {
        this.audioSettings = new byte[][]{{2, 2, 0}, {(byte) (this.volumeLevels.length - 1), (byte) (this.volumeLevels.length - 1), 1}, {-1, -1, -1}, {0, 0, 0}};
        this.currentMusicTrack = 0;
        this.previousMusicTrack = 0;
        this.volumeController = null;
        this.listenerMode = 0;
        this.musicListener = new AudioPlayerListener(this);
        this.effectListener = this.musicListener;
        if (this.musicPlayers == null) {
            this.maxMusicPlayers = 7;
            this.musicPlayers = new Player[7];
        }
        this.listenerMode = 0;
        this.audioBasePath = var4;
    }

    public final void cleanup() {
        int var1;
        if (this.musicPlayers != null) {
            for (var1 = 0; var1 < this.musicPlayers.length; ++var1) {
                this.musicPlayers[var1] = null;
            }
        }

        if (this.soundEffectPlayers != null) {
            for (var1 = 0; var1 < this.soundEffectPlayers.length; ++var1) {
                this.soundEffectPlayers[var1] = null;
            }
        }

        this.musicPlayers = null;
        this.soundEffectPlayers = null;
    }

    private static Player createAudioPlayer(String var0, int var1) {
        try {
            var0.getClass();
            InputStream var3 = ManagedInputStream.openStream(var0);
            Player var4 = Manager.createPlayer(var3, SUPPORTED_FORMATS[var1]);
            var4.realize();
            var4.prefetch();
            var4.setLoopCount(-1);
            var3.close();
            return var4;
        } catch (Exception var2) {
            var2.printStackTrace();
            return null;
        }
    }

    public final void preloadMusicTracks(byte[] var1) {
        for (int var2 = 0; var2 < var1.length; ++var2) {
            if (var1[var2] != -1 && this.musicPlayers[var1[var2]] == null) {
                this.musicPlayers[var1[var2]] = createAudioPlayer((String) (this.audioBasePath + var1[var2] + ".mid"), 1);
            }
        }

    }

    private void playAudio(Player var1, int var2, int var3, boolean var4, boolean var5) {
        if (var1 != null) {
            try {
                if (this.audioSettings[0][var2] != 0) {
                    if (this.listenerMode == 1) {
                        if (var5) {
                            var1.addPlayerListener(this.musicListener);
                        } else {
                            var1.addPlayerListener(this.effectListener);
                        }
                    }

                    var1.prefetch();
                    if (var1.getState() == 300) {
                        var1.setLoopCount(var3);
                        if (!var4) {
                            var1.setMediaTime(0L);
                        }

                        this.setPlayerVolume((Player) var1, this.volumeLevels[this.audioSettings[0][var2]]);
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

    private void stopAudio(Player var1, boolean var2) {
        if (var1 != null) {
            try {
                var1.stop();
                if (this.listenerMode == 1) {
                    if (var2) {
                        var1.removePlayerListener(this.musicListener);
                        return;
                    }

                    var1.removePlayerListener(this.effectListener);
                }

                return;
            } catch (Exception var3) {
                var3.printStackTrace();
            }
        }

    }

    private void setPlayerVolume(Player var1, int var2) {
        try {
            this.volumeController = (VolumeControl) var1.getControl("VolumeControl");
            if (var2 == 0) {
                this.stopAudioChannel((int) 1);
            } else {
                this.volumeController.setLevel(var2);
                if (isPlayerReady(var1)) {
                    var1.start();
                }

            }
        } catch (Exception var3) {
            var3.printStackTrace();
        }
    }

    public final void deallocateAudioResources(byte var1) {
        if (this.musicPlayers != null) {
            for (Player var2 : this.musicPlayers) {
                if (var2 != null) {
                    var2.deallocate();
                    var2.close();
                }
            }

        }
    }

    private static boolean isPlayerReady(Player var0) {
        try {
            var0.prefetch();
            if (var0.getState() == 300) {
                return true;
            }
        } catch (Exception var1) {
        }

        return false;
    }

    public final void setMasterVolume(byte var1) {
        if (var1 >= this.volumeLevels.length) {
            var1 = (byte) (this.volumeLevels.length - 1);
        }

        this.audioSettings[2][1] = (byte) this.currentMusicTrack;
        this.audioSettings[0][1] = var1;
        this.audioSettings[0][0] = var1;
        if (this.musicPlayers != null && this.musicPlayers[this.currentMusicTrack] != null) {
            this.setPlayerVolume(this.musicPlayers[this.currentMusicTrack], this.volumeLevels[this.audioSettings[0][1]]);
        }

        if (this.soundEffectPlayers != null) {
            for (int var2 = 0; var2 < this.soundEffectPlayers.length; ++var2) {
                if (this.soundEffectPlayers[var2] != null) {
                    this.setPlayerVolume(this.soundEffectPlayers[var2], this.volumeLevels[this.audioSettings[0][0]]);
                }
            }
        }

    }

    public void playBackgroundMusic(int trackIndex, int fadeMode) {
        this.previousMusicTrack = this.currentMusicTrack;
        this.currentMusicTrack = trackIndex;
        if (trackIndex >= 0 && trackIndex < this.maxMusicPlayers) {
            if (this.audioSettings[2][1] == -1 || trackIndex != this.audioSettings[2][1]) {
                byte var3 = 1;
                if (this.audioSettings[0][var3] == 0) {
                    return;
                }

                this.stopAudioChannel(1);
                if (this.previousMusicTrack != trackIndex && this.musicPlayers[this.previousMusicTrack] != null) {
                    this.musicPlayers[this.previousMusicTrack] = null;
                }

                if (this.musicPlayers[trackIndex] == null) {
                    this.musicPlayers[trackIndex] = createAudioPlayer(this.audioBasePath + trackIndex + ".mid", 1);
                }

                byte var5 = 1;
                byte var4 = -1;
                this.stopAudioChannel(var5);
                this.audioSettings[2][var5] = (byte) trackIndex;
                this.audioSettings[3][var5] = var4;
                this.playAudio(this.musicPlayers[trackIndex], 1, -1, false, true);
            }
        } else if (trackIndex == -2 || trackIndex == -1) {
            this.stopAudioChannel(1);
        }

    }

    private void stopAudioChannel(int var1) {
        switch (var1) {
            case 0:
                if (this.audioSettings[2][0] >= 0) {
                    this.stopAudio(this.soundEffectPlayers[this.audioSettings[2][0]], false);
                    return;
                }
                break;
            case 1:
                if (this.audioSettings[2][1] >= 0) {
                    this.stopAudio(this.musicPlayers[this.audioSettings[2][1]], true);
                }
            case 2:
        }

    }

    public final void stopAllAudio() {
        this.stopAudioChannel(0);
        this.stopAudioChannel(1);
    }

    public final void resumeAllAudio() {
        if (this.audioSettings[2][1] != -1) {
            this.playAudio(this.musicPlayers[this.audioSettings[2][1]], 1, this.audioSettings[3][1], this.listenerMode == 0, true);
        }

        if (this.audioSettings[2][0] != -1) {
            this.playAudio(this.soundEffectPlayers[this.audioSettings[2][0]], 0, this.audioSettings[3][0], this.listenerMode == 0, false);
        }

    }

    static byte[][] getAudioSettings(AudioManager var0) {
        return var0.audioSettings;
    }
}
