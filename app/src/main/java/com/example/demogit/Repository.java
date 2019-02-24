package com.example.demogit;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

    public class Repository {


        @SerializedName("author")
        @Expose
        private String author;

        public Repository() {
        }

        public Repository(String author, String name, String description, String language, String languageColor, String url,Integer stars, Integer forks, Integer currentPeriodStars) {
            this.author = author;
            this.name = name;
            this.description = description;
            this.stars = stars;
            this.currentPeriodStars = currentPeriodStars;
            this.language = language;
            this.languageColor=languageColor;
            this.forks = forks;
            this.url=url;
        }

        @SerializedName("name")
        @Expose
        private String name;
        @SerializedName("url")
        @Expose
        private String url;
        @SerializedName("description")
        @Expose
        private String description;
        @SerializedName("stars")
        @Expose
        private Integer stars;
        @SerializedName("forks")
        @Expose
        private Integer forks;
        @SerializedName("currentPeriodStars")
        @Expose
        private Integer currentPeriodStars;
        @SerializedName("builtBy")
        @Expose
        private List<BuiltBy> builtBy = null;
        @SerializedName("language")
        @Expose
        private String language;
        @SerializedName("languageColor")
        @Expose
        private String languageColor;

        public String getAuthor() {
            return author;
        }

        public void setAuthor(String author) {
            this.author = author;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getUrl() {
            return url;
        }

        public void setUrl(String url) {
            this.url = url;
        }

        public String getDescription() {
            return description;
        }

        public void setDescription(String description) {
            this.description = description;
        }

        public Integer getStars() {
            return stars;
        }

        public void setStars(Integer stars) {
            this.stars = stars;
        }

        public Integer getForks() {
            return forks;
        }

        public void setForks(Integer forks) {
            this.forks = forks;
        }

        public Integer getCurrentPeriodStars() {
            return currentPeriodStars;
        }

        public void setCurrentPeriodStars(Integer currentPeriodStars) {
            this.currentPeriodStars = currentPeriodStars;
        }

        public List<BuiltBy> getBuiltBy() {
            return builtBy;
        }

        public void setBuiltBy(List<BuiltBy> builtBy) {
            this.builtBy = builtBy;
        }

        public String getLanguage() {
            return language;
        }

        public void setLanguage(String language) {
            this.language = language;
        }

        public String getLanguageColor() {
            return  languageColor;
        }

        public void setLanguageColor(String languageColor) {
            this.languageColor = languageColor;
        }
    }

