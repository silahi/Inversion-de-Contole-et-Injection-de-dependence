# Principe d'Inversion de contrôle et d'Injection de dépendence 

## Objectif 
Créer une application qui utilise l'inversion de contrôle et L'injection de dépendence par  Programmation pure (Sans Framworks) 
et en utilisant un Framwork [Spring] (Par fichier de config et par annotation)

- Utilisation d'un couplage faible
- Programmation par interface
- L'application doit être fermée à la modification et être uverte à l'extension

## Description
L'application consite à réaliser un petit calculateur numérique qui permet de faire les 4 opérations suivantes : 
- Addition
- Soustraction
- Multiplication
- Division

Note : Nous avons implémenté seulement la fonction d'addition dans ce tutoriel. les 3 autres opérations restantes sont laisées aux lecteurs
Vous pouvez cloner le projet et implémenter les autres fonctionnalités. Vous n'aurez donc pas bésoin de modifier le code existant , Vous 
aurez juste à ajouter les fonctionnalités que vous voulez.

 
### Description des classes et Injerfaces
- **INombre** : Interface qui contient deux methodes pour l'initialisation des deux nombres qui seront utilisés pour faire l'opération
- **NombreImpl** : Classe qui implémente l'interface INombre 
- **IRunner** : Interface qui contient une methode run pour le démarrage de l'application
- **RunnerImpl**: Implémentation de l'interface IRunner
- **IAddition** : Interface qui contient la methode somme pour l'opération d'addition
- **AdditionImpl** : Implémentation de l'interface IAddition

## Déroulement
L'inversion de contrôle et l'injection de dépendence sont assurés par la notion de couplage faible, Instantiation dynamique des objets ,
l'invocation dynamique des methodes et la programmation par reflexion.
Nous allons donc voir :

- Inversion de contrôle et Injection de dependence par programmation pure (approche sans framwork)
- Inversion de contrôle et Injection de dependence avec Spring (par fichier de configuration)
- Inversion de contrôle et Injection de dependence avec Spring (par annotations)

### Inversion de contrôle et Injection de dependence par programmation (approche sans framwork)

- Initialisation des nombres

