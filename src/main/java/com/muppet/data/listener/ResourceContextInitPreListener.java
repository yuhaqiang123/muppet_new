package com.muppet.data.listener;

import com.muppet.data.util.log.Logger;

public class ResourceContextInitPreListener implements Listener{

	@Override
	public EventType eventType() {
		
		return EventType.RESOURCE_CONTEXT_INIT_PRE;
	}

	@Override
	public void event(EventType type, Event event) {
		Logger.println(ListenerLogMsg.RESOURCE_CONTEXT_INIT_PRE);
	}

}
