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
	String [] spaceNames;
    HzlSpace sp;
	String hzlConfigFile;

    public HzlSpaceAdaptor() {
        super();
    }

    public void initService() throws ConfigurationException {
        Element e = getPersist();
		hzlConfigFile = cfg.get("hzlConfigFile", "");
		spaceNames = cfg.getAll("space-name");
		for (String name : spaceNames) {
			sp = new HzlSpace(name, hzlConfigFile);
			NameRegistrar.register(name, sp);
		}
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
        for (String name : spaceNames) {
			sp = new HzlSpace(name, hzlConfigFile);
			NameRegistrar.unregister(name);
		}
    	super.stopService();
    }
}
