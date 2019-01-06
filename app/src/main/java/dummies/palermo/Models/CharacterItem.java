package dummies.palermo.Models;

import android.content.res.Resources;
import android.graphics.drawable.Drawable;
import android.support.annotation.DrawableRes;
import android.support.v4.content.res.ResourcesCompat;

public class CharacterItem {
    private int iconID, imageID;
    private String title, subtitle, info;

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

        Builder addTitle(String title) {
            item.title = title;
            return this;
        }

        Builder addSubtitle(String subtitle) {
            item.subtitle = subtitle;
            return this;
        }

        Builder addInfo(String info) {
            item.info = info;
            return this;
        }

        CharacterItem create() {
            return item;
        }
    }

    CharacterItem(@DrawableRes int iconID, String title, String subtitle) {
        this.iconID = iconID;
        this.title = title;
        this.subtitle = subtitle;
        imageID = -1;
        info = "";
    }

    CharacterItem(@DrawableRes int imageID, String title, String subtitle, String info) {
        this.imageID = imageID;
        this.title = title;
        this.subtitle = subtitle;
        this.info = info;
        this.iconID = -1;
    }

    CharacterItem() {
        this(-1, "", "");
    }

    public int getIconID() {
        return iconID;
    }

    public void setIconID(int iconID) {
        this.iconID = iconID;
    }

    public int getImageID() {
        return imageID;
    }

    public void setImageID(int imageID) {
        this.imageID = imageID;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getSubtitle() {
        return subtitle;
    }

    public void setSubtitle(String subtitle) {
        this.subtitle = subtitle;
    }

    public String getInfo() {
        return info;
    }

    public void setInfo(String info) {
        this.info = info;
    }
}
