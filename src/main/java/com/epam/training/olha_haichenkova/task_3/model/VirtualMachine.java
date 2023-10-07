package com.epam.training.olha_haichenkova.task_3.model;

import java.util.Objects;

public class VirtualMachine {
    private final String operatingSystemSoftware;
    private final String vmFamily;
    private final String vmSeries;
    private final String machineType;
    private final String numberOfGPUs;
    private final String gPUType;
    private final String localSSD;
    private final String datacenterLocation;

    public VirtualMachine (String operatingSystemSoftware,
                           String vmFamily,
                           String vmSeries,
                           String machineType,
                           String numberOfGPUs,
                           String gPUType,
                           String localSSD,
                           String datacenterLocation) {
        this.operatingSystemSoftware = operatingSystemSoftware;
        this.vmFamily = vmFamily;
        this.vmSeries = vmSeries;
        this.machineType = machineType;
        this.numberOfGPUs = numberOfGPUs;
        this.gPUType = gPUType;
        this.localSSD = localSSD;
        this.datacenterLocation = datacenterLocation;
    }

    public String getOperatingSystemSoftware() {
        return operatingSystemSoftware;
    }

    public String getVmFamily() {
        return vmFamily;
    }

    public String getVmSeries() {
        return vmSeries;
    }

    public String getMachineType() {
        return machineType;
    }

    public String getNumberOfGPUs() {
        return numberOfGPUs;
    }

    public String getGPUType() {
        return gPUType;
    }

    public String getLocalSSD() {
        return localSSD;
    }

    public String getDatacenterLocation() {
        return datacenterLocation;
    }

    @Override
    public String toString() {
        return "VirtualMachine{" +
                "operatingSystemSoftware='" + operatingSystemSoftware + '\'' +
                ", vmFamily='" + vmFamily + '\'' +
                ", vmSeries='" + vmSeries + '\'' +
                ", machineType='" + machineType + '\'' +
                ", numberOfGPUs='" + numberOfGPUs + '\'' +
                ", gPUType='" + gPUType + '\'' +
                ", localSSD='" + localSSD + '\'' +
                ", datacenterLocation='" + datacenterLocation + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        VirtualMachine that = (VirtualMachine) o;
        return Objects.equals(operatingSystemSoftware, that.operatingSystemSoftware) &&
                Objects.equals(vmFamily, that.vmFamily) && Objects.equals(vmSeries, that.vmSeries) &&
                Objects.equals(machineType, that.machineType) && Objects.equals(numberOfGPUs, that.numberOfGPUs) &&
                Objects.equals(gPUType, that.gPUType) && Objects.equals(localSSD, that.localSSD) &&
                Objects.equals(datacenterLocation, that.datacenterLocation);
    }

    @Override
    public int hashCode() {
        return Objects.hash(operatingSystemSoftware, vmFamily, vmSeries, machineType, numberOfGPUs,
                gPUType, localSSD, datacenterLocation);
    }

}
