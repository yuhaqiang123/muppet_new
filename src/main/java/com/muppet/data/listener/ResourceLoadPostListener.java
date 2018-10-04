package com.muppet.data.listener;

import com.muppet.data.util.log.Logger;

public class ResourceLoadPostListener implements Listener{

	@Override
	public EventType eventType() {
		
		return EventType.RESOURCE_LOAD_POST;
	}

	@Override
	public void event(EventType type, Event event) {
		Logger.println(ListenerLogMsg.RESOURCE_LOAD_POST);
		
	}

}
