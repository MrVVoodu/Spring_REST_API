package toh_rest_api;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Getter @Setter @NoArgsConstructor @Entity
public class Hero {

    private @Id @GeneratedValue Long id;
    private String name;

    Hero(Long id, String name) {
        this.id = id;
        this.name = name;
    }

}
