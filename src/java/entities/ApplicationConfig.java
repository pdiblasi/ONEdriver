/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entities;

import java.util.Set;
import javax.ws.rs.core.Application;

/**
 *
 * @author pdiblasi <ingpdiblasi at gmail.com>
 */
@javax.ws.rs.ApplicationPath("rest")
public class ApplicationConfig extends Application {

    @Override
    public Set<Class<?>> getClasses() {
        Set<Class<?>> resources = new java.util.HashSet<>();
        addRestResourceClasses(resources);
        return resources;
    }

    /**
     * Do not modify addRestResourceClasses() method.
     * It is automatically populated with
     * all resources defined in the project.
     * If required, comment out calling this method in getClasses().
     */
    private void addRestResourceClasses(Set<Class<?>> resources) {
        resources.add(API.OneACL.class);
        resources.add(API.OneCluster.class);
        resources.add(API.OneClusterPool.class);
        resources.add(API.OneDatastore.class);
        resources.add(API.OneDatastorePool.class);
        resources.add(API.OneDocument.class);
        resources.add(API.OneDocumentPool.class);
        resources.add(API.OneGroup.class);
        resources.add(API.OneGroupPool.class);
        resources.add(API.OneGroupQuota.class);
        resources.add(API.OneHost.class);
        resources.add(API.OneHostPool.class);
        resources.add(API.OneImage.class);
        resources.add(API.OneImagePool.class);
        resources.add(API.OneSecGroup.class);
        resources.add(API.OneSecGroupPool.class);
        resources.add(API.OneSystem.class);
        resources.add(API.OneTemplate.class);
        resources.add(API.OneTemplatePool.class);
        resources.add(API.OneUser.class);
        resources.add(API.OneUserPool.class);
        resources.add(API.OneUserQuota.class);
        resources.add(API.OneVDC.class);
        resources.add(API.OneVDCPool.class);
        resources.add(API.OneVM.class);
        resources.add(API.OneVMPool.class);
        resources.add(API.OneVN.class);
        resources.add(API.OneVNPool.class);
        resources.add(API.OneZone.class);
        resources.add(API.OneZonePool.class);
        resources.add(entities.GenericResource.class);
        resources.add(entities.SpecificResource.class);
    }
    
}
