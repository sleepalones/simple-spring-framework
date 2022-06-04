package org.simpleframework.aop.aspect;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.simpleframework.aop.PointcutLocator;

/**
 * @author brotherming
 * @createTime 2022年05月24日 17:53:00
 */
@AllArgsConstructor
@Getter
public class AspectInfo {

    private int orderIndex;

    private DefaultAspect aspectObject;

    private PointcutLocator pointcutLocator;

}
