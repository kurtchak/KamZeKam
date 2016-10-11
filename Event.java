package org.blackbell.kamzekam;

import java.io.Serializable;

/**
 * Created by kurtcha on 8.10.2016.
 */

/*
[
example item
    {
        "id":1,
        "name":"minus123minut - comeback tour",
        "date":"2016-10-12",
        "time":"20:00:00",
        "place":"Wave club",
        "description":"Sedem rokov ticha prelomila kapela minus123minut na tradi\u010dnom \u010deskom open-ari Votv\u00edr\u00e1k v j\u00fani tohto roka. Do povedomia sa t\u00e1to troj\u010dlenn\u00e1 form\u00e1cia dostala, e\u0161te pod p\u00f4vodn\u00fdm n\u00e1zvom -123 min., v roku 2000 cenou Objav roka od Akad\u00e9mie popul\u00e1rnej hudby. Do ukon\u010denia p\u00f4sobenia v roku 2009 vydali p\u00e4\u0165 \u0161t\u00fadioviek a jednu live dosku a ich tvorba patr\u00ed medzi kultovky tej doby nielen u na\u0161ich z\u00e1padn\u00fdch susedov.\n\nDano \u0160oltis (bicie), Fredrik Jan\u00e1\u010dek (basgitara), Zden\u011bk B\u00edna (gitara, spev)\n",
        "price":"6.00",
        "image":"http:\/\/www.wave.sk\/_cache\/program\/_1200x1000\/1027987-minus123minut%20foto.jpg",
        "created_at":null,
        "updated_at":null
    },
    ...
]
 */

public class Event implements Serializable {
    private long id;
    private String name;
    private String date;
    private String time;
    private String place;
    private String description;
    private String price;
    private String image;
    private String created;
    private String modified;

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

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getPlace() {
        return place;
    }

    public void setPlace(String place) {
        this.place = place;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getCreated() {
        return created;
    }

    public void setCreated(String created) {
        this.created = created;
    }

    public String getModified() {
        return modified;
    }

    public void setModified(String modified) {
        this.modified = modified;
    }

    @Override
    public String toString() {
        return "Event{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", date='" + date + '\'' +
                ", time='" + time + '\'' +
                ", place='" + place + '\'' +
                ", description='" + description + '\'' +
                ", price='" + price + '\'' +
                ", image='" + image + '\'' +
                ", created='" + created + '\'' +
                ", modified='" + modified + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Event event = (Event) o;

        return id == event.id;

    }

    @Override
    public int hashCode() {
        return (int) (id ^ (id >>> 32));
    }
}
