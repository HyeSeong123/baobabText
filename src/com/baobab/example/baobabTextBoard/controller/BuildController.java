package com.baobab.example.baobabTextBoard.controller;

import com.baobab.example.baobabTextBoard.Container;
import com.baobab.example.baobabTextBoard.service.BuildService;

public class BuildController extends Controller {
	private BuildService buildService;

	public BuildController() {
		buildService = Container.buildService;

	}

	public void doCommand(String cmd) {
		if(cmd.startsWith("build site")) {
			doBuildSite(cmd);
		}
	}

	private void doBuildSite(String cmd) {
		buildService.buildSite();
	}
}