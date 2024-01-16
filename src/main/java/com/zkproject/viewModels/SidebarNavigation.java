package com.zkproject.viewModels;


import com.zkproject.services.SidebarPage;
import com.zkproject.services.SidebarPageConfig;
import org.zkoss.bind.annotation.Init;
import org.zkoss.zk.ui.Component;
import org.zkoss.zk.ui.Executions;
import org.zkoss.zk.ui.event.Event;
import org.zkoss.zk.ui.event.EventListener;
import org.zkoss.zk.ui.event.Events;
import org.zkoss.zk.ui.event.SerializableEventListener;
import org.zkoss.zk.ui.select.SelectorComposer;
import org.zkoss.zk.ui.select.Selectors;
import org.zkoss.zk.ui.select.annotation.VariableResolver;
import org.zkoss.zk.ui.select.annotation.Wire;
import org.zkoss.zk.ui.select.annotation.WireVariable;
import org.zkoss.zul.*;
import org.zkoss.zuti.zul.Apply;

@VariableResolver(org.zkoss.zkplus.spring.DelegatingVariableResolver.class)
public class SidebarNavigation extends SelectorComposer<Component> {

    private static final long serialVersionUID = 1L;
    @Wire
    Grid sidebar;

    //wire service
    @WireVariable("sidebarPageConfigAjaxbased")
    SidebarPageConfig pageConfig;

    private String includeSrc = "/home.zul";


    public void setHome(){

        SidebarPage name=pageConfig.getPages().get(0);
        Event event=new Event("click");
        //change the URI of shadow element, apply
        Apply apply = new Apply();
        apply.setTemplate(null);
        apply.setTemplateURI(name.getUri());
        apply.recreate();

        //advance bookmark control,
        //bookmark with a prefix
        if(name!=null){
            getPage().getDesktop().setBookmark("p_"+name);
        }
    }

    @Override
    public void doAfterCompose(Component comp) throws Exception{
        super.doAfterCompose(comp);

        //to initial view after view constructed.
        Rows rows = sidebar.getRows();

        for(SidebarPage page:pageConfig.getPages()){
            Row row = constructSidebarRow(page.getName(),page.getLabel(),page.getIconUri(),page.getUri());
            rows.appendChild(row);
        }
        this.setHome();

    }

    private Row constructSidebarRow(final String name,String label, String imageSrc, final String locationUri) {

        //construct component and hierarchy
        Row row = new Row();
        Image image = new Image(imageSrc);
        Label lab = new Label(label);

        row.appendChild(image);
        row.appendChild(lab);

        //set style attribute
        row.setSclass("sidebar-fn");

        //new and register listener for events
        EventListener<Event> onActionListener = new SerializableEventListener<Event>(){
            private static final long serialVersionUID = 1L;

            public void onEvent(Event event) throws Exception {
                //redirect current url to new location
                if(locationUri.startsWith("http")){
                    //open a new browser tab
                    Executions.getCurrent().sendRedirect(locationUri);
                }else{

                    //change the URI of shadow element, apply
                    Apply apply = (Apply) Selectors.iterable(event.getPage(), "::shadow#content")
                            .iterator().next();
                    apply.setTemplate(null);
                    apply.setTemplateURI(locationUri);
                    apply.recreate();

                    //advance bookmark control,
                    //bookmark with a prefix
                    if(name!=null){
                        getPage().getDesktop().setBookmark("p_"+name);
                    }
                }
            }
        };
        row.addEventListener(Events.ON_CLICK, onActionListener);

        return row;
    }

    public String getIncludeSrc() {
        return includeSrc;
    }

    public void setIncludeSrc(String includeSrc) {
        this.includeSrc = includeSrc;
    }

}
