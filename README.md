# Industrial-IoT-Data-Simulator



<!-----------------> 


Equipments play a major role in any given industry and can be used for a multitude of
purposes. They need to be monitored constantly to ensure that they function according to
certain requirements.

To ensure this, we connect certain devices to these equipments, which are responsible for
collecting required data.
This data can now be analysed to review the performance of the equipment.
SuperAxis is an Industrial IoT and Analytics Platform created by EcoAxis and deployed
at multiple customer locations - such as Thermal Power Plants, Discrete Manufacturing
Industries, Textile and Sugar Industries, Large Enterprises.
While developing and testing SaaS (software as a service) applications in the platform,
an IoT Data simulator that simulates close to real world data, as per the SuperAxis
configuration, is quite important and vital for the platform and applications.

Once the data is generated, it is made available in different transports such as CSV files,
zip file, FTP etc.

### System Requirements: 
- Java 
- Eclipse (other IDEs can also be used) 
- MySQL Workbench 
- HighCharts 
- XAmpp

The data simulator helps us analyse the performance of an equipment and its parameters, by
generating data. Typically the real world data from the Equipment is sensitive and protected on
one side and on the other, it doesn&#39;t provide all data probabilities within a considered short time
span. While building functional, performant and scalable Analytical systems, it is key to have a
data set close to real world.

To simulate real world situation, a pattern for a defined configuration is created that
enables Data simulator to generate the required data set.
- Using the Data Simulator, one can generate data for multiple such equipment, each of
them carrying many such parameters of different kinds. 
- The data simulator first acquires the relevant information that is stored in the database of
each equipment and its parameters, their nature,configuration and even pattern of data
generation.
- It sorts the data based on the equipments and their parameters.
- For each equipment that is read by the data simulator,data is generated depending on the
nature and the configuration of each parameter of the selected equipment.
- This process is repeated for all equipments that are present in the database’s table.
- Files consisting of the data of all parameters, are created( timestamp of occurrence, value
of data generated) in a certain folder, which is useful in the analysis of the equipment’s
functioning and behaviour.



### Database Structure: 
![image](https://user-images.githubusercontent.com/31934083/182775139-707a8673-c690-46ef-8c44-97838a26ca5b.png)

### Data Generation based on specifications: 
<img width="908" alt="image" src="https://user-images.githubusercontent.com/31934083/183371036-5e558f48-7c99-461b-9044-74a39a767340.png">
Analog data generation


<img width="1070" alt="image" src="https://user-images.githubusercontent.com/31934083/183371184-3f1f9bfe-46c8-457a-82b6-39af79c5dbf0.png">
Digital Data Generation

In the case of digital data, 1 refers to equipment being in use, whereas 0 refers to the equipment not being functional. 
