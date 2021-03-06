package main.java.me.creepsterlgc.core.api;

public class CoreAPI {
	
	public CoreAPIBanManager getBanManager() {
		if(CoreAPIBanManager.instance == null) CoreAPIBanManager.instance = new CoreAPIBanManager();
		return CoreAPIBanManager.instance;
	}
	
	/*
	 * returns Core's ban manager @(/me/creepsterlgc/core/api/CoreAPIBanManager)
	 */
	
	public CoreAPIMuteManager getMuteManager() {
		if(CoreAPIMuteManager.instance == null) CoreAPIMuteManager.instance = new CoreAPIMuteManager();
		return CoreAPIMuteManager.instance;
	}
	
	/*
	 * returns Core's mute manager @(/me/creepsterlgc/core/api/CoreAPIMuteManager)
	 * 
	 */
	
	public CoreAPIWarpManager getWarpManager() {
		if(CoreAPIWarpManager.instance == null) CoreAPIWarpManager.instance = new CoreAPIWarpManager();
		return CoreAPIWarpManager.instance;
	}
	
	/*
	 * returns Core's warp manager @(/me/creepsterlgc/core/api/CoreAPIWarpManager)
	 * 
	 */
	
	public CoreAPISpawnManager getSpawnManager() {
		if(CoreAPISpawnManager.instance == null) CoreAPISpawnManager.instance = new CoreAPISpawnManager();
		return CoreAPISpawnManager.instance;
	}

	/*
	 * returns Core's spawn manager @(/me/creepsterlgc/core/api/CoreAPISpawnManager)
	 * 
	 */
	
	public CoreAPIEconomyManager getEconomyManager() {
		if(CoreAPIEconomyManager.instance == null) CoreAPIEconomyManager.instance = new CoreAPIEconomyManager();
		return CoreAPIEconomyManager.instance;
	}

	/*
	 * returns Core's economy manager @(/me/creepsterlgc/core/api/CoreAPIEconomyManager)
	 * 
	 */
	
}
