package com.roundforest.test.parser.entity;

import java.util.Date;

public class ReviewEntity {

    private String id;
    private String productId;
    private String userId;
    private String profileName;
    private int helpfulnessNumerator;
    private int helpfulnessDenominator;
    private int score;
    private Date date;
    private String summary;
    private String text;

    private ReviewEntity() {
    }

    public String getId() {
        return id;
    }

    public String getProductId() {
        return productId;
    }

    public String getUserId() {
        return userId;
    }

    public String getProfileName() {
        return profileName;
    }

    public void setProfileName(String profileName) {
        this.profileName = profileName;
    }

    public int getHelpfulnessNumerator() {
        return helpfulnessNumerator;
    }

    public void setHelpfulnessNumerator(int helpfulnessNumerator) {
        this.helpfulnessNumerator = helpfulnessNumerator;
    }

    public int getHelpfulnessDenominator() {
        return helpfulnessDenominator;
    }

    public void setHelpfulnessDenominator(int helpfulnessDenominator) {
        this.helpfulnessDenominator = helpfulnessDenominator;
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public String getSummary() {
        return summary;
    }

    public void setSummary(String summary) {
        this.summary = summary;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public static class RowEntityBuilder {
        private String id;
        private String productId;
        private String userId;
        private String profileName;
        private int helpfulnessNumerator;
        private int helpfulnessDenominator;
        private int score;
        private Date date;
        private String summary;
        private String text;

        public ReviewEntity build() {
            ReviewEntity reviewEntity = new ReviewEntity();
            reviewEntity.id = this.id;
            reviewEntity.productId = this.productId;
            reviewEntity.userId = this.userId;
            reviewEntity.profileName = this.profileName;
            reviewEntity.helpfulnessNumerator = this.helpfulnessNumerator;
            reviewEntity.helpfulnessDenominator = this.helpfulnessDenominator;
            reviewEntity.date = this.date;
            reviewEntity.score = this.score;
            reviewEntity.summary = this.summary;
            reviewEntity.text = this.text;
            return reviewEntity;
        }

        public RowEntityBuilder id(String id) {
            this.id = id;
            return this;
        }

        public RowEntityBuilder productId(String productId) {
            this.productId = productId;
            return this;
        }

        public RowEntityBuilder userId(String userId) {
            this.userId = userId;
            return this;
        }

        public RowEntityBuilder profileName(String profileName) {
            this.profileName = profileName;
            return this;
        }

        public RowEntityBuilder helpfulnessNumerator(int helpfulnessNumerator) {
            this.helpfulnessNumerator = helpfulnessNumerator;
            return this;
        }

        public RowEntityBuilder helpfulnessDenominator(int helpfulnessDenominator) {
            this.helpfulnessDenominator = helpfulnessDenominator;
            return this;
        }

        public RowEntityBuilder score(int score) {
            this.score = score;
            return this;
        }

        public RowEntityBuilder date(Date date) {
            this.date = date;
            return this;
        }

        public RowEntityBuilder summary(String summary) {
            this.summary = summary;
            return this;
        }

        public RowEntityBuilder text(String text) {
            this.text = text;
            return this;
        }
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        ReviewEntity reviewEntity = (ReviewEntity) o;

        if (id != null ? !id.equals(reviewEntity.id) : reviewEntity.id != null) return false;
        if (productId != null ? !productId.equals(reviewEntity.productId) : reviewEntity.productId != null) return false;
        return userId != null ? userId.equals(reviewEntity.userId) : reviewEntity.userId == null;
    }

    @Override
    public int hashCode() {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + (productId != null ? productId.hashCode() : 0);
        result = 31 * result + (userId != null ? userId.hashCode() : 0);
        return result;
    }
}
