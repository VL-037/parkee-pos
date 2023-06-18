package vincentlow.parkee.parkingpos.model.entity;

import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;

import org.hibernate.annotations.GenericGenerator;

import lombok.Data;

@Data
@MappedSuperclass
public class BaseEntity {

  @Id
  @GeneratedValue(generator = "uuid-sequence")
  @GenericGenerator(name = "uuid-sequence", strategy = "org.hibernate.id.UUIDGenerator")
  @Column(name = "id", updatable = false, nullable = false)
  protected String id;

  @Column(name = "created_by")
  protected String createdBy;

  @Column(name = "created_date")
  protected LocalDateTime createdDate;

  @Column(name = "updated_by")
  protected String updatedBy;

  @Column(name = "updated_date")
  protected LocalDateTime updatedDate;

  @Column(name = "mark_for_delete")
  protected boolean markForDelete;
}
