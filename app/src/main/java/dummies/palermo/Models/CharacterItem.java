package dummies.palermo.Models;

import android.content.res.Resources;
import android.graphics.drawable.Drawable;
import android.support.annotation.ColorRes;
import android.support.annotation.DrawableRes;
import android.support.annotation.IdRes;
import android.support.annotation.StringRes;
import android.support.v4.content.res.ResourcesCompat;

import dummies.palermo.R;

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

    public @StringRes int getTitle() {
        return titleID;
    }

    public void setTitle(@StringRes int titleID) {
        this.titleID = titleID;
    }

    public @StringRes int getSubtitle() {
        return subtitleID;
    }

    public void setSubtitle(@StringRes int subtitleID) {
        this.subtitleID = subtitleID;
    }

    public @StringRes int getInfo() {
        return infoID;
    }

    public void setInfo(@StringRes int infoID) {
        this.infoID = infoID;
    }
}
