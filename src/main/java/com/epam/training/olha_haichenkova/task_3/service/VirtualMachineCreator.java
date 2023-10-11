package com.epam.training.olha_haichenkova.task_3.service;

import com.epam.training.olha_haichenkova.task_3.model.VirtualMachine;

public class VirtualMachineCreator {

    public String operatingSystemSoftware;
    public String vmFamily;
    public String vmSeries;
    public String machineType;
    public String numberOfGPUs;
    public String gPUType;
    public String localSSD;
    public String datacenterLocation;

    public VirtualMachineCreator setOperatingSystemSoftware(String operatingSystemSoftware) {
        this.operatingSystemSoftware = operatingSystemSoftware;
        return this;
    }

    public VirtualMachineCreator setVmFamily(String vmFamily) {
        this.vmFamily = vmFamily;
        return this;
    }

    public VirtualMachineCreator setVmSeries(String vmSeries) {
        this.vmSeries = vmSeries;
        return this;
    }

    public VirtualMachineCreator setMachineType(String machineType) {
        this.machineType = machineType;
        return this;
    }

    public VirtualMachineCreator setNumberOfGPUs(String numberOfGPUs) {
        this.numberOfGPUs = numberOfGPUs;
        return this;
    }

    public VirtualMachineCreator setgPUType(String gPUType) {
        this.gPUType = gPUType;
        return this;
    }

    public VirtualMachineCreator setLocalSSD(String localSSD) {
        this.localSSD = localSSD;
        return this;
    }

    public VirtualMachineCreator setDatacenterLocation(String datacenterLocation) {
        this.datacenterLocation = datacenterLocation;
        return this;
    }

    public VirtualMachine perform() {
        return new VirtualMachine(this);
    }

}
