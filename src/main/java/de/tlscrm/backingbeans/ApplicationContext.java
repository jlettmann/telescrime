package de.tlscrm.backingbeans;

import javax.enterprise.context.ApplicationScoped;
import javax.faces.annotation.FacesConfig;
import javax.faces.annotation.FacesConfig.Version;

/**
 * {@link ApplicationScoped} Backing Bean which ensures JSF 2.3 is used and can also be used to
 * supply resources to the application.
 *
 * @author ltegethoff
 *
 */
@FacesConfig(version = Version.JSF_2_3)
@ApplicationScoped
public class ApplicationContext {

}
