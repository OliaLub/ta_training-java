package com.epam.training.olha_haichenkova.task_3.service;

import com.epam.training.olha_haichenkova.task_3.model.VirtualMachine;

public class VirtualMachineCreator {
    private static final String OPERATING_SYSTEM_SOFTWARE = "testdata.operatingSystemSoftware";
    private static final String VM_FAMILY = "testdata.vMFamily";
    private static final String VM_SERIES = "testdata.vmSeries";
    private static final String MACHINE_TYPE = "testdata.machineType";
    private static final String NUMBER_OF_GPU = "testdata.numberOfGPUs";
    private static final String GPU_TYPE = "testdata.gPUType";
    private static final String LOCAL_SSD = "testdata.localSSD";
    private static final String DATACENTER_LOCATION = "testdata.datacenterLocation";

    public static VirtualMachine withCharacteristics(){
        return new VirtualMachine(TestDataReader.getTestData(OPERATING_SYSTEM_SOFTWARE),
                                  TestDataReader.getTestData(VM_FAMILY),
                                  TestDataReader.getTestData(VM_SERIES),
                                  TestDataReader.getTestData(MACHINE_TYPE),
                                  TestDataReader.getTestData(NUMBER_OF_GPU),
                                  TestDataReader.getTestData(GPU_TYPE),
                                  TestDataReader.getTestData(LOCAL_SSD),
                                  TestDataReader.getTestData(DATACENTER_LOCATION));
                        }

}