Nous avons bésoin de deux nombres pour l'opération voulue(Addition, etc).
Pour cela on a l'interface **INombre**  et la classe **NombreImpl** pour l'implémentation de l'interface.
Puisque nous avons bésoin des données (les valeurs des deux nombres) on a donc décider de placer ces fichiers (l'interface INombre et la class NombreImpl)
dans le package **dao**:

- Pour l'interface **INombre**

```
package com.alhous.di.dao;
/**
 *
 * @author silah
 */
public interface INombre {
    /**
     *
     * @return le prenimer nombre pour l'opération
     */
    double getNombre1();
    /**
     *
     * @return le deuxième nombre pourl'opération
     */
    double getNombre2();
}
```

- Pour la classe **NombreImpl** : La manière d'obtenir les nombres est laissée au lecteur .
Quant à nous, on a initialisé des valeurs par défauts juste pour vous montrer le principe.

```
package com.alhous.di.dao;
/**
 *
 * @author silah
 */
public class NombreImpl implements INombre {
    @Override
    public double getNombre1() {
        /* La manière de récupérer le nombre : */
        return 15;
    }
    @Override
    public double getNombre2() {
        /* La manière de récupérer le nombre : */
        return 15;
    }
}
```

- Partie métier 

Nous avont bésoin de calculer la somme de deux nombres , pour cela nous allons utiliser l'interface **IAddition** et la classe
**AdditionImpl** pour son implémentation. De plus ici on a bésoin de deux nombres pour la somme , c'est pour quoi on a l'attribut **nombre**
dans la classe **AdditionImpl**. De même la partie métier de notre application se trouve dans le package metier.

- L'interface **IAddition**

``` 
package com.alhous.di.metier;
/**
 *
 * @author silah
 */
public interface IAddition {
    /**
     *
     * @return calcul la somme des  nombres
     */
    double somme();
}
```

- L'implémentation de l'interface

```
package com.alhous.di.metier;
import com.alhous.di.dao.INombre;
/**
 *
 * @author silah
 */
public class AdditionImpl implements IAddition {
    private INombre nombre;
    @Override
    public double somme() {
        return nombre.getNombre1() + nombre.getNombre2();
    }
    public INombre getNombre() {
        return nombre;
    }
    public void setNombre(INombre nombre) {
        this.nombre = nombre;
    }
}
```
**Note :** cette classe contient un attribut (nombre de type INombre), et c'est cette attribut qui sera injecté 

- Partie présentation

Pour que nottre application soit fermée à la modification nous allons utiliser l'instantiation dynamique des objets et 
l'invocation dynamqique des méthodes. Pour cela nous avons bésoin d'un fichier qui sera placé dans la racine du projet
ou dans le classpath. Lors du démarrage de l'application le fichier sera lu et les classes seront instantiées.
Dans cet exemple, on a nommé notre fichier **config.properties** , le fichier peut avoir n'importe qu'elle extension tant que son contenu 
est lisible. voici le contenu de notre fichier.

```
nom=Calculateur Numérique
version=1.1
#Classe de démarrage de l'application
runner=com.alhous.di.prog.RunnerImpl
#Classe pour la récupération des nombres
nombreImpl=com.alhous.di.dao.NombreImpl
#classe pour l'opération d'addition
additionImpl=com.alhous.di.metier.AdditionImpl
```
Dans ce fichier, on précise la classe de présentation  de notre application . La manière d'indiquer les classes a charger va dépendre 
du developpeur . Pour moi, j'ai choisit le principe de clé valeur. Il faut justeque dans votre application vous sachiez comment 
connaitre la bonne classe.

- Interface et classe pour le démarrage

```
package com.alhous.di;
/**
 *
 * @author silah
 */
public interface IRunner {
    /**
     * Point de demarrage de l'application
     */
    void run();
}
```

- **Implémentation**
```
public class RunnerImpl implements IRunner {
    @Override
    public void run() {
        try {
            Path path = new File("config.properties").toPath();
            Optional<String> nombreImplLine = Files.readAllLines(path).stream().filter(line -> line.startsWith("nombreImpl=")).findFirst();
            Optional<String> additionImplLine = Files.readAllLines(path).stream().filter(line -> line.startsWith("additionImpl=")).findFirst();
            if (nombreImplLine.isPresent() && additionImplLine.isPresent()) {
                Class cnombre = Class.forName(nombreImplLine.get().split("=")[1]);
                INombre nombre = (INombre) cnombre.newInstance();
                Class caddition = Class.forName(additionImplLine.get().split("=")[1]);
                IAddition addition = (IAddition) caddition.newInstance();
                Method setNombreMethod = caddition.getMethod("setNombre", INombre.class);
                setNombreMethod.invoke(addition, nombre);
                double res = addition.somme();
                System.out.println(nombre.getNombre1() + " + " + nombre.getNombre2() + " = " + res);
            }
        } catch (IOException | ClassNotFoundException | IllegalAccessException | IllegalArgumentException | InstantiationException | NoSuchMethodException | SecurityException | InvocationTargetException ex) {
            Logger.getLogger(RunnerImpl.class.getName()).log(Level.SEVERE, "Message : " + ex.getMessage(), ex);
        }
    }
}
```
**Note** : Pour instancier une classe dynamiquement on a bésoin d'un objet de type **java.lang.Class** et appeler la méthode statique
**forName** de la classe **Class** et lui passer le nom de la classe et le package où la classe se trouve. Dans notre cas la classe 
est lue dans le fichier de configuration de notre application. Si on developpe une autre classe pour le démarrage de l'application
on va seulement changer dans le fichier de configuration au lieu de modifier notre code.

De plus, l'injection de dépendence se fait lors du démarrage de l'application dans l'attribut nombre de la classe AdditionImpl.
On invoque dynamquement la méthode **setNombre** et lui passer l'objet qu'il faut. Vous constatez le nombre de lignes de codes
qu'il faut pour mettre en place en place ce mécanisme. C'est pour quoi les framworks sont créés pour nous faciliter la tâche.

### Inversion de contrôle et Injection de dependence avec Spring (par fichier de configuration)
 
 **Spring** gère l'injection de dépendence de deux manière de :
 - Par fichier de configuration 
 - Par annotation

 Dans cette partie nous allons voir l'approche par fichier de configuration. Il faut donc créer le fichier de configuration
 (**un fichier .xml**) dans le classpath du projet. Si vous utilisez **maven**, il faut mettre ce fichier dans le dossier **resources**

 On a nommé notre fichier **config.xml**. Voici le contenu de notre fichier
``` 
  <?xml version = "1.0" encoding = "UTF-8"?>
<beans xmlns = "http://www.springframework.org/schema/beans"
       xmlns:xsi = "http://www.w3.org/2001/XMLSchema-instance"
       xsi:schemaLocation = "http://www.springframework.org/schema/beans
   http://www.springframework.org/schema/beans/spring-beans-3.0.xsd">
    <!-- Démarrage de l'application -->
    <bean id = "runner" class = "com.alhous.di.RunnerImpl"/>     
    <!-- Bean pour la récupération des nombres -->
    <bean id = "nombreImpl" class = "com.alhous.di.dao.NombreImpl"/>        
    <!-- Bean pour l'opération d'addtion -->
    <bean id = "additionImpl" class = "com.alhous.di.metier.AdditionImpl">
        <property name="nombre" ref="nombreImpl"  />
    </bean>    
</beans>
 ```

On précise à spring les classes à instancier dynamiquement et les réferences des methodes.
La balise **beans** va contenir toutes les définitions des bean qu'on bésoin dans notre application. Ici chaque classe qui sera 
instanciée dynamiquement est précisée dans une balise  **bean** avec un **id** et l'emplacement de la classe avec l'attribut **class**

Dans la classe **AdditionImpl** on a un attribut de type **INombre** c'est pour quoi on la propriété **nombre** dans le bean 
**additionImpl**. Pour plus e détails réferez vous à la documentation de spring.

Maintenant que nous avons dit à Spring les classes à instancier dynamiquement , on va voir ce qu'on va écrire dans la partie métier

``` 
public class RunnerImpl implements IRunner {
    @Override
    public void run() {
        ApplicationContext context = new ClassPathXmlApplicationContext("config.xml");
        INombre nombre = (INombre) context.getBean("nombreImpl");
        IAddition addition = (IAddition) context.getBean("additionImpl");
        double res = addition.somme();
        System.out.println(nombre.getNombre1() + " + " + nombre.getNombre2() + " = " + res);
    }
}

``` 
**Note** : On va seulement  passer à spring notre fichier de configuration qui se trouve dans le classpath de notre projet,
invoquer les classes avec leur id et faire les opérations dont on a bésoin. dans la partie suivante on va voir comment diminuer
encore le nombre de ligne de codes grâce aux annotations

### Inversion de contrôle et Injection de dependence avec Spring (par annotations)

On va utiliser deux annotation en générale :
- **@Component** ou **@Service**
- **@Autowire**

Voici ce que l'on peut faire dans notre classe **NombreImpl** pour indiquer à spring de charger cette classe 

```
  package com.alhous.di.annota.dao;
import com.alhous.di.dao.INombre;
import org.springframework.stereotype.Component;
/**
 *
 * @author silah
 */
@Component("nombreImplV2")
public class NombreImpl implements INombre {
    @Override
    public double getNombre1() {
        /* Code pour la récupération du premier nombre */
        return 15;
    }
    @Override
    public double getNombre2() {
        /* Code pour la récupération du deuxième nombre */
        return 15;
    }
}
```
 
 **Note** : Le nom du composant est optionnel 

 - Pour la classe **AdditionImpl**

```
package com.alhous.di.annota.metier;
import com.alhous.di.dao.INombre;
import com.alhous.di.metier.IAddition;
import javax.annotation.Resource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
/**
 *
 * @author silah
 */
@Component("additionImplV2")
public class AdditionImpl implements IAddition {
    @Autowired
    @Resource(name = "nombreImplV2") // optionnel
    private INombre nombre;
    @Override
    public double somme() {
        return nombre.getNombre1() + nombre.getNombre2();
    }
    public INombre getNombre() {
        return nombre;
    }
    public void setNombre(INombre nombre) {
        this.nombre = nombre;
    }
}
```

**Note** : Ici l'annotation **@Autowire** permet d'indiquer à spring que cette attribut (ici nombre) sera injecté.
- Voici donc ce qu'on aura dans la partie présentation

``` 
package com.alhous.di.annota;
import com.alhous.di.IRunner;
import com.alhous.di.dao.INombre;
import com.alhous.di.metier.IAddition;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.stereotype.Component;
/**
 *
 * @author silah
 */
@Component
public class RunnerImpl implements IRunner {
    @Override
    public void run() {
        ApplicationContext context = new AnnotationConfigApplicationContext("com.alhous.di.annota.dao", "com.alhous.di.annota.metier");
        INombre nombre = (INombre) context.getBean("nombreImplV2");
        IAddition addition = (IAddition) context.getBean("additionImplV2");
        System.out.println(nombre.getNombre1() + " + " + nombre.getNombre2() + " = " + addition.somme());
    }
}
``` 

**Note** : Avec les annotations, n a plus bésoin de préciser un fichier de configuration. Dans le context de l'application 
On instancie le context de l'application (on peut précier à spring les packages où se trouve les classes : optionnel)
Dans la méthode **getBean** je peut préciser seulement le type de class ou on peut  indiquer le nom du composant qu'on fait reférence.

Remarquez que nous n'avons pas bésoin d'appeler la méthode le methode **setNombre** pour lui passer l'interface **INombre**; Ce mécanisme 
est assuré par l'injection de dépendence.
 
