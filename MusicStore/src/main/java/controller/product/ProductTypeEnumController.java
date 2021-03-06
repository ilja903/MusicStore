package controller.product;

import java.io.Serializable;

import javax.enterprise.context.RequestScoped;
import javax.inject.Named;

import model.product.ProductType;

/**
 * @author ilja Class is used for transforming Enum into array for JSF selectone
 *         tag
 */
@Named
@RequestScoped
public class ProductTypeEnumController implements Serializable {
	private static final long serialVersionUID = 1L;

	public ProductType[] getProductTypeValues() {
		return ProductType.values();
	}

}
