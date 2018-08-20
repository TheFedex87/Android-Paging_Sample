package it.pagingexample.thefedex87.paginationexamplegithub.data;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.Ignore;
import android.arch.persistence.room.PrimaryKey;
import android.support.v7.util.DiffUtil;

@Entity(tableName = "topics")
public class Topic {
    @PrimaryKey
    private long id;

    private String name;
    private String html_url;

    @ColumnInfo(name = "private")
    private boolean isPrivate;

    private double score;
    private String description;

    private String ownerName;

    @Ignore
    private Owner owner;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getHtml_url() {
        return html_url;
    }

    public void setHtml_url(String html_url) {
        this.html_url = html_url;
    }

    public boolean isPrivate() {
        return isPrivate;
    }

    public void setPrivate(boolean aPrivate) {
        isPrivate = aPrivate;
    }

    public double getScore() {
        return score;
    }

    public void setScore(double score) {
        this.score = score;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getOwnerName() {
        return ownerName;
    }

    public void setOwnerName(String ownerName) {
        this.ownerName = ownerName;
    }

    public Owner getOwner() {
        return owner;
    }

    public void setOwner(Owner owner) {
        this.owner = owner;
    }

    public static DiffUtil.ItemCallback<Topic> DIFF_CALLBACK = new DiffUtil.ItemCallback<Topic>() {
        @Override
        public boolean areItemsTheSame(Topic oldItem, Topic newItem) {
            return oldItem.getId() == newItem.getId();
        }

        @Override
        public boolean areContentsTheSame(Topic oldItem, Topic newItem) {
            return oldItem.equals(newItem);
        }
    };
}
