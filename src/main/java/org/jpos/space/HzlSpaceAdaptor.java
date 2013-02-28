package org.jpos.space;

/**
 * Created by IntelliJ IDEA.
 * User: Eric
 * Date: 2/20/12
 * Time: 10:24 AM
 * To change this template use File | Settings | File Templates.
 */

import org.jdom.Element;
import org.jpos.core.ConfigurationException;
import org.jpos.q2.QBeanSupport;
import org.jpos.util.NameRegistrar;

public class HzlSpaceAdaptor extends QBeanSupport {
    String spaceUri;
    HzlSpace sp;

    public HzlSpaceAdaptor() {
        super();
    }

    public void initService() throws ConfigurationException {
        Element e = getPersist();
        spaceUri = cfg.get("space-name", "hzl:DefaultSpace");
        sp = new HzlSpace(cfg);
       NameRegistrar.register(spaceUri, sp);
    }

    public void startService() {
        try {
            super.startService();
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    protected void stopService() throws Exception {
    	sp.shutdownInstance();
        NameRegistrar.unregister (spaceUri);
    	super.stopService();
    }
}
