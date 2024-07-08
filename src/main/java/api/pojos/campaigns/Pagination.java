package api.pojos.campaigns;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.FieldDefaults;

@Getter
@Setter
@FieldDefaults(level = AccessLevel.PRIVATE)
public class Pagination{
     int pageNumber;
     int pageSize;
     int totalElements;
     int totalPages;
}
