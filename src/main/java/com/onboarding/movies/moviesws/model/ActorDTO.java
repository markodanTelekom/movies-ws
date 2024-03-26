package com.onboarding.movies.moviesws.model;

public class ActorDTO {

    private Integer ActorId;

    private String actorImage;

    private String imageName;

    private String fullName;

    public Integer getActorId() {
        return ActorId;
    }

    public void setActorId(Integer actorId) {
        ActorId = actorId;
    }

    public String getActorImage() {
        return actorImage;
    }

    public void setActorImage(String actorImage) {
        this.actorImage = actorImage;
    }

    public String getImageName() {
        return imageName;
    }

    public void setImageName(String imageName) {
        this.imageName = imageName;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }
}
