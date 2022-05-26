package com.esgi.jee.apijee.kernel.utils.bucket;

public enum BucketName {
    PROFILE_FILE("projet-je");

    private final String bucketName;

     BucketName(String bucketName) {
        this.bucketName = bucketName;
    }

    public String getBucketName() {
        return bucketName;
    }
}
