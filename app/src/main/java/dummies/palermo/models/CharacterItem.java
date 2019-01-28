package dummies.palermo.models;

import android.support.annotation.ColorRes;
import android.support.annotation.DrawableRes;
import android.support.annotation.StringRes;

public class CharacterItem {
    private @DrawableRes
    int iconID, imageID;

    private @StringRes
    int titleID, subtitleID, infoID;

    private @ColorRes
    int colorID;

    static class Builder {
        CharacterItem item;

        Builder() {
            item = new CharacterItem();
        }

        Builder addIconID(@DrawableRes int iconID) {
            item.iconID = iconID;
            return this;
        }

        Builder addImageID(@DrawableRes int imageID) {
            item.imageID = imageID;
            return this;
        }

        Builder addTitleID(@StringRes int titleID) {
            item.titleID = titleID;
            return this;
        }

        Builder addSubtitleID(@StringRes int subtitleID) {
            item.subtitleID = subtitleID;
            return this;
        }

        Builder addInfoID(@StringRes int infoID) {
            item.infoID = infoID;
            return this;
        }

        Builder addColorID(@ColorRes int colorID) {
            item.colorID = colorID;
            return this;
        }

        CharacterItem create() {
            return item;
        }
    }

    CharacterItem() {
        iconID = 0;
        titleID = 0;
        subtitleID = 0;
        imageID = 0;
        infoID = 0;
        colorID = 0;
    }

    public @DrawableRes int getIconID() {
        return iconID;
    }

    public void setIconID(int iconID) {
        this.iconID = iconID;
    }

    public @DrawableRes int getImageID() {
        return imageID;
    }

    public void setImageID(int imageID) {
        this.imageID = imageID;
    }

    public @StringRes int getTitleID() {
        return titleID;
    }

    public void setTitleID(@StringRes int titleID) {
        this.titleID = titleID;
    }

    public @StringRes int getSubtitleID() {
        return subtitleID;
    }

    public void setSubtitleID(@StringRes int subtitleID) {
        this.subtitleID = subtitleID;
    }

    public @StringRes int getInfoID() {
        return infoID;
    }

    public void setInfoID(@StringRes int infoID) {
        this.infoID = infoID;
    }

    public @ColorRes int getColorID() {
        return colorID;
    }

    public void setColorID(@ColorRes int colorID) {
        this.colorID = colorID;
    }
}
