package movie.service;

import movie.bean.Moviecategory;
import movie.repository.MoviecategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.data.domain.Pageable;

@Transactional
@Service

public class   MoviecategoryService {

    @Autowired
    private MoviecategoryRepository moviecategoryRepository;

    public List<Moviecategory> searchTypeList(int categoryid, int page, int size){
        Sort sort = new Sort(Direction.DESC, "movcatid");
        Pageable pageable = new PageRequest(page, size, sort);
        List<Moviecategory> list = null;
        list = moviecategoryRepository.findByCategoryid(categoryid, pageable);
        return list;

    }
}
