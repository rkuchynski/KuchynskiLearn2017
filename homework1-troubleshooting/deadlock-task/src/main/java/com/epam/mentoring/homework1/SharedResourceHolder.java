package com.epam.mentoring.homework1;

import java.util.ArrayList;
import java.util.List;

/**
 * Object that contains shared resources.
 * <p/>
 * Date: 02/18/2017
 *
 * @author Raman Kuchynski
 */
public class SharedResourceHolder {

    private List<Object> sharedResources;
    private final int resourcesCount;

    /**
     * Constructor.
     *
     * @param resourcesCount count of shared resources to create.
     */
    public SharedResourceHolder(int resourcesCount) {
        this.resourcesCount = resourcesCount;
        sharedResources = new ArrayList(this.resourcesCount);
        for(int i=0; i < this.resourcesCount; i++) {
            sharedResources.add(new Object());
        }
    }

    /**
     * Get shared resource by number.
     * @param resourceNumber resource number.
     * @return {@link Object} that is used as a shared resource.
     */
    public Object getSharedResource(int resourceNumber) {
        return sharedResources.get(resourceNumber);
    }

    /**
     * Get number of the next resource to acquire.
     * @param resourceNumber resource number.
     * @return next resource number.
     */
    public int getNextResourceNumber(int resourceNumber) {
        if (resourceNumber == (resourcesCount - 1)) {
            return 0;
        }
        return resourceNumber + 1;
    }

}
