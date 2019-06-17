package movie.service;

import movie.bean.Category;
import movie.repository.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Transactional
@Service
public class CategoryService {
    @Autowired
    private CategoryRepository categoryRepository;

    /*
     * 搜索电影分类标签
     */
    public List<Category> categorytab() {
        List<Category> list = null;
        list = categoryRepository.findAll();
        if (list == null || list.size() == 0) {
            // 返回
            return null;
        }
        return list;
    }



}
