package Manufacturing.ProductLine.Pretreatment;

import Manufacturing.Ingredient.BaseIngredient;

import java.util.List;

/**
 * TODO:剥皮预处理类
 *
 * @author 孟繁霖
 * @date 2021-10-12 8:28
 */
public class Peel implements Pretreatment {
    @Override
    public List<BaseIngredient> treat(List<BaseIngredient> baseIngredientList) {
        return baseIngredientList;
    }
}
