package com.softlayer.ksy.service;

import java.util.List;

import com.google.gson.Gson;
import com.softlayer.api.*;
import com.softlayer.api.service.Account;
import com.softlayer.api.service.Location;
import com.softlayer.api.service.virtual.Guest;
import com.softlayer.api.service.virtual.guest.block.device.Template;
import com.softlayer.api.service.virtual.guest.block.device.template.Group;

public class VMService {

	public void OrderVM() {

		ApiClient client = new RestApiClient().withCredentials("IBM1389723",
				"df6acb888f21046c8594ff3ec7a462da96adfd361d22627f84eadc09ec5d4657");
		Group.Service service = Group.service(client);
		Template template = new Template();
		template.setDiskImageId((long) 1721599);
		List<Group> publicImages = service.getPublicImages();

		Gson gson = new Gson();
		for (Group image : publicImages) {
			if (image.getId() == (long) 1721449)
				System.out.println(gson.toJson(image));
		}

		Guest guest = new Guest();
		guest.setHostname("test");
		guest.setDomain("cloudtech03.sk.com");
		guest.setStartCpus((long) 1);
		guest.setMaxMemory((long) 1024);
		guest.setHourlyBillingFlag(true);
		// guest.setOperatingSystemReferenceCode("UBUNTU_LATEST");
		guest.setLocalDiskFlag(false);
		guest.setDatacenter(new Location());
		guest.getDatacenter().setName("seo01");

		Account.Service accountService = Account.service(client);
		for (Group blockGroup : accountService.getPrivateBlockDeviceTemplateGroups())
			if ((long) 1721599 == blockGroup.getId())
				guest.setBlockDeviceTemplateGroup(blockGroup);

		guest = Guest.service(client).createObject(guest);
		System.out.println("Virtual server ordered with ID: " + guest.getId() + ", " + guest.getGlobalIdentifier());

		// Guest.service(client).getOrderTemplate(template, guest);
	}
}
