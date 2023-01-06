package jpabook.jpashop.domain.item;

import jpabook.jpashop.domain.Category;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import java.util.List;

@Entity
@DiscriminatorValue("A")
@Getter
@NoArgsConstructor
public class Album extends Item{
    private String artist;
    private String etc;
}
